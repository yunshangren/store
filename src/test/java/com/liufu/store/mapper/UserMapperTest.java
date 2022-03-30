package com.liufu.store.mapper;

import com.liufu.store.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
// RunWith:表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    /**
     * 单元测试方法：可以单独的独立运行，不用启动整个项目
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的修饰参数必须是public
     */
    @Test
    public void addUser(){
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        System.out.println(userMapper.addUser(user));
    }

    @Test
    public void findByName(){
        System.out.println(userMapper.findByUsername("张三"));
    }
}
