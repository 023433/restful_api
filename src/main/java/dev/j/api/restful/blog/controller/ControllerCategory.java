package dev.j.api.restful.blog.controller;

import dev.j.api.restful.blog.service.ServiceCategory;
import dev.j.api.restful.blog.vo.post.category.CategoryChildren;
import dev.j.api.restful.blog.vo.post.category.CategoryParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller Category")
@RestController
public class ControllerCategory {

  @Autowired
  private ServiceCategory serviceCategory;

  @ApiOperation(
    value = "카테고리 목록 요청",
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
  @GetMapping("/categories")
  public ResponseEntity<List<CategoryChildren>> getCategories() {
    return new ResponseEntity<List<CategoryChildren>>(serviceCategory.getCategories(), HttpStatus.OK);
  }


  @ApiOperation(
    value = "카테고리 목록 요청",
    notes = "등록된 포스트의 갯수를 포함한 카테고리 정보 반환",
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
  @GetMapping("/categories/count")
  public ResponseEntity<List<CategoryChildren>> getCategoriesWithCount() {
    return new ResponseEntity<List<CategoryChildren>>(serviceCategory.getCategoriesWithCount(), HttpStatus.OK);
  }


  @ApiOperation(
    value = "카테고리 요청",
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
  @GetMapping("/category/{id}")
  public ResponseEntity<CategoryParent> getCategory(
    @PathVariable("id")
    String id
  ) {
          
    return new ResponseEntity<CategoryParent>(serviceCategory.getCategory(id), HttpStatus.OK);
  }
}