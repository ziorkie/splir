package com.ziora.splir.controller;

import com.ziora.splir.SplirApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest(classes= SplirApplication.class)
class AuthControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    protected String obtainToken(String usernameOrEmail, String password) throws Exception{
    String jsonString = "{\"usernameOrEmail\" : \"" + usernameOrEmail + "\",\"password\" : \"" + password + "\"}";

    ResultActions result =
            mockMvc.perform(post("/auth/signin")
            .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonString)
                    .accept("application/json;charset=UTF-8"))
                    .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    String resultString = result.andReturn().getResponse().getContentAsString();
    JacksonJsonParser jsonParser = new JacksonJsonParser();
    return jsonParser.parseMap(resultString).get("accessToken").toString();
}

    @Test
    @Transactional
    public void getUserDetailsShouldReturnCorrectObject()throws Exception{
        String accessToken = obtainToken("testuser", "testuser");

        mockMvc.perform(get("/user/me").header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testuser"))
        );
    }

}