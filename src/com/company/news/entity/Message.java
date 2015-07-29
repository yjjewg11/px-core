package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="px_message") 
public class Message extends IdEntity{
	
	  @Column
	  private Timestamp create_time;//创建时间
	  @Column
	  private String send_user;//发送人
	  @Column
	  private String send_useruuid;//发送人
	  @Column
	  private String revice_user;//接收人
	  @Column
	  private String revice_useruuid;//接收人
	  @Column
	  private String title;//标题
	  @Column
	  private String message;//内容
	  @Column
	  private Integer type;//类型'0:推送消息 1：站内信
	  @Column
	  private Integer isread;//坐标	
	  @Column
	  private Integer isdelete;//坐标	
	  @Column
	  private String group_uuid;//标题
	  @Column
	  private String group_name;//内容
	  
	  
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getSend_user() {
		return send_user;
	}
	public void setSend_user(String send_user) {
		this.send_user = send_user;
	}
	public String getSend_useruuid() {
		return send_useruuid;
	}
	public void setSend_useruuid(String send_useruuid) {
		this.send_useruuid = send_useruuid;
	}
	public String getRevice_user() {
		return revice_user;
	}
	public void setRevice_user(String revice_user) {
		this.revice_user = revice_user;
	}
	public String getRevice_useruuid() {
		return revice_useruuid;
	}
	public void setRevice_useruuid(String revice_useruuid) {
		this.revice_useruuid = revice_useruuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsread() {
		return isread;
	}
	public void setIsread(Integer isread) {
		this.isread = isread;
	}
	  
	  



}
