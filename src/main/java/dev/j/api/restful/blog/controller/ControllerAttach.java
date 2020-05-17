package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServiceAttach;
import dev.j.api.restful.blog.vo.post.content.MainImage;
import dev.j.api.restful.blog.vo.post.summary.Thumbnail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "Controller Attach")
@RestController
@RequestMapping(value = "/attach")
public class ControllerAttach {
    
    @Autowired
    private ServiceAttach serviceAttach;


    @ApiOperation(
        value = "썸네일 이미지 업로드",
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
    @PreAuthorize ("hasRole('ROLE_ADMIN')")
    @PostMapping("/thumbnail")
    public ResponseEntity<Thumbnail> attachThumbnail(
        @ApiParam(value = "첨부파일(이미지)", required = true) 
        @RequestParam MultipartFile attachImage, 
        
        HttpServletRequest request) {
            
        return new ResponseEntity<Thumbnail>(serviceAttach.attachThumbnail(request, attachImage), HttpStatus.OK);
    }

    @ApiOperation(
        value = "메인 이미지 업로드",
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
    @PreAuthorize ("hasRole('ROLE_ADMIN')")
    @PostMapping("/mainimage")
    public ResponseEntity<MainImage> attachMainImage(
        @ApiParam(value = "첨부파일(이미지)", required = true) 
        @RequestParam MultipartFile attachImage, 
        
        HttpServletRequest request) {
            
        return new ResponseEntity<MainImage>(serviceAttach.attachMainImage(request, attachImage), HttpStatus.OK);
    }
}