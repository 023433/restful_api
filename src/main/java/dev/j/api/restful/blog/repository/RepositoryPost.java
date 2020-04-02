package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RepositoryPost extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{

}