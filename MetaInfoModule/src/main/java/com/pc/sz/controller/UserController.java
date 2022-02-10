package com.pc.sz.controller;

import com.pc.sz.aspect.SysLog;
import com.pc.sz.model.User;
import com.pc.sz.service.SysLogService;
import com.pc.sz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private UserService userService;

    @RequestMapping("/helloworld")
    @ResponseBody
    @ApiOperation(value = "hello world", httpMethod = "GET")
    @SysLog("测试")
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping("/userNameIsExist")
    @ResponseBody
    @ApiOperation(value = "userNameIsExist", httpMethod = "GET")
    @SysLog("测试")
    public HttpStatus userNameIsExist(@RequestParam String userName) {

        //1.判断用户名是否为空
        if (StringUtils.isBlank(userName)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        User user = new User();
        user.setUserName(userName);
        boolean isExist = userService.queryUsernameIsExist(user);
        if (isExist) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }
}
