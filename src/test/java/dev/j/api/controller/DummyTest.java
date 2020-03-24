package dev.j.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dev.j.api.restful.Application;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class DummyTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void dummyGet() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/dummy/dummy").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string("dummy"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void accGet(){
        try {
            mvc.perform(
                    MockMvcRequestBuilders.get("/acc")
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}