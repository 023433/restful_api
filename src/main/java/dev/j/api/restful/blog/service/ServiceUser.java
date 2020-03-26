package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.RepositoryUser;
import dev.j.api.restful.blog.vo.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {

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
    
}