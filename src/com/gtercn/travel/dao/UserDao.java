package com.gtercn.travel.dao;

import org.springframework.stereotype.Repository;

import com.gtercn.travel.bean.User;

@Repository
public interface UserDao {
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public User userLogin(User user);
}
