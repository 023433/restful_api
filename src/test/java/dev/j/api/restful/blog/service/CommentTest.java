package dev.j.api.restful.blog.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.comment.Comment;
import dev.j.api.restful.blog.vo.post.comment.CommentGuest;
import dev.j.api.restful.blog.vo.post.comment.CommentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class CommentTest {

    @Autowired
    private ServiceComment serviceComment;

    @Autowired
    private ServicePost servicePost;

    @Autowired
    private ServiceUser serviceUser;

    User user = new User();
    Post post = new Post();
    Comment comment = new Comment();
    Comment commentUser = new Comment();
    Comment commentGuest = new Comment();

    @BeforeEach
    public void before(){
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPw("userPw");
        serviceUser.save(user);

        post = new Post();

        post.setSubject("subject");
        post.setPublish(true);
        post.setAuthor(user.getUserId());

        servicePost.savePost(post);

        comment = new Comment();
        CommentGuest guest = new CommentGuest();
        guest.setName("name");
        guest.setPw("pw");

        comment.setPost(post);
        comment.setPostNo(post.getNo());
        comment.setGuest(guest);

        commentGuest = serviceComment.saveCommentWithGuest(comment);

        comment = new Comment();
        CommentUser cuser = new CommentUser();
        cuser.setAuthor(user.getUserId());
        cuser.setUser(user);

        comment.setPost(post);
        comment.setPostNo(post.getNo());
        comment.setAuth(cuser);

        commentUser = serviceComment.saveCommentWithUser(comment);
    }
    
    @Test
    public void getComments(){
        Page<Comment> comments = serviceComment.getComments("0", "10");
        assertNotNull(comments.getContent());
    }

    @Test
    public void getCommentUserNotNull(){
        System.out.println(commentUser);
        assertNotNull(commentUser.getAuth());
    }

    @Test
    public void getComment(){
        Page<Comment> comments = serviceComment.getComments(String.valueOf(post.getNo()), "0", "10");
        System.out.println(comments);
        assertNotNull(comments.getContent());

    }

}