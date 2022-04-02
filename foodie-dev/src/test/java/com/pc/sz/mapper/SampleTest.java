package com.pc.sz.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pc.sz.pojo.Users;
import com.pc.sz.util.MD5Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.pc.sz.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author 焱宣
 * @Description
 * @create 2022-03-06 13:27
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Sid sid;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Users> users = userMapper.selectList(null);
        Assert.assertEquals(6, users.size());
        users.forEach(System.out::println);
    }

    @Test
    public void insert() throws Exception {
        System.out.println(("----- insert method test ------"));
        Users user = new Users();
        user.setUserId(sid.nextShort());
        user.setNickname("renpengcheng");
        user.setSex(2);
        user.setUsername("renpengcheng");
        user.setFace("https://www.baidu.com");
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        user.setPassword(MD5Utils.getMD5Str("1234334"));
        user.setRealname("任鹏举");
        user.setRemark("我是一个备注信息");
        userMapper.insert(user);
    }

    @Test
    public void selectById() {
        Users users = userMapper.selectById("220316832298YCH0");
        System.out.println(users);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("username", "renpengcheng");
        columnMap.put("id", "2203168270F1TB9P");
        System.out.println(userMapper.selectByMap(columnMap));
    }

    @Test
    public void selectBachId() {
        ArrayList<String> ids = Lists.newArrayList("1908017YR51G1XWH", "190815GTKCBSS7MW", "2203167T17YFXW6W");
        userMapper.selectBatchIds(ids).forEach(System.out::println);
    }

    //map 提供了强大的构造器
    @Test
    public void selectByWrapper() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.like("username", "鹏").lt("created_time", new Date());
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }


    // 查找姓为任，创建日期为 2022-03-16的用户
    @Test
    public void selectByWrapper2() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.like("username", "ren").apply("date_format(created_time,'%Y-%m-%d')={0}", "2022-03-16");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    // 查找姓为任，创建日期为 2022-03-16的用户
    @Test
    public void selectByWrapper3() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.apply("date_format(created_time,'%Y-%m-%d')={0}", "2022-03-16")
                .inSql("realname", "SELECT realname  from users where realname like '任%'");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    // 查找姓为任，并且(年龄小于 40 或 邮箱不为空)
    // name like '任%' and (age<40 or email is not null)
    @Test
    public void selectByWrapper4() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.likeRight("realname", "任").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }


    // 查找姓为任，并且(年龄小于 40 且 大于 20 且 邮箱不为空)
    // name like '任%' and (age<40 or email is not null)
    @Test
    public void selectByWrapper5() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.likeRight("realname", "任").and(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    // (年龄小于 40 且 大于 20 且 邮箱不为空) 并且为 任姓
    //  (age<40 or email is not null)  and realname like '任%'
    @Test
    public void selectByWrapper6() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.nested(wr -> wr.lt("age", 40).gt("age", 20))
                .likeRight("realname", "任");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    //  找出姓名 为 任鹏程 任鹏举
    // realname in ('任鹏程','任鹏举')
    @Test
    public void selectByWrapper7() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.in("realname", Arrays.asList("任鹏程", "任鹏举"));
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    //查出邮箱只有为 87134070@qq.com 只返回一条
    //
    @Test
    public void selectByWrapper8() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.in("email", "871340705@qq.com").last("limit 1");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    // select 只查询部分字段
    //  找出姓名 为 任鹏程 任鹏举
    // realname in ('任鹏程','任鹏举')
    @Test
    public void selectByWrapperSuper() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.select("realname", "nickname").in("realname", Arrays.asList("任鹏程", "任鹏举"));
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    // select 只查询部分字段  (当字段比较多的时候)
    //  找出姓名 为 任鹏程 任鹏举
    // realname in ('任鹏程','任鹏举')
    @Test
    public void selectByWrapperSuper2() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.select(Users.class, info -> !info.getColumn().equals("created_time") && !info.getColumn().equals("updated_time")).in("realname", Arrays.asList("任鹏程", "任鹏举"));
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    // select 只查询部分字段  (当字段比较多的时候)
    //  找出姓名 为 任鹏程 任鹏举
    // realname in ('任鹏程','任鹏举')
    @Test
    public void selectWrapperLambda() {
        LambdaQueryWrapper<Users> lambdaQuery = new QueryWrapper<Users>().lambda();
        lambdaQuery.like(Users::getRealname, "任").lt(Users::getAge, 40);
        List<Users> users = userMapper.selectList(lambdaQuery);
        users.forEach(System.out::println);
    }
}
