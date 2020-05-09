package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServiceComment;
import dev.j.api.restful.blog.vo.post.comment.Comment;
import dev.j.api.restful.blog.vo.post.comment.CommentGuest;
import dev.j.api.restful.blog.vo.post.comment.CommentPostLoad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Page<CommentPostLoad>> getComments(
        @ApiParam(value = "페이지 번호") 
        @RequestParam(value = "pageNo", defaultValue = "0")
        String pageNo, 
        
        @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
        @RequestParam(value = "pageSize", defaultValue = "10")
        String pageSize,
        
        @ApiParam(value = "포스트 번호", required = true) 
        @PathVariable(value = "postNo", required = true) 
        String postNo) {
            
        return new ResponseEntity<Page<CommentPostLoad>>(serviceComment.getComments(postNo, pageNo, pageSize), HttpStatus.OK);
    }

    @ApiOperation(
        value = "최신 댓글 목록 요청",
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
    @GetMapping("/comments/newest")
    public ResponseEntity<Page<CommentPostLoad>> getCommentsNewest() {
        return new ResponseEntity<Page<CommentPostLoad>>(serviceComment.getCommentsNewest(), HttpStatus.OK);
    }



    @ApiOperation(
        value = "대댓글 등록",
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
    @PostMapping("/comment/reply")
    public ResponseEntity<Comment> commentReply(
        HttpServletRequest request,

        @ApiParam(value = "이름", required = false) 
        @RequestParam(value = "name", required = false)
        String name, 
        
        @ApiParam(value = "비밀번호", required = false) 
        @RequestParam(value = "password", required = false)
        String password,

        @ApiParam(value = "댓글 번호", required = true) 
        @RequestParam(value = "no", required = true)
        String no,
        
        @ApiParam(value = "내용", required = true) 
        @RequestParam(value = "content", required = true)
        String content,
        
        @ApiParam(value = "비공개 여부", required = true) 
        @RequestParam(value = "secret", required = true)
        String secret) {

            

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setParentNo(Long.parseLong(no));
        comment.setSecret(Boolean.parseBoolean(secret));

        if(!name.isEmpty()){
            CommentGuest guest = new CommentGuest();
            guest.setName(name);
            guest.setPw(password);

            comment.setGuest(guest);
        }

        return new ResponseEntity<Comment>(serviceComment.saveComment(request, comment), HttpStatus.OK);
        
    }


    @ApiOperation(
        value = "댓글 수정",
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
    @PutMapping("/comment")
    public ResponseEntity<Comment> commentModify(
        HttpServletRequest request,

        @ApiParam(value = "이름", required = false) 
        @RequestParam(value = "name", required = false)
        String name, 
        
        @ApiParam(value = "비밀번호", required = false) 
        @RequestParam(value = "password", required = false)
        String password,

        @ApiParam(value = "댓글 번호", required = true) 
        @RequestParam(value = "no", required = true)
        String no,
        
        @ApiParam(value = "내용", required = true) 
        @RequestParam(value = "content", required = true)
        String content,
        
        @ApiParam(value = "비공개 여부", required = true) 
        @RequestParam(value = "secret", required = true)
        String secret) {

            
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setNo(Long.parseLong(no));
        comment.setSecret(Boolean.parseBoolean(secret));

        if(!name.isEmpty()){
            CommentGuest guest = new CommentGuest();
            guest.setName(name);
            guest.setPw(password);

            comment.setGuest(guest);
        }

        return new ResponseEntity<Comment>(serviceComment.updateComment(request, comment), HttpStatus.OK);
        
    }


    @ApiOperation(
        value = "댓글 등록",
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
    @PostMapping("/comment")
    public ResponseEntity<Comment> commentInsert(
        HttpServletRequest request,

        @ApiParam(value = "이름", required = false) 
        @RequestParam(value = "name", required = false)
        String name, 
        
        @ApiParam(value = "비밀번호", required = false) 
        @RequestParam(value = "password", required = false)
        String password,

        @ApiParam(value = "글 번호", required = false) 
        @RequestParam(value = "postNo", required = false)
        String postNo,

        @ApiParam(value = "내용", required = true) 
        @RequestParam(value = "content", required = true)
        String content,
        
        @ApiParam(value = "비공개 여부", required = true) 
        @RequestParam(value = "secret", required = true)
        String secret) {

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPostNo(Long.parseLong(postNo));
        comment.setSecret(Boolean.parseBoolean(secret));

        if(!name.isEmpty()){
            CommentGuest guest = new CommentGuest();
            guest.setName(name);
            guest.setPw(password);

            comment.setGuest(guest);
        }

        return new ResponseEntity<Comment>(serviceComment.saveComment(request, comment), HttpStatus.OK);
        
    }


    @ApiOperation(
        value = "댓글 삭제",
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
    @DeleteMapping("/comment")
    public ResponseEntity<String> commentDelete(
        @ApiParam(value = "비밀번호", required = false) 
        @RequestParam(value = "password", required = false)
        String password,

        @ApiParam(value = "댓글 번호", required = true) 
        @RequestParam(value = "no", required = true)
        String no) {


        System.out.println(no);
        System.out.println(password);
        HttpStatus http = HttpStatus.OK;


        return new ResponseEntity<>(http);
        
    }



    @ApiOperation(
        value = "댓글 요청",
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
    @GetMapping("/comment/{no}")
    public ResponseEntity<Comment> getComment(
        @ApiParam(value = "비밀번호", required = false) 
        @RequestParam(value = "password", required = false)
        String password,

        @ApiParam(value = "댓글 번호", required = true) 
        @PathVariable(value = "no", required = true)
        String no,

        HttpServletRequest request) {


        return new ResponseEntity<Comment>(serviceComment.getComment(request, no, password), HttpStatus.OK);
    }


}