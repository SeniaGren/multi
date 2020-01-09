package com.senia.test.multi.service.impl;

import com.senia.test.multi.entity.UserDo;
import com.senia.test.multi.mapper.UserMapper;
import com.senia.test.multi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDo> findAll() {
        return userMapper.findAll();
    }
}
