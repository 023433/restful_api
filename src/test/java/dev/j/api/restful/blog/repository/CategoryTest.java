package dev.j.api.restful.blog.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.j.api.restful.blog.vo.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private RepositoryCategory repositoryCategory;

    private Category saved;
    private Category secondChild;
    private Category thirdChild;

    @BeforeEach
    public void before(){
        Category category = new Category();
        category.setTitle("first");


        Category secondCategory = new Category();
        secondCategory.setTitle("second");
        secondCategory.setParentNo(category);

        Category thirdCategory = new Category();
        thirdCategory.setTitle("third");
        thirdCategory.setParentNo(secondCategory);


        saved = repositoryCategory.save(category);
        secondChild = repositoryCategory.save(secondCategory);
        thirdChild = repositoryCategory.save(thirdCategory);
    }

    @Test
    public void oneDepth(){
        assertEquals(saved.getParentNo(), null);
    }

    @Test
    public void twoDepth(){
        assertEquals(secondChild.getParentNo(), saved);
    }

    @Test
    public void threeDepth(){
        assertEquals(saved, secondChild.getParentNo());
        assertEquals(secondChild, thirdChild.getParentNo());
    }

}