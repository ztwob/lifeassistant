package com.lifeassistant.po;

import java.util.Date;
/**
 * 类描述：游戏排行榜视图实体类
 * 作者： 于红键
 * 创建日期：2017年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class RankingVo {
	//用户姓名
	private String userName;
	//用户积分
	private int integral;
	//时间
	private Date time;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public RankingVo(String userName, int integral, Date time) {
		super();
		this.userName = userName;
		this.integral = integral;
		this.time = time;
	}
	public RankingVo() {
		super();
	}
	
	
}
