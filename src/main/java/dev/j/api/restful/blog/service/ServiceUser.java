package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.RepositoryUser;
import dev.j.api.restful.blog.vo.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser extends AbstractService {

  @Autowired
  private RepositoryUser repositoryUser;

  public List<String> getUserRoles(String userId){

    Optional<User> optionalUser = repositoryUser.findById(userId);

    if(optionalUser.isPresent()){
      User user = optionalUser.get();

      return user.getRoles();
    }

    return null;
  }

  public void save(User user) {
    String userPw = user.getUserPw();
    userPw = componentEncrypt.encrypt(userPw);
    user.setUserPw(userPw);
    
    repositoryUser.save(user);
  }

  public Page<User> getUsers(String pageNo, String pageSize) {
    int page = Integer.parseInt(pageNo);
    int size = Integer.parseInt(pageSize);
    Sort sort = Sort.by(Order.desc("createDate"));
    
    Pageable pageable = PageRequest.of(page, size, sort);

    return repositoryUser.findAll(pageable);
  }
    
}