package com.senia.test.multi.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Rollback(value = false)
@Transactional(transactionManager = "transactionManager")
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void login() throws Exception {
        log.info("执行结果：{}",mockMvc.perform(MockMvcRequestBuilders.get("/user/login?username=senia&password=123456")));
    }
    @Test
    public void findAll() throws Exception {
        // 需要权限验证，返回的是重定向 /user/login
        log.error("+++++++++++++++++++++++++");
        mockMvc.perform(MockMvcRequestBuilders.get("/user/findAll"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/login"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
//        log.info("{}",mvcResult);
    }

    @Test
    public void findAll2() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/user/findAll2")).andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print()).andReturn();

        log.info("{}",mvcResult);
    }


}
