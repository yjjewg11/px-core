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
	  private String send_user;//品牌名称
	  @Column
	  private String send_useruuid;//公司全称
	  @Column
	  private String revice_user;//品牌名称
	  @Column
	  private String revice_useruuid;//公司全称
	  @Column
	  private String title;//坐标
	  @Column
	  private String message;//电话
	  @Column
	  private Integer type;//类型'0:普通通知 1:内部通知 2：班级通知',
	  @Column
	  private Integer isread;//坐标	
	  @Column
	  private Integer isdelete;//坐标	
	  
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
