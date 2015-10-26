package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 家长的其他资料数据
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_parent_data")
public class ParentData extends IdEntity {
	@Column
	private String parent_uuid;// 关联用户uuid
	@Column
	private String phone_version;//操作系统版本号
	@Column
	private String loginname;// 电话号码。
	@Column
	private String phone_type;//iPhone 或  andorid
	@Column
	private String app_verion;// 客户端版本号
	@Column
	private String sessionid;// 保存登录成功后的sessionid
	@Column
	private String city;// 城市
	@Column
	private String ip;//客户端ip
	@Column
	private Timestamp update_time;// 更新时间
	public String getParent_uuid() {
		return parent_uuid;
	}
	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}
	
	public String getPhone_type() {
		return phone_type;
	}
	public void setPhone_type(String phone_type) {
		this.phone_type = phone_type;
	}
	public String getApp_verion() {
		return app_verion;
	}
	public void setApp_verion(String app_verion) {
		this.app_verion = app_verion;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPhone_version() {
		return phone_version;
	}
	public void setPhone_version(String phone_version) {
		this.phone_version = phone_version;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

}
