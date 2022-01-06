package com.chinaclear.sz.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController     // 控制器注解
@EnableAutoConfiguration  // 启用自动配置
@RequestMapping("/base")
public class
BaseController {

    @RequestMapping("/home")           // 表示访问映射路径，此时的路径为 "/" ,访问地址为 http://localhost:8080/
    @ResponseBody                   //RestFul返回形式
    public String home() {
        return "www.baidu.com";
    }

    @GetMapping("/start")
    @ResponseBody          // 该注解表示直接返回的数据以字符串或 Json 的形式获得
    public String start() {
        return "1234567.....";
    }

    @GetMapping("/echo")    //只支持 get 请求模式
    public String echo(String msg) {  //接受msg 参数    请求方式为： http://localhost:8080/echo?msg=www.mldn.cn
        return "[ECHO]" + msg;     // 信息处理后返回
    }

    @GetMapping("/echo/{msg}")    //只支持 get 请求模式
    public String restFulEcho(@PathVariable("msg") String msg) {  // 接受msg 参数 请求方式为： http://localhost:8080/echo/www.mldn.cn
        return "[ECHO]" + msg;     // 信息处理后返回
    }


    /*
     * 关于传递参数的选择：
     *  在RestFul 架构中请求路径受多累语法支持，开发者可以结合HTTP 请求模式（Get,post,put,delete等）与路径，实现多种组合，以处理不同类型的用户请求。参数的传递模式可以由开发团队自行定义
     *
     * */

}
