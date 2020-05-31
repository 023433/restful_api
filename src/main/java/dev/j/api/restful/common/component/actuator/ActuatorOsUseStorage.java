package dev.j.api.restful.common.component.actuator;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
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

    @ApiOperation(
        value = "사용량 요청",
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
    public @ResponseBody ResponseEntity<Map<String, String>> getServerInfo(
        @ApiParam(value = "폴더명", required = true) 
        @RequestParam(value = "dir", required = true)
        String[] dir) {

        Map<String, String> map = new HashMap<>();

        if(dir == null){
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
        }

        for (String str : dir) {
            map.put(str, componentStorage.getDirSize(str));
        }

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }

}