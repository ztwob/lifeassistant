package com.lifeassistant.po;

import java.math.BigDecimal;

/**
 * 
 * @author 周生锋 账户信息实体类： 主要用于定义账户ID、账户余额、对应的用户ID；同时包括构造方法、 get/set方法、tostring方法
 * 
 */
public class Account {
	private int accountId; // 用户账户id
	private double account_money;// 账户余额
	private int userId;// 对应的userID
	// 构造方法

	public Account(int accountId, double account_money, int userId) {
		super();
		this.accountId = accountId;
		this.account_money = account_money;
		this.userId = userId;
	}

	public Account() {
		super();
	}

	// get() set() 方法
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAccount_money() {
		return account_money;
	}

	public void setAccount_money(double account_money) {
		this.account_money = account_money;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// 重写的tostring()方法
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", account_money=" + account_money + ", userId=" + userId + "]";
	}

}
