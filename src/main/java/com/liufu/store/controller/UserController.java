package com.liufu.store.controller;

import ch.qos.logback.core.util.FileSize;
import com.liufu.store.controller.ex.FileSizeException;
import com.liufu.store.controller.ex.FileStateException;
import com.liufu.store.controller.ex.FileTypeException;
import com.liufu.store.controller.ex.FileUploadIOException;
import com.liufu.store.pojo.User;
import com.liufu.store.service.UserService;
import com.liufu.store.service.ex.ServiceException;
import com.liufu.store.service.ex.UsernameDuplicatedException;
import com.liufu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseHandler{
    @Autowired
    private UserService userService;

    /**
     * 用户注册方法
     * @param user 这个地方不加注解，用的是ModelAndView
     * @return
     */
    @PostMapping("/reg")
    public JsonResult<Void> addUser(User user){
       /* JsonResult<Void> result = new JsonResult<>();
        try {
            userService.addUser(user);
            result.setStatus(200);
            result.setMessage("注册成功");
        } catch (ServiceException e) {
            result.setStatus(400);
            result.setMessage(e.getMessage());
        }*/
        userService.addUser(user);
        return new JsonResult<>(OK,"注册成功");
    }

    /**
     * 1.处理异常
     *     业务层抛出的异常是什么，在统一异常处理类中处理
     * 2.设计请求
     * 3.处理请求
     */
    @PostMapping("/login")
    public JsonResult<User> login(@RequestParam("username") String username,
                                  @RequestParam("password") String password, HttpSession session){
        User user = userService.login(username, password);

        // 把username和uid封装到session中
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());

        return new JsonResult<>(OK,"登录成功",user);

    }

    @PostMapping("/updatePassword")
    public JsonResult<Void> updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
                                           HttpSession session){
        int uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updatePassword(uid,oldPassword,newPassword,username);
        return new JsonResult<>(OK,"修改密码成功");
    }

    @PostMapping("/updateUserdata")
    public JsonResult<Void> updateUserData(HttpSession session, String phone,String email,int gender){
        int uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateUserInfo(uid,phone,email,gender,username);
        return new JsonResult<>(OK,"修改资料成功");
    }

    // 设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024; //10M
    // 限制上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
     * 修改用户头像
     * MultipartFile是springMVC提供的一个接口，可以用它来获取任何类型的文件，在springboot中，只需要在
     * 请求处理方法上添加一个MultipartFile类型的参数，会自动将文件中的数据赋值给这个参数
     * @param session
     * @param file
     * @return
     */
    @PostMapping("/updateAvatar")
    public JsonResult<String> updateAvatar(HttpSession session, MultipartFile file){
        // 判断文件大小
        if(file.isEmpty()){
            throw new FileSizeException("文件为空");
        }
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件过大");
        }
        // 判断文件类型
        if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("文件类型不支持");
        }

        String realPath = session.getServletContext().getRealPath("upload");
        File dir = new File(realPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        // 获取文件名，例如 avatar01.png
        String filename = file.getOriginalFilename();
        int index = filename.lastIndexOf('.');
        String suffix = filename.substring(index);
        filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        // 在upload文件夹中创建一个空文件dest
        File dest = new File(dir, filename);
        // 把传过来的文件file中的数据写到dest中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        } catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }

        String avatar = "/upload/" + filename;
        System.out.println(avatar);
        int uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.updateAvatarByUid(uid,avatar,username);
        // 返回用户头像的路径给前端，将来用于头像展示
        return new JsonResult<>(OK,"修改头像成功",avatar);
    }
}
