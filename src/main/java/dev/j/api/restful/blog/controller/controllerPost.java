package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServicePost;
import dev.j.api.restful.blog.vo.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller Post")
@RestController
@RequestMapping(value = "/post")
public class controllerPost {

    @Autowired
    private ServicePost servicePost;
    
    @ApiOperation(
        value = "포스트 요청",
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
    @GetMapping
    public ResponseEntity<Page<Post>> getPosts(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "20")
        String pageSize) {
            
        return new ResponseEntity<Page<Post>>(servicePost.getPosts(pageNo, pageSize), HttpStatus.OK);
    }


    @ApiOperation(
        value = "포스트 요청",
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
    @GetMapping("/{postNo}")
    public ResponseEntity<Post> getPost(
        @ApiParam(value = "페이지 번호") 
        @PathVariable(value = "postNo", required = true)
        String postNo) {
            
        return new ResponseEntity<Post>(servicePost.getPost(Long.parseLong(postNo)), HttpStatus.OK);
    }
    
    
}