package dev.j.api.restful.blog.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.j.api.restful.blog.repository.post.RepositorySummary;
import dev.j.api.restful.blog.vo.Category;
import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.blog.vo.post.Summary;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {

    @Autowired
    private PreAuthorizedRepositoryPost repositoryPost;
    
    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositorySummary repositoryPostSummary;
    
    @Autowired
    private RepositoryCategory repositoryCategory;

    Post firstPost;
    Summary firstSummary;
    Category category;
    User user;

    List<Category> categories = new ArrayList<>();
    
    @BeforeEach
    public void before(){

        category = repositoryCategory.findById((long) 1).get();
        categories.add(category);

        user = new User();
        
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPw("userPw");
        repositoryUser.save(user);

        firstPost = new Post();
        firstPost.setSubject("first");
        firstPost.setAuthor(user);
        firstPost.setViewCount(1);
        firstPost.getCategories().add(category);
        
        repositoryPost.save(firstPost);
        System.out.println(firstPost);


        firstSummary = new Summary();

        firstSummary.setPost(firstPost);
        firstSummary.setSummary("summary");
        firstSummary.setThumbnail("thumbnail");

        repositoryPostSummary.save(firstSummary);
        
    }

    @Test
    public void firstPost(){
        assertEquals(firstPost.getNo(), firstPost.getNo());
    }

    @Test
    public void firstPostSummary(){
        Post post = repositoryPost.findById(firstPost.getNo()).get();

        Summary summary = post.getSummary();
        assertEquals(summary.getNo(), firstSummary.getNo());
    }

    
}