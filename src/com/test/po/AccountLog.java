package com.lifeassistant.po;

import java.math.BigDecimal;

/**
 * 
 * @author 周生锋 主要用于记录本系统的操作日志信息， 主要定义日志ID、账户ID、余额、余额转入账户ID、 余额转入转出类型、 时间 （余额转动操作时间、 游戏开始时间、游戏结束时间、 登录系统时间 、 退出系统时间）等；
 * 
 */
public class AccountLog {
	private int log_id;// 日志ID
	private int account_Id;// 账户ID
	private int transfer_account;// 余额
	private String type;// 余额转入账户ID、余额转入转出的类型
	private BigDecimal log_time;// 时间（余额转动操作时间 游戏开始时间、游戏结束时间、登录系统时间 、退出系统时间）
	private BigDecimal money;

	// 构造函数
	public AccountLog() {
		super();
	}

	public AccountLog(int log_id, int account_Id, int transfer_account, String type, BigDecimal log_time, BigDecimal money) {
		super();
		this.log_id = log_id;
		this.account_Id = account_Id;
		this.transfer_account = transfer_account;
		this.type = type;
		this.log_time = log_time;
		this.money = money;
	}

	// get() set() 方法
	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public int getAccount_Id() {
		return account_Id;
	}

	public void setAccount_Id(int account_Id) {
		this.account_Id = account_Id;
	}

	public int getTransfer_account() {
		return transfer_account;
	}

	public void setTransfer_account(int transfer_account) {
		this.transfer_account = transfer_account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getLog_time() {
		return log_time;
	}

	public void setLog_time(BigDecimal log_time) {
		this.log_time = log_time;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	// 重写的tostring()方法
	@Override
	public String toString() {
		return "Account_log [log_id=" + log_id + ", account_Id=" + account_Id + ", transfer_account=" + transfer_account + ", type=" + type + ", log_time=" + log_time + ", money=" + money + "]";
	}

}
