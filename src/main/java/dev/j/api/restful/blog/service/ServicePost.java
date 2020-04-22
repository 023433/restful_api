package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.category.RepositoryCategoryParent;
import dev.j.api.restful.blog.repository.post.RepositoryContent;
import dev.j.api.restful.blog.repository.post.RepositoryPost;
import dev.j.api.restful.blog.repository.post.RepositorySummary;
import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.post.Content;
import dev.j.api.restful.blog.vo.post.Summary;
import dev.j.api.restful.blog.vo.post.category.CategoryParent;
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
  private RepositorySummary repositorySummary;

  @Autowired
  private RepositoryCategoryParent repositoryContentCategoryParent;
  
  public Page<Summary> getPostsSummary(String pageNo, String pageSize) {
    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("no"));
    
    Pageable pageable = PageRequest.of(page, size, sort);
  
    return repositorySummary.findAllWithByPostPublish(pageable, true);
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

	public Page<Summary> getPostsSummaryWithCategory(String pageNo, String pageSize, List<String> categories) {

    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("no"));

    Pageable pageable = PageRequest.of(page, size, sort);

    CategoryParent category = repositoryContentCategoryParent.findByCategories(categories);

    return repositorySummary.findAllWithByCategoryCategoryNo(pageable, category.getNo());
	}

    
}