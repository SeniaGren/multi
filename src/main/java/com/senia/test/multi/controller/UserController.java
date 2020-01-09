package com.senia.test.multi.controller;

import com.senia.test.multi.annotation.DataSource;
import com.senia.test.multi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author senia
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    @DataSource(value="master")
    public Object findAll(){
        return userService.findAll();
    }

    @RequestMapping("findAll2")
    @DataSource(value="slave")
    public Object findAll2(){
        return userService.findAll();
    }
}
