package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.post.category.RepositoryCategoryChildren;
import dev.j.api.restful.blog.repository.post.category.RepositoryCategoryParent;
import dev.j.api.restful.blog.repository.post.category.RepositoryPostCategory;
import dev.j.api.restful.blog.vo.post.category.CategoryChildren;
import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import dev.j.api.restful.blog.vo.post.category.PostCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategory extends AbstractService {

  @Autowired
  private RepositoryCategoryChildren repositoryCategoryChildren;

  @Autowired
  private RepositoryCategoryParent repositoryCategoryParent;

  @Autowired
  private RepositoryPostCategory repositoryPostCategory;

  public List<CategoryChildren> getCategoriesWithCount() {

    List<CategoryChildren> categories = repositoryCategoryChildren.findAll();
    List<PostCategory> postCategories = repositoryPostCategory.findPostCount();

    List<CategoryChildren> root = new ArrayList<>();
    Map<Long, CategoryChildren> all = new HashMap<>();
    Map<Long, Long> allCount = new HashMap<>();

    for (PostCategory category : postCategories) {
      allCount.put(category.getCategoryNo(), category.getCount());
    }

    for (CategoryChildren category : categories) {
      category.setChildren(null);

      Long no = category.getNo();
      Long count = allCount.get(no);

      if(count != null && count != 0){
        category.setCount(count.intValue());
      }

      all.put(no, category);
    }

    for (CategoryChildren category : categories) {
      category.setChildren(new ArrayList<>());
      if(category.getParentNo() == null){
        root.add(category);
      }else{
        CategoryChildren parent = all.get(category.getParentNo());

        List<CategoryChildren> childrens = parent.getChildren();

        if(childrens == null){
          childrens = new ArrayList<>();
        }

        childrens.add(category);
      }
    }
    
    return root;
  }

  public List<CategoryChildren> getCategories() {
    List<CategoryChildren> categories = repositoryCategoryChildren.findAll();

    List<CategoryChildren> root = new ArrayList<>();
    
    Map<Long, CategoryChildren> all = new HashMap<>();

    for (CategoryChildren category : categories) {
      category.setChildren(null);
      all.put(category.getNo(), category);
    }

    for (CategoryChildren category : categories) {
      category.setChildren(new ArrayList<>());

      if(category.getParentNo() == null){
        root.add(category);
      }else{
        CategoryChildren parent = all.get(category.getParentNo());

        List<CategoryChildren> childrens = parent.getChildren();

        if(childrens == null){
          childrens = new ArrayList<>();
        }
        childrens.add(category);
      }
    }
    
    return root;
  }
    
  public CategoryParent getCategory(String id){
    Long lId = Long.valueOf(id);
    Optional<CategoryParent> opCategory =  repositoryCategoryParent.findById(lId);

    if(opCategory.isPresent()){
      return opCategory.get();
    }

    return null;
  }

  public List<CategoryParent> getCategoriess() {
    return repositoryCategoryParent.findAll();
  }

}