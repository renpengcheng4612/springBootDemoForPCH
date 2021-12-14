package com.chinaclear.sz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("helloworld")
    @ResponseBody
    public String helloWorld() {
        return "hello world";
    }

}
