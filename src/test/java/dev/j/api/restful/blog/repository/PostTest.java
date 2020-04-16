package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.repository.post.RepositoryContent;
import dev.j.api.restful.blog.repository.post.RepositoryPostCategory;
import dev.j.api.restful.blog.repository.post.RepositorySummary;
import dev.j.api.restful.blog.vo.Category;
import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.blog.vo.post.Content;
import dev.j.api.restful.blog.vo.post.Summary;
import dev.j.api.restful.blog.vo.post.category.PostCategory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {

    @Autowired
    private RepositoryPost repositoryPost;
    
    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositorySummary repositoryPostSummary;
    
    @Autowired
    private RepositoryCategory repositoryCategory;

    @Autowired
    private RepositoryPostCategory repositoryPostCategory;

    @Autowired
    private RepositoryContent repositoryContent;

    Post firstPost;
    Summary firstSummary;
    Category category;
    Content content;
    User user;

    List<Category> categories = new ArrayList<>();
    
    @BeforeEach
    public void before(){

        user = new User();
        
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPw("userPw");
        repositoryUser.save(user);


        for(int i = 1; i <= 10; i++){

            for(int j = 1; j <= 310; j++){
                category = repositoryCategory.findById((long) j).get();
                categories.add(category);

                String fTitle = i + "_post_" + j + "_cate";

                firstPost = new Post();
                firstPost.setSubject(fTitle);
                firstPost.setAuthor(user.getUserId());
                firstPost.setViewCount(1);
                // firstPost.getCategories().add(category);
                
                repositoryPost.save(firstPost);



                firstSummary = new Summary();
                firstSummary.setPost(firstPost);
                firstSummary.setPostNo(firstPost.getNo());
                firstSummary.setSummary(fTitle + j + "_summary");
                firstSummary.setThumbnail(fTitle + j + "_thumbnail");
        
                repositoryPostSummary.save(firstSummary);

                content = new Content();
                content.setPost(firstPost);
                content.setPostNo(firstPost.getNo());
                content.setContent(fTitle + j + "_content");
                content.setMainImage(fTitle + j + "_mainimg");

                repositoryContent.save(content);


                PostCategory postCatogory = new PostCategory();

                postCatogory.setPostNo(firstPost.getNo());
                postCatogory.setCategoryNo(category.getNo());

                repositoryPostCategory.save(postCatogory);

            }
        }


        
    }



}