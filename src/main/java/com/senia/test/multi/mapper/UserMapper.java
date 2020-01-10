package com.senia.test.multi.mapper;

import com.senia.test.multi.entity.PageParameter;
import com.senia.test.multi.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user")
    public List<UserDo> findAll();

    /**
     * 带入数据参数
     * @param page
     * @return
     */
    @Select("select * from t_user ")
    public List<UserDo> findAllPage(PageParameter page);
}
