package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_classnewsdianzan")
public class ClassNewsDianzan extends IdEntity {

	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String newsuuid;// 品牌名称
	@Column
	private String message;// 公司全称
	@Column
	private String create_user;// 品牌名称
	@Column
	private String create_useruuid;// 公司全称
	@Column
	private Integer type;//点赞类型 0：互动 1：公告 2：课程表 3：食谱
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column
	private Integer usertype;//0:老师 1：家长

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getNewsuuid() {
		return newsuuid;
	}
	public void setNewsuuid(String newsuuid) {
		this.newsuuid = newsuuid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}



}
