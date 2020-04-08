package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface RepositoryContent extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content>{

}