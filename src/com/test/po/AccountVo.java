package com.lifeassistant.po;

import java.util.Date;

/**
 * 类描述：账户信息实体 用于业务层之间的数据传递
 * 作者： 陈向阳
 * 创建日期：2017年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class AccountVo {
	
	//存入账户或者转出账户
	private int accountId;
	
	//转入账户
	private int transferAccountId;
	
	//转入或者转出
	private String type;
	
	//操作时间
	private Date log_time;
	
	//金额
	private double money;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getTransferAccountId() {
		return transferAccountId;
	}
	public void setTransferAccountId(int transferAccountId) {
		this.transferAccountId = transferAccountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getLog_time() {
		return log_time;
	}
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "AccountVo [accountId=" + accountId + ", transferAccountId=" + transferAccountId + ", type=" + type + ", log_time=" + log_time + ", money=" + money + "]";
	}
	
	
}
