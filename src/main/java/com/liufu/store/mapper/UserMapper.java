package com.liufu.store.mapper;

import com.liufu.store.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     *  插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数
     */
    @Insert("insert into t_user (uid, username, password, salt, phone, email, " +
            "gender, avatar, is_delete, created_user, created_time, modified_user, modified_time)\n" +
            "values (null,#{username},#{password},#{salt},#{phone},#{email},#{gender},#{avatar}," +
            "#{isDelete},#{createdUser},#{createdTime},#{modifiedUser},#{modifiedTime})")
    int addUser(User user);

    /**
     * 根据传入的用户名来查询用户数据
     * @param username
     * @return 如果找到则返回这个用户的数据，否则返回null
     */
    @Select("select * from t_user where username = #{username}")
    User findByUsername(String username);


}
