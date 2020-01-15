package com.senia.test.multi.mapper;

import com.senia.test.multi.entity.PageParameter;
import com.senia.test.multi.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    // 查询用户信息
    @Select("select * from t_user where NAME = #{name} ")
    UserDo findByName(@Param("name") String name);

    // 查询用户信息、角色、权限
    @Select(" SELECT user.id, user.name, user.password,role.id as roleId, role.name as roleName,\tpermission.id as permissionId, permission.name as permissionName, permission.url as permissionUrl FROM user, user_role, role, role_permission, permission     WHERE user.id = #{id}         AND user.id = user_role.user_id  AND user_role.role_id = role.id AND role.id = role_permission.role_id   AND role_permission.permission_id = permission.id")
    UserDo findById(@Param("id") String id);
}
