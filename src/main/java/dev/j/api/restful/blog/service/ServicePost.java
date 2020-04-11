package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.RepositoryPost;
import dev.j.api.restful.blog.repository.post.RepositoryContent;
import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.post.Content;
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

    public Page<Post> getPosts(Pageable pageable){
        return repositoryPost.findAll(pageable);
    }

	public Page<Post> getPosts(String pageNo, String pageSize) {
        int page = Integer.parseInt(pageNo);
        int size = Integer.parseInt(pageSize);
        Sort sort = Sort.by(Order.desc("no"));

        Pageable pageable = PageRequest.of(page, size, sort);
        
		return repositoryPost.findAllWithSummaryBy(pageable);
	}

	public void savePost(Post post) {
        repositoryPost.save(post);
	}

	public Post getPost(Long postNo) {
        Content content = repositoryContent.findByPostNo(postNo);
        Post post = content.getPost();

        post.setContent(content);

		return post;
	}

	public void saveContent(Content content) {
        repositoryContent.save(content);
	}

    
}