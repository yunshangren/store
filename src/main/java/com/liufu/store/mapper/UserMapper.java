package com.liufu.store.mapper;

import com.liufu.store.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

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

    @Select("select count(*) from t_user where username = #{username}")
    int countByUsername(String username);

    @Select("select * from t_user where uid = #{uid}")
    User findByUid(int uid);

    @Update("update t_user set password = #{password},modified_user = #{modifiedUser},modified_Time = #{modifiedTime} where uid = #{uid}")
    int updatePasswordByUid(int uid, String password, String modifiedUser, Date modifiedTime);

    @Update("update t_user set phone = #{phone}, email = #{email}, gender = #{gender}," +
            "modified_user = #{modifiedUser}, modified_time = #{modifiedTime} where uid = #{uid}")
    int updateUserDataByUid(int uid,String phone,String email,int gender, String modifiedUser, Date modifiedTime );


    @Update("update t_user set avatar = #{avatar}, modified_user = #{modifiedUser},modified_Time = #{modifiedTime} where uid = #{uid}")
    int updateAvatarByUid(int uid, String avatar, String modifiedUser, Date modifiedTime);
}
