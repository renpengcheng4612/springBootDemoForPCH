package com.pc.sz.service;

import com.pc.sz.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> findAllUser();

    public boolean queryUsernameIsExist(User user);
}
