package com.liufu.store.service;

import com.liufu.store.mapper.UserMapper;
import com.liufu.store.pojo.User;
import com.liufu.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username){
        return findByUsername(username);
    }

    /**
     * 用户注册方法
     * @param user 用户对象数据
     */
    public void addUser(User user){
        User result = userMapper.findByUsername(user.getUsername());
        if(result != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }

        // 密码加密处理：md5算法,连续加载三次
        // salt + password + salt
        String prePassword = user.getPassword();
        // 获取salt(随机生成)
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(prePassword, salt);
        user.setPassword(md5Password);
        user.setSalt(salt);


        user.setIsDelete(0);

        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        int rows = userMapper.addUser(user);
        if(rows != 1){
            throw new InsertException("注册过程中产生了未知异常");
        }
    }

    /**
     * 用户登录的方法
     * 将当前登录成功的用户数据以当前用户对象的形式返回
     * 可以将这个数据保存在session中，避免重复度很高的数据获取频繁操作数据库
     * 这里将username，uid保存在session中，用户头像保存在cookie中
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据
     */

    public User login(String username,  String password){
        User user = userMapper.findByUsername(username);
        if(user == null){
            throw new UsernameOrPasswordException("用户名或密码错误");
        }
        String salt = user.getSalt();
        String userPassword = user.getPassword();
        String md5Password = getMD5Password(password, salt);
        // 数据库字段带下划线，要转成驼峰格式，不然可能出现空指针异常
        if(user.getIsDelete() == 1 || !(md5Password.equals(userPassword))){
            throw new UsernameOrPasswordException("用户名或密码错误");
        } else{
            // 返回用户对象数据是为了辅助其他页面展示用户数据
            // 获取到的对象可能体量很大，但是实际只需要三个字段
            // 可以新创建一个user,只给需要的字段赋值

            User result = new User();
            result.setUsername(user.getUsername());
            result.setUid(user.getUid());
            result.setAvatar(user.getAvatar());

            return result;

        }
    }

    /**
     * 修改密码方法
     * @param uid uid, 用户在登录系统时，把uid,username保存在session中，所以可以在session中直接获取这个uid
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param modifiedUser 修改人
     */
    public void updatePassword(int uid,String oldPassword,String newPassword,String modifiedUser){
        User user = userMapper.findByUid(uid);
        String salt = user.getSalt();
        String md5Password = getMD5Password(oldPassword, salt);
        if(md5Password.equals(user.getPassword())){
            newPassword = getMD5Password(newPassword,salt);
            int row = userMapper.updatePasswordByUid(uid, newPassword,modifiedUser,new Date());
            if(row != 1){
                throw new UpdatePassWordException("修改密码时产生了未知异常");
            }
        }else{
            throw new WrongPasswordException("密码错误");
        }


    }

    /**
     * 更新用户数据方法
     * @param uid id
     * @param phone 电话
     * @param email 邮箱
     * @param gender 性别
     * @param modifiedUser 修改人
     */
    public void updateUserInfo(int uid,String phone,String email,int gender, String modifiedUser){
        int row = userMapper.updateUserDataByUid(uid,phone,email,gender,modifiedUser,new Date());
        if(row != 1){
            throw new UpdateUserDataException("修改个人资料出现未知异常");
        }
    }

    /**
     * 修改用户的头像
     * @param uid
     * @param avatar 用户头像的路径
     * @param modifiedUser
     */
    public void updateAvatarByUid(int uid, String avatar, String modifiedUser){
        int row = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, new Date());
        if(row != 1){
            throw new UpdateAvatarException("修改头像失败");
        }
    }

    /** 定义一个MD5算法的加密
     *
     */
    private String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            // md5加密算法
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
