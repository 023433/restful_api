package dev.j.api.restful.blog.repository.post.category;

import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import java.util.List;

public interface CustomRepositoryCategory {

    CategoryParent findByCategories(List<String> categories);

}