package com.liufu.store.controller;

import com.liufu.store.pojo.User;
import com.liufu.store.service.UserService;
import com.liufu.store.service.ex.ServiceException;
import com.liufu.store.service.ex.UsernameDuplicatedException;
import com.liufu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
}
