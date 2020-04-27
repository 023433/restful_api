package dev.j.api.restful.blog.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.j.api.restful.blog.repository.post.RepositoryPost;
import dev.j.api.restful.blog.repository.post.comment.RepositoryComment;
import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.comment.Comment;
import dev.j.api.restful.blog.vo.post.comment.CommentUser;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class CommentTest {

    @Autowired
    private RepositoryComment repositoryComment;

    @Autowired
    private RepositoryPost repositoryPost;
    
    private Post post;

    @BeforeEach
    public void beforeEach(){
        post = repositoryPost.findById(1L).get();
    }

    @Test
    public void insertUser(){
        Comment comment = new Comment();

        User user = new User();
        user.setUserId("userId");
        CommentUser auth = new CommentUser();
        auth.setUser(user);

        comment.setPostNo(post.getNo());
        comment.setPost(post);

        Comment saved = repositoryComment.save(comment);

        assertNotNull(saved.getNo());
    }

}