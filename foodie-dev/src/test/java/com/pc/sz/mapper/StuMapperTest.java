package com.pc.sz.mapper;

import com.pc.sz.pojo.Stu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class StuMapperTest {

    @Autowired
    private StuMapper stuMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Stu> stus = stuMapper.selectAll();
        log.info("----------------------[{}]----------------", stus);
    }
}
