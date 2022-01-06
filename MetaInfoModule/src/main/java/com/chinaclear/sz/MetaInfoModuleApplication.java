package com.chinaclear.sz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MetaInfoModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetaInfoModuleApplication.class, args);

    }
}
