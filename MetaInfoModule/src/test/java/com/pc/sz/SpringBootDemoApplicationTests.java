package com.pc.sz;


import com.pc.sz.controller.BaseController;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest     //可以在SpringBootTest 括号中定义要测试的SpringBoot类
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration                     //进行web应用配置
class SpringBootDemoApplicationTests {

    @Autowired
    private BaseController baseController;  // 注入控制器对象

    @Test
    void contextLoads() {    // 使用junit测试
        TestCase.assertEquals(this.baseController.home(), "www.baidu.com");
    }
}
