package dev.j.api.restful.common.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapperDbStatus {
  List<Map<String, Object>> getDbTableStatus();
}