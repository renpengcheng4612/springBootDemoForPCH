package com.pc.sz.service.payment;


import com.pc.sz.pojo.payment.Users;

public interface UserService {

	/**
	 * @Description: 查询用户信息
	 */
	public Users queryUserInfo(String userId, String pwd);

}

