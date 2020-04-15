package dev.j.api.restful.blog.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.blog.vo.post.Content;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {

    @Autowired
    private ServicePost servicePost;

    @Autowired
    private ServiceUser serviceUser;

    User user = new User();
    Long postNo = 0L;

    @BeforeEach
    public void before(){
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPw("userPw");
        serviceUser.save(user);

        Post post = new Post();
        Content content = new Content();
        
        

        post.setSubject("subject");
        post.setPublish(true);
        post.setAuthor(user.getUserId());

        servicePost.savePost(post);

        content.setPost(post);
        content.setContent("content");
        content.setMainImage("mainImage");
        content.setPostNo(post.getNo());
        servicePost.saveContent(content);

        postNo = post.getNo();
    }
    

    @Test
    @Transactional
    public void savePost(){
        Post post = new Post();
        

        post.setSubject("subject");
        post.setPublish(true);
        post.setAuthor(user.getUserId());

        servicePost.savePost(post);

        assertNotNull(post.getNo());

    }

}