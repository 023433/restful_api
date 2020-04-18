package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.RepositoryCategory;
import dev.j.api.restful.blog.vo.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategory extends AbstractService {

    @Autowired
    private RepositoryCategory repositoryCategory;

	public List<Category> getCategories() {

		return repositoryCategory.findAll();
    }
    
    public Category getCategory(String id){
        Long lId = Long.valueOf(id);
        Optional<Category> opCategory =  repositoryCategory.findById(lId);

        if(opCategory.isPresent()){
            return opCategory.get();
        }

        return null;
    }

}