package com.senia.test.multi.controller;

import com.senia.test.multi.annotation.DataSource;
import com.senia.test.multi.entity.PageParameter;
import com.senia.test.multi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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


    @RequestMapping("findAllPage")
    public Object findAllPage(){
        PageParameter page = new PageParameter();
        page.setCurrentPage(1);
        page.setPageSize(2);
        return userService.findAllPage(page);
    }
}
