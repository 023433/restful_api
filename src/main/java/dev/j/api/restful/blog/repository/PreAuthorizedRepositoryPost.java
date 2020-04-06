package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(exported = true)
public interface PreAuthorizedRepositoryPost extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @Override
    void delete(Post entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @Override
    void deleteById(Long id);
}