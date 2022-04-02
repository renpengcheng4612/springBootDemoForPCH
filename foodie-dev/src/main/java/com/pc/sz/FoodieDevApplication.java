package com.pc.sz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.pc.sz.mapper")
@ComponentScan(basePackages = "com.pc.sz,org.n3r.idworker")
@SpringBootApplication
public class FoodieDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieDevApplication.class, args);
    }
}
