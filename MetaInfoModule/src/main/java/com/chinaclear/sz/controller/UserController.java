package com.chinaclear.sz.controller;

import com.chinaclear.sz.aspect.SysLog;
import com.chinaclear.sz.model.aspect.SysLogBo;
import com.chinaclear.sz.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/helloworld")
    @ResponseBody
    @ApiOperation(value = "hello world", httpMethod = "GET")
    @SysLog("测试")
    public String helloWorld() {
        return "hello world";
    }
}
