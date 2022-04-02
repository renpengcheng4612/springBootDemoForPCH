package com.pc.sz.resttemplate;

import com.pc.sz.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RestTemplateTest {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void test1() {
        // 底层执行引擎 HttpUrlConnection
        RestTemplate restTemplate = new RestTemplate();
        // 底层执行引擎 Apache HttpComponents  (HttpClient)
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        String url = "http://localhost:8686/v1.0/user";
        Map<String, Long> paramMap = new HashMap<>();
        User user = restTemplate.getForObject(url, User.class, paramMap);
        log.info("-------------------------", user);
    }

    @Test
    public void test2() {
        String url = "http://localhost:8686/v1.0/user";
        Map<String, Long> paramMap = new HashMap<>();
        //  User user = restTemplate.getForObject(url, User.class,  null);
        //  log.info("-------------------------", user);
    }
}