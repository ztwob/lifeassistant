package com.lifeassistant.po;

import java.sql.Timestamp;

/**
 * 
 * @author 周生锋 用户信息实体类： 主要用于定义用户ID、用户名、用户密码、地址、电话、真实姓名、上次登录时间等， 同时包括构造方法、get/set方法、tostring方法；
 * 
 */

public class User {
	private int userId;// 用户id
	private String userName;// 用户姓名
	private String userPassword;// 登录密码
	private String realName;// 真实名字
	private String tel;// 电话号码
	private String address;// 地址
	private Timestamp logintime;// 上次登陆时间
//构造方法
	
	public User(String userName, String userPassword, String realName, String tel, String address) {
		
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.realName = realName;
		this.tel = tel;
		this.address = address;
	}

	public User() {
	super();
}

	public User(int userId, String userName, String userPassword, String realName, String tel, String address, Timestamp logintime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.realName = realName;
		this.tel = tel;
		this.address = address;
		this.logintime = logintime;
	}
//get() set() 方法
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}
//重写的tostring()方法
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", realName=" + realName + ", tel=" + tel + ", address=" + address + ", logintime=" + logintime
				+ "]";
	}

}
