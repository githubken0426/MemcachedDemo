package com.gtercn.travel.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.gtercn.travel.bean.User;
import com.gtercn.travel.memcached.XMemcachedService;
import com.gtercn.travel.service.UserService;
import com.opensymphony.xwork2.Action;

public class UserAction {
	@Autowired
	private UserService userService;
//	@Autowired
//	private MemCachedService memcachedService;
	@Autowired
	private XMemcachedService xMemchachedService;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		User loginUser=userService.userLogin(user);
		System.out.println(loginUser);
//		memChachedService.save(MemCachedKeys.USER_INFO, loginUser);
//		User cachedUser=(User) memcachedService.getDataByCache(MemCachedKeys.USER_INFO);
//		System.out.println(cachedUser);
		return Action.SUCCESS;
	}
}
