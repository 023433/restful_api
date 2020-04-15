package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.category.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryPostCategory extends JpaRepository<PostCategory, Long>, JpaSpecificationExecutor<PostCategory>{

}