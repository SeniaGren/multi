package com.senia.test.multi.service;

import com.senia.test.multi.entity.PageParameter;
import com.senia.test.multi.entity.UserDo;

import java.util.List;

public interface UserService {

    public List<UserDo> findAll();

    public List<UserDo> findAllPage(PageParameter page);

    UserDo findByName(String name);

    UserDo findById(String id);
}
