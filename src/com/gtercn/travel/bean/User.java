package com.gtercn.travel.bean;

import java.io.Serializable;

/**
 * 用户表
 * @author Administrator
 * 2016-5-30 下午04:00:42
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userName;
	private String email;
	private String password;
	private Integer isEnable;
	private String roleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
