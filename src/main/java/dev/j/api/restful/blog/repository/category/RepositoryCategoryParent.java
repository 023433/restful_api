package dev.j.api.restful.blog.repository.category;

import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryCategoryParent extends JpaRepository<CategoryParent, Long>, JpaSpecificationExecutor<CategoryParent>, CustomRepositoryCategory{

}