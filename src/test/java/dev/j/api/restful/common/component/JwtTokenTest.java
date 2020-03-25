package dev.j.api.restful.common.component;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import dev.j.api.restful.Application;

@SpringBootTest(classes = Application.class)
public class JwtTokenTest {

    @Autowired
    private ComponentJwtToken componentJwtToken;

    @Value("${ENC_PWD}")
    private String secretKey;
    
    private String jwtToken;
    
    @BeforeEach
    public void before(){
        jwtToken = componentJwtToken.createToken("userId", null);
        System.out.println(jwtToken);
    }

    @Test
    public void validToken(){
        assertTrue(componentJwtToken.isValidToken(jwtToken));
    }

    @Test
    public void invalidToken(){
        assertFalse(componentJwtToken.isValidToken(jwtToken + "aaa"));
    }
}