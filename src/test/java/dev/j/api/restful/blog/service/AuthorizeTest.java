package dev.j.api.restful.blog.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.j.api.restful.blog.repository.RepositoryUser;
import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.common.component.ComponentEncrypt;
import dev.j.api.restful.common.component.ComponentJwtToken;
import dev.j.api.restful.common.enums.EnumRole;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AuthorizeTest {

    @Autowired
    private ComponentJwtToken componentJwtToken;

    @Autowired
    private ComponentEncrypt componentEncrypt;

    @Autowired
    private ServiceAuthorize serviceAuthorize;

    @Autowired
    private RepositoryUser repositoryUser; 

    User admin = new User();
    User user = new User();

    @BeforeEach
    public void bofore(){
        List<String> roles = new ArrayList<>();
        
        roles.add(EnumRole.ADMIN.label());
        roles.add(EnumRole.MANAGER.label());


        admin.setRoles(roles);
        admin.setUserId("admin");
        admin.setUserName("userName");
        admin.setUserPw(componentEncrypt.encrypt("userPw"));

        roles = new ArrayList<>();
        roles.add(EnumRole.MANAGER.label());


        user.setRoles(roles);
        user.setUserId("manager");
        user.setUserName("userName2");
        user.setUserPw(componentEncrypt.encrypt("userPw"));

        repositoryUser.save(admin);
        repositoryUser.save(user);
    }

    @AfterEach
    public void after(){
        // repositoryUser.delete(admin);
        // repositoryUser.delete(user);
    }

    @Test
    public void createToken(){
        String jwtToken = serviceAuthorize.createJwtToken("admin");

        assertTrue(componentJwtToken.isValidToken(jwtToken));
    }

    @Test
    @Transactional
    public void isAdmin(){
        String jwtToken = serviceAuthorize.getJwtToken("admin", "userPw");
        List<String> roles = componentJwtToken.getUserRole(jwtToken);

        assertTrue(roles.contains(EnumRole.ADMIN.label()));
    }

    @Test
    @Transactional
    public void isNull(){
        String jwtToken = serviceAuthorize.getJwtToken("admin", "admin");
        List<String> roles = componentJwtToken.getUserRole(jwtToken);

        assertNull(roles);
    }

    @Test
    @Transactional
    public void isNotAdmin(){
        String jwtToken = serviceAuthorize.getJwtToken("manager", "userPw");
        List<String> roles = componentJwtToken.getUserRole(jwtToken);

        assertFalse(roles.contains(EnumRole.ADMIN.label()));
    }
    
}