package dev.j.api.restful.common.dao;

import dev.j.api.restful.common.mapper.MapperDbStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DaoDbStatus {

  @Autowired
  private MapperDbStatus mapperDbStatus;

  public List<Map<String, Object>> getDbTableStatus(){

    List<Map<String, Object>> temp = mapperDbStatus.getDbTableStatus();

    List<Map<String, Object>> result = new ArrayList<>();

    for (Map<String, Object> map : temp) {
      int data = 0;
      int index = 0;

      Map<String, Object> tMap = new HashMap<>();

      if(map.containsKey("Data_length")){
        data = Integer.parseInt(map.get("Data_length").toString());
      }

      if(map.containsKey("Index_length")){
        index = Integer.parseInt(map.get("Index_length").toString());
      }
      
      tMap.put("size", index + data);

      if(map.containsKey("Name")){
        tMap.put("name", map.get("Name"));
      }

      result.add(tMap);

    }

    return result;
  }

}