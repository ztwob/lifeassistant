package com.lifeassistant.po;
/**
 * 
 * @author 周生锋
 *猜拳（猜数字）游戏实体类：
	主要用于定义游戏ID、用户ID、用户积分、时间（玩游戏的时间），
	同时包括构造方法、get/set方法、tostring方法；

 */

import java.sql.Date;

public class Ranking {
	private int rankingId;// 游戏id
	private int userId;// 对应的userId
	private int integral;// 用户积分
	private Date time;// 玩游戏的时间
	// 构造方法

	public Ranking() {
		super();
	}

	public Ranking(int rankingId, int userId, int integral, Date timestamp) {
		super();
		this.rankingId = rankingId;
		this.userId = userId;
		this.integral = integral;
		this.time = timestamp;
	}

	// get() set() 方法
	public int getRankingId() {
		return rankingId;
	}

	public void setRankingId(int rankingId) {
		this.rankingId = rankingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public Date getTimestamp() {
		return time;
	}

	public void setTimestamp(Date timestamp) {
		this.time = timestamp;
	}

	// 重写的tostring()方法
	@Override
	public String toString() {
		return "Ranking [rankingId=" + rankingId + ", userId=" + userId + ", integral=" + integral + ", time=" + time + "]";
	}

}
