package com.pc.sz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 焱宣
 * @Description
 * @create 2022-03-06 12:40
 */


@MapperScan("com.pc.sz.mapper")
@ComponentScan(basePackages = "com.pc.sz")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
