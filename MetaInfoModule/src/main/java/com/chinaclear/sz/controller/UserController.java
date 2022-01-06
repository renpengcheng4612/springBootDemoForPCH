package com.chinaclear.sz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/helloworld")
    @ResponseBody
    public String helloWorld() {
        return "hello world";
    }

}
