package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DataJpaTest
public class PostTest {

    @Autowired
    private RepositoryPost repositoryPost;

    @Test
    public void whenFindById(){
        Post firPost = new Post();
        firPost.setSubject("first");

        repositoryPost.save(firPost);
    }
    
}