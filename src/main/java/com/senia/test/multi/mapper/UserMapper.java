package com.senia.test.multi.mapper;

import com.senia.test.multi.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user")
    public List<UserDo> findAll();
}
