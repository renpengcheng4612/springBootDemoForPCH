package com.pc.sz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.pc.sz.mapper")
@ComponentScan(basePackages = "com.pc.sz")
@SpringBootApplication
public class FoodieDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieDevApplication.class, args);
    }
}
