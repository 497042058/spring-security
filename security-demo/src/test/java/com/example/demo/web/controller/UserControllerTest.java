package com.example.demo.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : zhuqiang
 * @version : V1.0
 * @date : 2018/8/1 22:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    // 伪造的mvc不会真正去启动项目?
    // 相对来说会比直接启用项目要快
    private MockMvc mockMvc;

    @Before
    public void setup() {
        // befor 注解，每个测试用例执行前都会执行
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // 查询成功的测试用例
    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc
                // 发起请求
                .perform(MockMvcRequestBuilders.get("/user")
                        .param("username", "mrcode")
                        .param("age", "1")
                        .param("ageTo", "3")
                        .param("xxx", "test")
                        // 添加请求头为json
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                // 期望的结果
                // 这里期望返回的http状态码为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 从返回的结果中（json）获取长度，期望长度为3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
        ;
    }
}