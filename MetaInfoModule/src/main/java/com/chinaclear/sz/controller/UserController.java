package com.chinaclear.sz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @RequestMapping("/helloworld")
    @ResponseBody
    @ApiOperation(value = "hello world",httpMethod = "GET")
    public String helloWorld() {
        return "hello world";
    }

}
