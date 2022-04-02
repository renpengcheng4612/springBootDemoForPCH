package com.pc.sz.controller;

import com.pc.sz.model.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RequestMapping(value = "v1.0")
@RestController
@Api(tags = "基本接口")
public class RestTemplateTestController {


    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setUserName("renpengcheng");
        user.setAge(22);
        user.setUid("898989898");
        return user;
    }
}
