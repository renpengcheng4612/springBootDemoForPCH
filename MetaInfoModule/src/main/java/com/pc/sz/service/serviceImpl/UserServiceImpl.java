package com.pc.sz.service.serviceImpl;

import com.pc.sz.dao.UserDao;
import com.pc.sz.model.User;
import com.pc.sz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public boolean queryUsernameIsExist(User user) {
        return false;
    }
}
