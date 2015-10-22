package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "px_operate")
public class Operate extends IdEntity {

	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String create_user;// 操作人
	@Column
	private String create_useruuid;// 品牌名称
	@Column
	private String message;// 
	@Column
	private String note;// 
	@Column
	private String groupuuid;// 
	@Column
	private String studentuuid;// 
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getStudentuuid() {
		return studentuuid;
	}
	public void setStudentuuid(String studentuuid) {
		this.studentuuid = studentuuid;
	}



}
