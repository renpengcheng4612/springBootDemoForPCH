package com.pc.sz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 焱宣
 * @Description
 * @create 2022-02-07 15:13
 */
@ApiIgnore
@RestController
public class HomeController {

    @GetMapping(value = {"/home","/api"})
    public ModelAndView home() {
        return new ModelAndView("redirect:/swagger-ui/");
    }
}
