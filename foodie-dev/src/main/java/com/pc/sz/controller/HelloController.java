package com.pc.sz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController  // 默认所有的请求都是返回的json 对象
public class HelloController {

    @GetMapping("/hello")
    public Object hello() {
        return "hello world";
    }
}
