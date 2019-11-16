package com.lifeassistant.po;

import java.sql.Date;

/**
 * 
 * @author 周生锋 日志实体类： 主要定义备忘录的备忘录ID、标题、时间、内容、对应的用户ID，同时包括构造方法、 get/set方法、tostring方法；
 * 
 */
public class Memorandum {
	private int memorandum_id;// 备忘录id
	private String memorandum_title;// 备忘录标题
	private Date datetime;// 备忘录创建时间
	private String content;// 内容
	private int userId;// 对应的userId
	// 构造方法

	public Memorandum() {
		super();
	}

	public Memorandum(int memorandum_id, String memorandum_title, Date datetime, String content, int userId) {
		super();
		this.memorandum_id = memorandum_id;
		this.memorandum_title = memorandum_title;
		this.datetime = datetime;
		this.content = content;
		this.userId = userId;
	}

	// get() set() 方法
	public int getMemorandum_id() {
		return memorandum_id;
	}

	public void setMemorandum_id(int memorandum_id) {
		this.memorandum_id = memorandum_id;
	}

	public String getMemorandum_title() {
		return memorandum_title;
	}

	public void setMemorandum_title(String memorandum_title) {
		this.memorandum_title = memorandum_title;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "Memordum [memorandum_id=" + memorandum_id + ", memorandum_title=" + memorandum_title + ", datetime=" + datetime + ", content=" + content + ", userId=" + userId + "]";
	}

}
