package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServiceAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller Authorize")
@RestController
@RequestMapping(value = "/auth")
public class ControllerAuthorize {

    @Autowired
    private ServiceAuthorize serviceAuthorize;
    
    @ApiOperation(
        value = "로그인",
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
    @PostMapping("/login")
    public ResponseEntity<String> login(
        @ApiParam(value = "사용자 아이디", required = true) 
        @RequestParam(value = "userId", required = true)
        String userId, 
        
        @ApiParam(value = "사용자 비밀번호", required = true) 
        @RequestParam(value = "userPwd", required = true)
        String userPwd) {

        String jwtToken = serviceAuthorize.getJwtToken(userId, userPwd);
            
        HttpStatus http = HttpStatus.BAD_REQUEST;

        if(jwtToken != null){
            http = HttpStatus.OK;
        }

        return new ResponseEntity<>(jwtToken, http);
        
    }
    
}