package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.RepositoryUser;
import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.common.enums.EnumRole;
import dev.j.api.restful.common.property.PropertyJwtToken;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAuthorize extends AbstractService {

  @Autowired
  private RepositoryUser repositoryUser;

  public String createJwtToken(String userId) {
    return componentJwtToken.createToken(userId);
  }

  public String getJwtToken(String userId, String userPw) {

    if(userId.isEmpty() || userPw.isEmpty()){
      return null;
    }
    
    Optional<User> userOp = repositoryUser.findById(userId);
    
    if( !userOp.isPresent() ){
      return null;
    }

    User user = userOp.get();

    String savedUserPw = user.getUserPw();

    if(componentEncrypt.matches(userPw, savedUserPw)){
      List<String> roles = user.getRoles();
      return componentJwtToken.createToken(userId, roles);
    }

    return null;
  }

  public boolean validateAdmin(HttpServletRequest request) {

    String jwtToken = request.getHeader(PropertyJwtToken.STR_TOKEN);
    String userId = componentJwtToken.getUserId(jwtToken);
    
    Optional<User> userOp = repositoryUser.findById(userId);
    
    if( !userOp.isPresent() ){
      return false;
    }
    
    User user = userOp.get();
    List<String> roles = user.getRoles();

    return roles.contains(EnumRole.ADMIN.label());
  }

}
