package com.liufu.store.service;

import com.liufu.store.pojo.User;
import com.liufu.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void addInsert(){
        try {
            User user = new User();
            user.setUsername("王五");
            user.setPassword("002");
            userService.addUser(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @Test
    public void login(){
        try {
            User user = userService.login("cool", "123");
            System.out.println(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
