package com.pc.sz.service;


import com.pc.sz.pojo.Users;
import com.pc.sz.pojo.bo.UserBO;

public interface UserService {

    /**
     * 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 判断用户名是否存在
     */
    public Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public Users queryUserForLogin(String username, String password);
}
