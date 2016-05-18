package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户修改自己资料记录
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_user_login_trace")
public class UserInfoUpdateTrace extends IdEntity{

	@Column
	private String user_uuid;// 用户uuid，45
	@Column
	private String sessionid;// 当前session，128
	@Column
	private String  ip;//  普通用户昵称
	@Column
	private String  address;//  地址。256
	@Column
	private String  type;//  登录类型,45。info：资料。password.密码。
	@Column
	private String name;// 昵称
	@Column
	private String  nickname;//  普通用户昵称
	@Column
	private String  realname;//  真实姓名
	@Column
	private String password;// 密码，md5加密。（UTF-8）
	@Column
	private String tel;// 电话号码。
	@Column
	private Integer tel_verify;// 电话号码，验证。默认0，0:没验证。1:验证，2：提交验证，3.验证失败
	@Column
	private Integer sex;// 0:男,1:女
	@Column
	private String office;// 职位。
	@Column
	private String img;// 头像。
	@Column
	private String email;// email
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getTel_verify() {
		return tel_verify;
	}

	public void setTel_verify(Integer tel_verify) {
		this.tel_verify = tel_verify;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
