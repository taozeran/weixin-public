package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by taozeran on 2020/3/24/17:13
 */
@AutoConfigureMockMvc
@SpringBootTest
public class AuthIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getEchoStr() throws Exception {
        this.mockMvc.perform(get("/auth/test").param("echostr", "abcasd"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("abcasd"));
    }
}


