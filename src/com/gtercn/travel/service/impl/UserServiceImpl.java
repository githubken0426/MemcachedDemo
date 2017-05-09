package com.gtercn.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.travel.bean.User;
import com.gtercn.travel.dao.UserDao;
import com.gtercn.travel.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	public User userLogin(User user) {
		return userDao.userLogin(user);
	}

}
