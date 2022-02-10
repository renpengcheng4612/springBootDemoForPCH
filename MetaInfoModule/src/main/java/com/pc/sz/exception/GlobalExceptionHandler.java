package com.pc.sz.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*
     * 要捕获全局异常只需要以下几步即可
     *  1. 创建一个类,在类上面添加@ControllerAdvice注解
     *  2. 编写任意一个方法,参数是HttpServletRequest和Exception,在方法上面添加@ExceptionHandler注解,方法返回 值如果是字符串,则还需啊添加@ResponseBody,如果返回的是页面,则返回 ModelAndView 对象
     *  3. 按照自己的业务逻辑决定返回什么
     *
     * */
    @ExceptionHandler
    @ResponseBody
    public String exceptionHandler(HttpServletRequest request, Exception e) {
        return "错误";
    }
}
