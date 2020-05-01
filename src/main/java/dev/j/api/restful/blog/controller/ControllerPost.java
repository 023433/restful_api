package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServicePost;
import dev.j.api.restful.blog.vo.post.Content;
import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.summary.SummaryCategory;
import dev.j.api.restful.blog.vo.post.summary.SummaryTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller Post")
@RestController
public class ControllerPost {

    @Autowired
    private ServicePost servicePost;



    @ApiOperation(
        value = "포스트 최신글 요청",
        response = ResponseEntity.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "X-Auth-Token", 
            required = false, 
            paramType = "header", 
            dataTypeClass = String.class
        ) 
    })
    @GetMapping("/posts/newest/{category}")
    public ResponseEntity<Page<Post>> getPostsNewestCategory(
        @ApiParam(value = "카테고리 번호", required = true) 
        @PathVariable("category") 
        String category,

        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "5")
        String pageSize) {
        return new ResponseEntity<Page<Post>>(servicePost.getPostsNewestCategory(category, pageNo, pageSize), HttpStatus.OK);
    }


    @ApiOperation(
        value = "포스트 최신글 요청",
        response = ResponseEntity.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "X-Auth-Token", 
            required = false, 
            paramType = "header", 
            dataTypeClass = String.class
        ) 
    })
    @GetMapping("/posts/newest")
    public ResponseEntity<Page<Post>> getPostsNewest() {
        return new ResponseEntity<Page<Post>>(servicePost.getPostsNewest(), HttpStatus.OK);
    }


    @ApiOperation(
        value = "포스트 요약 정보 요청",
        response = ResponseEntity.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "X-Auth-Token", 
            required = false, 
            paramType = "header", 
            dataTypeClass = String.class
        ) 
    })
    @GetMapping("/posts/summary")
    public ResponseEntity<Page<SummaryCategory>> getPostsSummary(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize) {
            
        return new ResponseEntity<Page<SummaryCategory>>(servicePost.getPostsSummary(pageNo, pageSize), HttpStatus.OK);
    }


    @ApiOperation(
        value = "포스트 요약 정보 요청",
        response = ResponseEntity.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "X-Auth-Token", 
            required = false, 
            paramType = "header", 
            dataTypeClass = String.class
        ) 
    })
    @GetMapping("/posts/summary/category")
    public ResponseEntity<Page<SummaryCategory>> getPostsSummaryWithCategory(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize,
        
        @ApiParam(value = "카테고리 이름 : 리스트 형태, 상위에서 하위 순", required = true) 
        @RequestParam(value = "categories", required = true)
        List<String> categories) {
            
        return new ResponseEntity<Page<SummaryCategory>>(servicePost.getPostsSummaryWithCategory(pageNo, pageSize, categories), HttpStatus.OK);
    }


    @ApiOperation(
        value = "포스트 요약 정보 요청",
        response = ResponseEntity.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "X-Auth-Token", 
            required = false, 
            paramType = "header", 
            dataTypeClass = String.class
        ) 
    })
    @GetMapping("/posts/summary/tag/{tag}")
    public ResponseEntity<Page<SummaryTag>> getPostsSummaryWithTag(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize,

        @ApiParam(value = "태그명", required = true) 
        @PathVariable(value = "tag", required = true)
        String tag) {
            
        return new ResponseEntity<Page<SummaryTag>>(servicePost.getPostsSummaryWithTag(pageNo, pageSize, tag), HttpStatus.OK);
    }


    @ApiOperation(
        value = "포스트 내용 요청",
        response = ResponseEntity.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "X-Auth-Token", 
            required = false, 
            paramType = "header", 
            dataTypeClass = String.class
        ) 
    })
    @GetMapping("/post/content/{postNo}")
    public ResponseEntity<Content> getPost(
        @ApiParam(value = "포스트 번호", required = true) 
        @PathVariable(value = "postNo", required = true)
        String postNo) {
            
        return new ResponseEntity<Content>(servicePost.getPost(Long.parseLong(postNo)), HttpStatus.OK);
    }
    
    
}