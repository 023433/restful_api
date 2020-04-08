package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(exported = true)
public interface RepositoryCategory extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category>{

    @RestResource(exported = false)
    void deleteById(Long id);

}