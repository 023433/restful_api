package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryPost extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{
    Page<Post> findAllWithByCategoryCategoryNo(Pageable pageable, Long categoryNo);
}