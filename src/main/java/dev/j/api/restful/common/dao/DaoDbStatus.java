package dev.j.api.restful.common.dao;

import dev.j.api.restful.common.mapper.MapperDbStatus;
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
    return mapperDbStatus.getDbTableStatus();
  }

}