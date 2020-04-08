package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface RepositoryPost extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @Override
    @RestResource(exported = false)
    void delete(Post entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @Override
    <S extends Post> S save(S entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @Override
    <S extends Post> List<S> saveAll(Iterable<S> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    @Override
    <S extends Post> S saveAndFlush(S entity);
}