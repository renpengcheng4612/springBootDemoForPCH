package com.chinaclear.sz.main;

import com.chinaclear.sz.configuration.SpringConfig;
import com.chinaclear.sz.model.User;
import com.chinaclear.sz.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        // AnnotationConfigApplicationContext 
        AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(SpringConfig.class);
        // 获取对象
        UserService userService = configApplicationContext.getBean(UserService.class);
        List<User> allUser = userService.findAllUser();
        System.out.println(allUser);
    }
}
