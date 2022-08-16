package com.websocket.simplerest.controller;


import com.fasterxml.jackson.databind.ObjectWriter;
import com.websocket.simplerest.entity.Simple;
import com.websocket.simplerest.service.SimpleService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class SimpleServiceControllerTest {

    private static final Long SIMPLE_OBJECT_ID = 1L;
    private static final String SIMPLE_CONTENT = "Some context";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SimpleService simpleService;

    @Test
    void createSimpleObject() throws Exception{
        Simple simple = createObject();

        String requestBody = objectMapper.writeValueAsString(simple);

        this.mockMvc.perform(post("/api/addLog")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findObjectById() throws Exception {
        Simple simple = createObject();

        String expectedResponseBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(simple);

        Mockito.when(simpleService.findById(SIMPLE_OBJECT_ID)).thenReturn(simple);

        this.mockMvc.perform(get("/api/"+SIMPLE_OBJECT_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));

        verify(simpleService, times(1)).findById(SIMPLE_OBJECT_ID);
    }

    @Test
    void findAllObjects() throws Exception {
        Simple simple = createObject();
        List<Simple> simples = List.of(simple);

        String expectedResponseBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(simples);

        Mockito.when(simpleService.getListContent()).thenReturn(simples);

        this.mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));

        verify(simpleService, times(1)).getListContent();
    }

    @Test
    void deleteObjectById() throws Exception {
        Simple simple = createObject();

        Mockito.when(simpleService.findById(SIMPLE_OBJECT_ID)).thenReturn(simple);

        this.mockMvc.perform(delete("/api/"+SIMPLE_OBJECT_ID))
                .andExpect(status().isOk());

        verify(simpleService, times(1)).deleteContentById(SIMPLE_OBJECT_ID);
    }

    @Test
    void updateObjectById() throws Exception {
        Simple simpleObject = new Simple();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(simpleObject);

        this.mockMvc.perform(put("/api/"+SIMPLE_OBJECT_ID)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(simpleService, times(1)).update(SIMPLE_OBJECT_ID, simpleObject);
    }

    private Simple createObject(){
        Simple simple = new Simple();
        simple.setId(SIMPLE_OBJECT_ID);
        simple.setContent(SIMPLE_CONTENT);
        return simple;
    }
}
