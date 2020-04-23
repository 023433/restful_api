package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServiceTag;
import dev.j.api.restful.blog.vo.Tag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller Tag")
@RestController
public class ControllerTag {

    @Autowired
    private ServiceTag serviceTag;
   
    @ApiOperation(
        value = "태그 목록 요청",
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
    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getTags(){

        return  new ResponseEntity<List<Tag>>(serviceTag.getTags(), HttpStatus.OK);

    }
}