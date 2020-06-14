package dev.j.api.restful.blog.service;

import dev.j.api.restful.common.component.ComponentEncrypt;
import dev.j.api.restful.common.component.ComponentFileUpload;
import dev.j.api.restful.common.component.ComponentJwtToken;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {

    @Autowired
    protected ComponentJwtToken componentJwtToken;

    @Autowired
    protected ComponentEncrypt componentEncrypt;

    @Autowired
    protected ComponentFileUpload componentFileUpload;

    protected Boolean isNullOrEmpty(String str){
      return str == null || str.isEmpty();
    }

    protected Boolean isNullOrEmpty(String[] str){
      return str == null || str.length == 0;
    }

    protected Boolean isNotNullOrEmpty(String[] str){
      return !isNullOrEmpty(str);
    }

    protected Boolean isNotNullOrEmpty(String str){
      return !isNullOrEmpty(str);
    }
    
}