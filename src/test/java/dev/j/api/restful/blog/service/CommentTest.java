package dev.j.api.restful.blog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        comment.setContent("content");
        commentGuest = serviceComment.saveCommentWithGuest(comment);

        comment = new Comment();
        CommentUser cuser = new CommentUser();
        cuser.setAuthor(user.getUserId());
        cuser.setUser(user);

        comment.setPost(post);
        comment.setPostNo(post.getNo());
        comment.setAuth(cuser);
        comment.setContent("content");

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

    @Test
    public void saveComment(){
        Comment comment = new Comment();

        comment.setPostNo(post.getNo());
        comment.setContent("content");
        
        serviceComment.saveComment(comment);

        Long no = comment.getNo();
        System.out.println(no);
        assertNotEquals(no, 0L);
    }


    @Test
    public void saveCommentChild(){
        Comment comment = new Comment();

        comment.setPostNo(post.getNo());
        comment.setContent("content");

        serviceComment.saveComment(comment);

        Long no = comment.getNo();

        Comment child1 = new Comment();

        child1.setContent("child_content_1");
        child1.setParentNo(no);
        child1.setPostNo(post.getNo());

        serviceComment.saveComment(child1);

        Long parentNo = child1.getParentNo();
        assertEquals(parentNo, no);

        Comment child2 = new Comment();

        child2.setContent("child_content_2");
        child2.setParentNo(no);
        child2.setPostNo(post.getNo());

        serviceComment.saveComment(child2);

        Long parentNo2 = child2.getParentNo();
        assertEquals(parentNo2, no);


        Comment child1_1 = new Comment();

        child1_1.setContent("child_content_1_1");
        child1_1.setParentNo(child1.getNo());
        child1_1.setPostNo(post.getNo());

        serviceComment.saveComment(child1_1);

        Long parentNo1_1 = child1_1.getParentNo();
        assertEquals(parentNo1_1, child1.getNo());

        Comment child1_1_1 = new Comment();

        child1_1_1.setContent("child_content_1_1_1");
        child1_1_1.setParentNo(child1_1.getNo());
        child1_1_1.setPostNo(post.getNo());

        serviceComment.saveComment(child1_1_1);

        Long parentNo1_1_1 = child1_1_1.getParentNo();
        assertEquals(parentNo1_1_1, child1_1.getNo());

        Comment child2_1 = new Comment();

        child2_1.setContent("child_content_2_1");
        child2_1.setParentNo(child2.getNo());
        child2_1.setPostNo(post.getNo());

        serviceComment.saveComment(child2_1);

        Long parentNo2_1 = child2_1.getParentNo();
        assertEquals(parentNo2_1, child2.getNo());


        Comment child1_1_1_1 = new Comment();

        child1_1_1_1.setContent("child_content_1_1_1_1");
        child1_1_1_1.setParentNo(child1_1_1.getNo());
        child1_1_1_1.setPostNo(post.getNo());

        serviceComment.saveComment(child1_1_1_1);

        Long parentNo1_1_1_1 = child1_1_1_1.getParentNo();
        assertEquals(parentNo1_1_1_1, child1_1_1.getNo());



        Comment child2_1_1 = new Comment();

        child2_1_1.setContent("child_content_2_1_1");
        child2_1_1.setParentNo(child2_1.getNo());
        child2_1_1.setPostNo(post.getNo());

        serviceComment.saveComment(child2_1_1);

        Long parentNo2_1_1 = child2_1_1.getParentNo();
        assertEquals(parentNo2_1_1, child2_1.getNo());


        Comment child3 = new Comment();

        child3.setContent("child_content_3");
        child3.setParentNo(no);
        child3.setPostNo(post.getNo());

        serviceComment.saveComment(child3);

        Long parentNo3 = child3.getParentNo();
        assertEquals(parentNo3, no);


        Comment child1_1_2 = new Comment();

        child1_1_2.setContent("child_content_1_1_2");
        child1_1_2.setParentNo(child1_1.getNo());
        child1_1_2.setPostNo(post.getNo());

        serviceComment.saveComment(child1_1_2);

        Long parentNo1_1_2 = child1_1_2.getParentNo();
        assertEquals(parentNo1_1_2, child1_1.getNo());


        Comment child2_2 = new Comment();

        child2_2.setContent("child_content_2_2");
        child2_2.setParentNo(child2.getNo());
        child2_2.setPostNo(post.getNo());

        serviceComment.saveComment(child2_2);

        Long parentNo2_2 = child2_2.getParentNo();
        assertEquals(parentNo2_2, child2.getNo());

    }



    @Test
    public void saveCommentChildOne(){
        Comment comment = new Comment();

        comment.setPostNo(post.getNo());
        comment.setContent("content");

        serviceComment.saveComment(comment);

        Long no = comment.getNo();

        Comment child1 = new Comment();

        child1.setContent("child_content_1");
        child1.setParentNo(no);
        child1.setPostNo(post.getNo());

        serviceComment.saveComment(child1);

        assertEquals(2, child1.getOrderNo());

    }

    @Test
    public void saveCommentChildTwo(){
        Comment comment = new Comment();

        comment.setPostNo(post.getNo());
        comment.setContent("content");

        serviceComment.saveComment(comment);

        Long no = comment.getNo();

        Comment child1 = new Comment();

        child1.setContent("child_content_1");
        child1.setParentNo(no);
        child1.setPostNo(post.getNo());

        serviceComment.saveComment(child1);

        Comment child1_2 = new Comment();

        child1_2.setContent("child_content_1_2");
        child1_2.setParentNo(no);
        child1_2.setPostNo(post.getNo());

        serviceComment.saveComment(child1_2);

        assertEquals(3, child1_2.getOrderNo());

    }


    @Test
    public void saveCommentChildThree(){
        Comment comment = new Comment();

        comment.setPostNo(post.getNo());
        comment.setContent("content");

        serviceComment.saveComment(comment);

        Long no = comment.getNo();

        Comment child1 = new Comment();

        child1.setContent("child_content_1");
        child1.setParentNo(no);
        child1.setPostNo(post.getNo());

        serviceComment.saveComment(child1);

        Comment child1_2 = new Comment();

        child1_2.setContent("child_content_1_2");
        child1_2.setParentNo(child1.getNo());
        child1_2.setPostNo(post.getNo());

        serviceComment.saveComment(child1_2);

        assertEquals(3, child1_2.getOrderNo());

        Comment child2 = new Comment();

        child2.setContent("child_content_2");
        child2.setParentNo(no);
        child2.setPostNo(post.getNo());

        serviceComment.saveComment(child2);

        assertEquals(4, child2.getOrderNo());


    }


}