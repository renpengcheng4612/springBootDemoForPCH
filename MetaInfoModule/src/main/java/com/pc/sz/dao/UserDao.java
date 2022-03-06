package com.pc.sz.dao;

import com.pc.sz.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> findAllUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(10 + i);
            user.setUid("uid" + i);
            user.setUserName("zhangsan" + i);
            list.add(user);
        }
        return list;
    }
}
