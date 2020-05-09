package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.post.category.RepositoryCategoryParent;
import dev.j.api.restful.blog.repository.post.RepositoryContent;
import dev.j.api.restful.blog.repository.post.RepositoryPost;
import dev.j.api.restful.blog.repository.post.summary.RepositorySummaryCategory;
import dev.j.api.restful.blog.repository.post.summary.RepositorySummaryTag;
import dev.j.api.restful.blog.vo.post.Content;
import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.PostCount;
import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import dev.j.api.restful.blog.vo.post.summary.SummaryCategory;
import dev.j.api.restful.blog.vo.post.summary.SummaryTag;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class ServicePost extends AbstractService {

  @Autowired
  private RepositoryPost repositoryPost;

  @Autowired
  private RepositoryContent repositoryContent;

  @Autowired
  private RepositorySummaryCategory repositorySummaryCategory;

  @Autowired
  private RepositorySummaryTag repositorySummaryTag;

  @Autowired
  private RepositoryCategoryParent repositoryContentCategoryParent;
  
  public Page<SummaryCategory> getPostsSummary(String pageNo, String pageSize) {
    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("postNo"));
    
    Pageable pageable = PageRequest.of(page, size, sort);
  
    return repositorySummaryCategory.findAllWithByPostPublish(pageable, true);
  }

  public void savePost(Post post) {
    repositoryPost.save(post);
  }

  public Content getPost(Long postNo) {
    return repositoryContent.findOneByPostNoAndPostPublish(postNo, true);
  }

  public void saveContent(Content content) {
    repositoryContent.save(content);
  }

  public Page<Post> getPostsNewest() {
    Sort sort = Sort.by(Order.desc("no"));
    
    Pageable pageable = PageRequest.of(0, 5, sort);

    return repositoryPost.findAll(pageable);
  }

	public Page<Post> getPostsNewestCategory(String category, String pageNo, String pageSize) {
    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("no"));

    Pageable pageable = PageRequest.of(page, size, sort);

    return repositoryPost.findAllWithByCategoryCategoryNo(pageable, Long.valueOf(category));
	}

	public Page<SummaryCategory> getPostsSummaryWithCategory(String pageNo, String pageSize, List<String> categories) {

    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("postNo"));

    Pageable pageable = PageRequest.of(page, size, sort);

    categories.removeAll(Collections.singleton(""));

    CategoryParent category = null;

    try {
      category = repositoryContentCategoryParent.findByCategories(categories);
    } catch (Exception e) {
      return null;
    }

    if(category == null){
      return null;
    }

    return repositorySummaryCategory.findAllWithByPostPublishAndCategoryCategoryNo(pageable, true, category.getNo());
	}

	public Page<SummaryTag> getPostsSummaryWithTag(String pageNo, String pageSize, String tag) {

    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("postNo"));

    Pageable pageable = PageRequest.of(page, size, sort);

		return repositorySummaryTag.findAllWithByPostPublishAndTagTagTitle(pageable, true, tag);
	}

	public Page<SummaryCategory> getPostsSummaryWithSearch(String pageNo, String pageSize, String searchVal) {
    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("postNo"));

    Pageable pageable = PageRequest.of(page, size, sort);

		return repositorySummaryCategory.findAllWithByPostPublishAndPostSubjectContains(pageable, true, searchVal);
	}

	public List<PostCount> getCountGroupByCreateDate(String date) {
		return repositoryPost.findGroupByCreateDate(date);
	}

	public Page<SummaryCategory> getPostsByCreateDate(String pageNo, String pageSize, String date) {
    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("postNo"));
    
    Pageable pageable = PageRequest.of(page, size, sort);

    String[] temp = date.split("-");

    int year = Integer.parseInt(temp[0]);
    int month = Integer.parseInt(temp[1]);
    int dayOfMonth = Integer.parseInt(temp[2]);

    LocalDateTime start = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
    LocalDateTime end = LocalDateTime.of(year, month, dayOfMonth, 23, 59);
    
    return repositorySummaryCategory.findAllWithByPostPublishAndPostCreateDateBetween(pageable, true, start, end);
	}

    
}