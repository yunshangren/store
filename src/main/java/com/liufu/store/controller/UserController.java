package com.liufu.store.controller;

import com.liufu.store.pojo.User;
import com.liufu.store.service.UserService;
import com.liufu.store.service.ex.ServiceException;
import com.liufu.store.service.ex.UsernameDuplicatedException;
import com.liufu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseHandler{
    @Autowired
    private UserService userService;


    @PostMapping("/")
    public JsonResult<Void> addUser(@RequestBody User user){
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
}
