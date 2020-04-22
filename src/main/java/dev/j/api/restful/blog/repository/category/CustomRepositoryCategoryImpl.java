package dev.j.api.restful.blog.repository.category;

import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomRepositoryCategoryImpl implements CustomRepositoryCategory {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CategoryParent findByCategories(List<String> categories) {
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryParent> query = criteriaBuilder.createQuery(CategoryParent.class);
        Root<CategoryParent> root = query.from(CategoryParent.class);
        int size = categories.size();

        Predicate[] predicates = new Predicate[size];
        predicates[0] = criteriaBuilder.equal(root.get("title"), categories.get(size-1));

        if(size > 1){
    
            Join<CategoryParent, CategoryParent> parent = null;

            for(int i = size-1; i >= 1; --i){
                
                if(parent == null){
                    parent = root.join("parent", JoinType.INNER);
                }else{
                    parent = parent.join("parent", JoinType.INNER);
                }

                predicates[size - i] = criteriaBuilder.equal(parent.get("title"), categories.get(i-1));
            }

        }

        query.where(predicates);

        return entityManager.createQuery(query).getSingleResult();
    }
}