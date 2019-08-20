package com.example.demo.api;

import com.example.demo.api.dto.OrderRequestDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContrllerAPITests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void when_InvalidInput_then_BadRequest() throws Exception {
        OrderRequestDto dtoEmptyCode = OrderRequestDto.builder()
                .code("")
                .build();
        String json = (new Gson()).toJson(dtoEmptyCode);

        mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_EverythingOk_then_Ok() throws Exception {
        OrderRequestDto dtoOk = OrderRequestDto.builder()
                .code("ORDER001")
                .product("PRODUCT001")
                .action("BUY")
                .volume(10000.)
                .build();
        String json = (new Gson()).toJson(dtoOk);

        mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.code").value(dtoOk.getCode()));
    }
}
