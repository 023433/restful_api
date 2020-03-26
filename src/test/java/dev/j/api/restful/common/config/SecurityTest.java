package dev.j.api.restful.common.config;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.j.api.restful.Application;
import dev.j.api.restful.common.component.ComponentJwtToken;
import dev.j.api.restful.common.property.PropertyJwtToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ComponentJwtToken componentJwtToken;

    @Test
    public void accessDenied() throws Exception{
        mvc.perform(
            MockMvcRequestBuilders
                .get("/apiAccessHistories")
                .accept(MediaType.APPLICATION_JSON)
                .header(PropertyJwtToken.STR_TOKEN, "values")
            )
            .andExpect(status().is4xxClientError());
       
    }
    
    @Test
    public void accept() throws Exception{
        String token = componentJwtToken.createToken("jhk7375");
        mvc.perform(
            MockMvcRequestBuilders
                .get("/apiAccessHistories")
                .accept(MediaType.APPLICATION_JSON)
                .header(PropertyJwtToken.STR_TOKEN, token)
            )
            .andExpect(status().isOk());
        
    }


}