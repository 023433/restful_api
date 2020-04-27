package dev.j.api.restful.blog.repository.post.category;

import dev.j.api.restful.blog.vo.post.category.CategoryChildren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryCategoryChildren extends JpaRepository<CategoryChildren, Long>, JpaSpecificationExecutor<CategoryChildren>{

}