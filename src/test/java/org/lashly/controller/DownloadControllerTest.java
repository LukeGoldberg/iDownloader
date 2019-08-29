package org.lashly.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.lashly.ApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DownloadControllerTest extends ApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void downloadListTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/download/list")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void downFileTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/download/file/2347823u9ryhshndfcwerf2iojfrihweoj")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .header("If-None-Match", "234823y4982urihdwe9yu23")
                        .header("If-Modified-Since", "2019-01-31 14:33:00")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

}
