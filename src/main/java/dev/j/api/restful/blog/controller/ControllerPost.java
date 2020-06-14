package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServicePost;
import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.PostCount;
import dev.j.api.restful.blog.vo.post.PostParam;
import dev.j.api.restful.blog.vo.post.content.Content;
import dev.j.api.restful.blog.vo.post.summary.SummaryCategory;
import dev.j.api.restful.blog.vo.post.summary.SummaryTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/posts/summary/search/{searchVal}")
    public ResponseEntity<Page<SummaryCategory>> getPostsSummaryWithSearch(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize,

        @ApiParam(value = "검색어", required = true) 
        @PathVariable(value = "searchVal", required = true)
        String searchVal) {
            
        return new ResponseEntity<Page<SummaryCategory>>(servicePost.getPostsSummaryWithSearch(pageNo, pageSize, searchVal), HttpStatus.OK);
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


    @ApiOperation(
        value = "포스트 일별 작성 정보 요청",
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
    @GetMapping("/post/count/{date}")
    public ResponseEntity<List<PostCount>> getPostCountGroupByCreateDate(
        @ApiParam(value = "검색 날짜 (yyyy-mm)", required = true) 
        @PathVariable(value = "date", required = true)
        String date) {
            
        return new ResponseEntity<List<PostCount>>(servicePost.getCountGroupByCreateDate(date), HttpStatus.OK);
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
    @GetMapping("/posts/summary/day/{date}")
    public ResponseEntity<Page<SummaryCategory>> getPostsByCreateDate(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize,

        @ApiParam(value = "검색 날짜 (yyyy-mm-dd)", required = true) 
        @PathVariable(value = "date", required = true)
        String date) {
            
        return new ResponseEntity<Page<SummaryCategory>>(servicePost.getPostsByCreateDate(pageNo, pageSize, date), HttpStatus.OK);
    }



    @ApiOperation(
        value = "포스트 입력",
        response = ResponseEntity.class
    )
    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "X-Auth-Token", 
            required = true, 
            paramType = "header", 
            dataTypeClass = String.class
        ) 
    })
    @PostMapping("/post")
    public ResponseEntity<String> addPost(
        HttpServletRequest request,

        @ApiParam(value = "포스트 입력 내용", required = true) 
        PostParam post) {

        if(servicePost.addPostValidate(request, post)){
            return new ResponseEntity<String>(servicePost.addPost(request, post), HttpStatus.OK);
        }

        return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
            
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
    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getPosts(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize) {
            
        return new ResponseEntity<Page<Post>>(servicePost.getPosts(pageNo, pageSize), HttpStatus.OK);
    }
    
    
}