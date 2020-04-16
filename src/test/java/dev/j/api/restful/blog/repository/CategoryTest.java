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

    private Category first;
    private Category second;
    private Category third;

    @BeforeEach
    public void before(){

        for(int i = 1; i <= 10; i++){
            first = new Category();
            String fTitle = i + "_f";
            first.setTitle(fTitle);
            repositoryCategory.save(first);

            for(int j = 1; j <= 5; j++){
                second = new Category();
                String sTitle = fTitle + "_" + j + "_s";
                second.setTitle(sTitle);
                second.setParent(first);
                second.setParentNo(first.getNo());

                repositoryCategory.save(second);

                for(int c = 1; c <= 5; c++){
                    String tTitle = sTitle + "_"  + c + "_t";
                    third = new Category();
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