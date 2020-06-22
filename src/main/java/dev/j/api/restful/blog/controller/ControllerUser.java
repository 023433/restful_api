package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServiceUser;
import dev.j.api.restful.blog.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller User")
@RestController
public class ControllerUser {

  @Autowired
  private ServiceUser serviceUser;
  

  @ApiOperation(
    value = "사용자 목록 요청",
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
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/admin/users")
  public ResponseEntity<Page<User>> getPostsByCreateDate(
    @ApiParam(value = "페이지 번호") 
    @RequestParam(value = "pageNo", defaultValue = "0")
    String pageNo, 
    
    @ApiParam(value = "한 페이지에 보여질 게시글 갯수") 
    @RequestParam(value = "pageSize", defaultValue = "10")
    String pageSize) {
        
    return new ResponseEntity<Page<User>>(serviceUser.getUsers(pageNo, pageSize), HttpStatus.OK);
  }
}