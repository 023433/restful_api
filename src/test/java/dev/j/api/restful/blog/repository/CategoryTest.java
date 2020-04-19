package dev.j.api.restful.blog.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.j.api.restful.blog.repository.category.RepositoryCategoryParent;
import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private RepositoryCategoryParent repositoryCategory;

    private CategoryParent first;
    private CategoryParent second;
    private CategoryParent third;

    @BeforeEach
    public void before(){

        for(int i = 1; i <= 2; i++){
            first = new CategoryParent();
            String fTitle = i + "_f";
            first.setTitle(fTitle);
            repositoryCategory.save(first);

            for(int j = 1; j <= 2; j++){
                second = new CategoryParent();
                String sTitle = fTitle + "_" + j + "_s";
                second.setTitle(sTitle);
                second.setParent(first);
                second.setParentNo(first.getNo());

                repositoryCategory.save(second);

                for(int c = 1; c <= 2; c++){
                    String tTitle = sTitle + "_"  + c + "_t";
                    third = new CategoryParent();
                    third.setTitle(tTitle);
                    third.setParent(second);
                    third.setParentNo(second.getNo());
                    repositoryCategory.save(third);
                }
            }
        }
    }



    @Test
    public void oneDepth(){
        assertEquals(first.getParentNo(), null);
    }

    @Test
    public void twoDepth(){
        assertEquals(second.getParent(), first);
    }

    @Test
    public void threeDepth(){
        assertEquals(first, second.getParent());
        assertEquals(second, third.getParent());
    }

}