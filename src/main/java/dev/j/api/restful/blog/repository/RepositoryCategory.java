package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryCategory extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category>{

}