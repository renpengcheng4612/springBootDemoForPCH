package com.pc.sz.configuration;

import com.pc.sz.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration  // 声明当前类是spring 的配置文件 xml,我们可以将各种配置放到这里，比如创建对象
@ComponentScan(value = "com.chinaclear.sz")
@PropertySource(value = "classpath:jdbc.properties")  // 通过@PropertySource 可以读取指定位置的配置文件，通过@Value注解获取值
public class SpringConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    /* 创建userdao 对象，相当于配置文件中的 bean 标签，当这个配置文件被加载的时候，这个注解会被解析，会调用此方法创建对象
     *  实际开发中，我们自己的对象都是通过component 相关注解创建的，此注解主要用于创建我们无法添加注解的引入依赖类。
     * */
    @Bean
    public UserDao getUserDao() {
        return new UserDao();
    }
}
