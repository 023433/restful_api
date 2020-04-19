package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.category.RepositoryCategoryChildren;
import dev.j.api.restful.blog.repository.category.RepositoryCategoryParent;
import dev.j.api.restful.blog.vo.post.category.CategoryChildren;
import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategory extends AbstractService {


    @Autowired
    private RepositoryCategoryChildren repositoryCategoryChildren;

    @Autowired
    private RepositoryCategoryParent repositoryCategoryParent;


	public List<CategoryChildren> getCategories() {
        List<CategoryChildren> categories = repositoryCategoryChildren.findAll();

        List<CategoryChildren> root = new ArrayList<>();
        
        for (CategoryChildren category : categories) {
            CategoryChildren parent = category.getParent();

            if(parent == null){
                root.add(category);
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