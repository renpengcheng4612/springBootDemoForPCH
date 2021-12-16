package com.chinaclear.sz.service;

import com.chinaclear.sz.dao.UserDao;
import com.chinaclear.sz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User>  findAllUser(){
        return userDao.findAllUser();
    }
}
