package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.post.RepositoryContent;
import dev.j.api.restful.blog.repository.post.RepositoryPost;
import dev.j.api.restful.blog.repository.post.category.RepositoryCategoryParent;
import dev.j.api.restful.blog.repository.post.category.RepositoryPostCategory;
import dev.j.api.restful.blog.repository.post.summary.RepositorySummary;
import dev.j.api.restful.blog.repository.post.summary.RepositorySummaryCategory;
import dev.j.api.restful.blog.repository.post.summary.RepositorySummaryTag;
import dev.j.api.restful.blog.repository.post.tag.RepositoryPostTag;
import dev.j.api.restful.blog.repository.post.tag.RepositoryTag;
import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.PostCount;
import dev.j.api.restful.blog.vo.post.PostParam;
import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import dev.j.api.restful.blog.vo.post.category.PostCategory;
import dev.j.api.restful.blog.vo.post.content.Content;
import dev.j.api.restful.blog.vo.post.summary.Summary;
import dev.j.api.restful.blog.vo.post.summary.SummaryCategory;
import dev.j.api.restful.blog.vo.post.summary.SummaryTag;
import dev.j.api.restful.blog.vo.post.tag.PostTag;
import dev.j.api.restful.blog.vo.post.tag.Tag;
import dev.j.api.restful.common.property.PropertyJwtToken;
import dev.j.api.restful.common.property.PropertyPath;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicePost extends AbstractService {

  @Autowired
  private RepositoryPost repositoryPost;

  @Autowired
  private RepositoryContent repositoryContent;

  @Autowired
  private RepositorySummary repositorySummary;

  @Autowired
  private RepositorySummaryCategory repositorySummaryCategory;

  @Autowired
  private RepositorySummaryTag repositorySummaryTag;

  @Autowired
  private RepositoryCategoryParent repositoryContentCategoryParent;

  @Autowired
  private RepositoryPostCategory repositoryPostCategory;

  @Autowired
  private RepositoryTag repositoryTag;
  
  @Autowired
	private RepositoryPostTag repositoryPostTag;

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

    return repositoryPost.findAllByPublish(pageable, true);
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

  @Transactional
	public String addPost(HttpServletRequest request, PostParam postParam) {
    String jwtToken = request.getHeader(PropertyJwtToken.STR_TOKEN);
    String userId = componentJwtToken.getUserId(jwtToken);
    
    Post post = new Post();

    post.setSubject(postParam.getSubject());
    post.setAuthor(userId);
    post.setPublish(Boolean.parseBoolean(postParam.getPublish()));

    repositoryPost.save(post);

    Long postNo = post.getNo();

    List<PostCategory> categories = new ArrayList<>();

    for (String category : postParam.getCategory()) {
      Long categoryNo = Long.parseLong(category);

      PostCategory postCategory = new PostCategory();
      postCategory.setCategoryNo(categoryNo);
      postCategory.setPostNo(postNo);

      categories.add(postCategory);
    }

    repositoryPostCategory.saveAll(categories);


    String strContent = postParam.getContent();

    String resSummary = strContent.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
    int lastIdx = 250;

    if(lastIdx > resSummary.length()){
      lastIdx = resSummary.length();
    }

    resSummary = resSummary.substring(0, lastIdx);

    String thumbnailSavePath = "";
    String thumbnailSaveName = postParam.getThumbnailSaveName();
    String tempThumbnailSavePath = postParam.getThumbnailSavePath();

    if(isNotNullOrEmpty(thumbnailSaveName)){
      thumbnailSavePath = PropertyPath.POST + postNo + PropertyPath.POST_THUMBNAIL;
      String source = tempThumbnailSavePath + thumbnailSaveName;
      String destination = thumbnailSavePath + thumbnailSaveName;

      Boolean result = componentFileUpload.moveTo(source, destination);

      if( !result ){
        thumbnailSavePath = "";
      }
    }

    Summary summary = new Summary();
    summary.setPostNo(postNo);
    summary.setPost(post);
    summary.setThumbnail(thumbnailSaveName);
    summary.setSavePath(thumbnailSavePath);
    summary.setSummary(resSummary);

    repositorySummary.save(summary);


    Content content = new Content();
    content.setPost(post);
    content.setPostNo(postNo);
    content.setContent(strContent);

    repositoryContent.save(content);


    String[] tags = postParam.getTags();

    if(isNotNullOrEmpty(tags)){

      for (String tag : tags) {

        Tag tempTag = repositoryTag.findByTitle(tag);
        PostTag postTag = new PostTag();

        if(tempTag == null){
          tempTag = new Tag(tag);
          repositoryTag.save(tempTag);
        }
        
        postTag.setPost(post);
        postTag.setPostNo(postNo);
        postTag.setTag(tempTag);
        postTag.setTagNo(tempTag.getNo());

        repositoryPostTag.save(postTag);
        
      }
      
    }

		return null;
	}

  public boolean addPostValidate(HttpServletRequest request, PostParam post) {
    if(isNullOrEmpty(post.getSubject())){
      return false;
    }

    if(isNullOrEmpty(post.getContent())){
      return false;
    }

    if(isNullOrEmpty(post.getCategory())){
      return false;
    }

    return true;
  }

  public Page<Post> getPosts(String pageNo, String pageSize) {
    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("no"));
    
    Pageable pageable = PageRequest.of(page, size, sort);

    return repositoryPost.findAll(pageable);
  }

    
}