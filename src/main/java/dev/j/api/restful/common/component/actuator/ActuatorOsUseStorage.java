package dev.j.api.restful.common.component.actuator;


import dev.j.api.restful.common.dao.DaoDbStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RestControllerEndpoint(id = "sysinfo")
public class ActuatorOsUseStorage {
    
  @Autowired
  private ComponentStorage componentStorage;

  @Autowired
  private DaoDbStatus daoDbStatus;

  @ApiOperation(
    value = "폴더별 사용량 요청",
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
  @GetMapping("/storage")
  public @ResponseBody ResponseEntity<List<Map<String, Object>>> getServerInfo(
    @ApiParam(value = "폴더명", required = true) 
    @RequestParam(value = "dir", required = true)
    String[] dir) {

    List<Map<String, Object>> result = new ArrayList<>();

    if(dir == null){
      return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.BAD_REQUEST);
    }

    for (String str : dir) {
      Map<String, Object> tMap = new HashMap<>();
      tMap.put("size", componentStorage.getDirSize(str));
      tMap.put("name", str);

      result.add(tMap);
    }

    return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
  }


  @ApiOperation(
    value = "DB 사용량 요청",
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
  @GetMapping("/db")
  public @ResponseBody ResponseEntity<List<Map<String, Object>>> getDbTableStatus() {

    List<Map<String, Object>> map = daoDbStatus.getDbTableStatus();

    return new ResponseEntity<List<Map<String, Object>>>(map, HttpStatus.OK);
  }

}