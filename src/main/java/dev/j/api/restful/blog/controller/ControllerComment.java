package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServiceComment;
import dev.j.api.restful.blog.vo.post.comment.Comment;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller Comment")
@RestController
public class ControllerComment {

    @Autowired
    private ServiceComment serviceComment;

    @ApiOperation(
        value = "댓글 목록 요청",
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
    @GetMapping("/comments/{postNo}")
    public ResponseEntity<Page<Comment>> getComments(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize,
        
        @ApiParam(value = "포스트 번호", required = true) 
        @PathVariable(value = "postNo", required = true) 
        String postNo) {
            
        return new ResponseEntity<Page<Comment>>(serviceComment.getComments(postNo, pageNo, pageSize), HttpStatus.OK);
    }
}