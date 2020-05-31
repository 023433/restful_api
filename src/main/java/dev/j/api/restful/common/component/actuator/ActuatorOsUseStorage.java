package dev.j.api.restful.common.component.actuator;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RestControllerEndpoint(id = "sysinfo")
public class ActuatorOsUseStorage {
    
    @Autowired
    private ComponentStorage componentStorage;

    @GetMapping("/storage")
    public @ResponseBody ResponseEntity<String> getServerInfo(HttpServletRequest request) {
        System.out.println(request.getParameterValues("name"));

        String[] dir = request.getParameterValues("dir");

        if(dir == null){
            return  new ResponseEntity<String>("ss", HttpStatus.BAD_REQUEST);
        }
        
        for (String str : dir) {
            System.out.println(componentStorage.getDirSize(str));
        }

        return  new ResponseEntity<String>("REST end point", HttpStatus.OK);
    }

}