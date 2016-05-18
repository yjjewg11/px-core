package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户登录日志记录
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_user_login_trace")
public class UserLoginTrace extends IdEntity{
	public static String Type_account="account";
	public static String Type_wenxin="wenxin";
	public static String Type_qq="qq";
	@Column
	private String user_uuid;// 用户uuid，45
	@Column
	private String sessionid;// 当前session，128
	@Column
	private String  ip;//  普通用户昵称
	@Column
	private String  address;//  地址。256
	@Column
	private String  type;//  登录类型,45。account:账户。wenxin，qq
	
	@Column
	private Timestamp create_time;

	public String getUser_uuid() {
		return user_uuid;
	}

	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}
