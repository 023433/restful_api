package dev.j.api.restful.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controller Authorize")
@RestController
@RequestMapping(value = "/auth")
public class controllerAuthorize {
    
    @ApiOperation(
        value = "로그인",
        response = ResponseEntity.class
    )
    @GetMapping("/login")
    public ResponseEntity<String> login() {

        String jwtToken = "jwtToken";
            
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        
    }
    
}