package com.senia.test.multi.controller;

import com.senia.test.multi.annotation.DataSource;
import com.senia.test.multi.entity.PageParameter;
import com.senia.test.multi.entity.UserDo;
import com.senia.test.multi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @RequestMapping("login")
    public Object login(String username, String password){
        log.info("登录接口:{}-{}",username,password);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            SecurityUtils.getSubject().login(token);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 401;
        }


    }
    @RequestMapping("error")
    public Object error(){
        log.info("错误");
        return 0;
    }
    @RequestMapping("success")
    public Object success(){
        UserDo principal = (UserDo) SecurityUtils.getSubject().getPrincipal();
        log.info("登录成功:{}",principal);
        return 0;
    }


}
