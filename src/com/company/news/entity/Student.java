package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户基本信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_student")
public class Student extends IdEntity {

	@Column
	private String loginname;// 登录名。手机号码或邮箱
	@Column
	private String name;// 昵称
	@Column
	private String password;// 密码，md5加密。（UTF-8）
	@Column
	private String nickname;// 电话号码。
	@Column
	private Integer sex;// 0:男,1:女
	@Column
	private Date login_time;// 最后一次登录时间。
	@Column
	private Date create_time;
	
	@Column
	private Date last_login_time;// 上一次登录时间
	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	@Column
	private String headimg;// 用户类型。0:普通用户(默认)；1:组织管理员

	@Column
	private String birthday;// email


	@Column
	private String ma_tel;// email

	@Column
	private String ba_tel;// email

	@Column
	private String nai_tel;// email

	@Column
	private String ye_tel;// email

	@Column
	private String waipo_tel;// email

	@Column
	private String waigong_tel;// email

	@Column
	private String other_tel;// email

	@Column
	private String groupuuid;// email
	
	@Column
	private String classuuid;// email
	
	@Column
	private String ma_name;// email

	@Column
	private String ba_name;// email

	@Column
	private String address;// email

	@Column
	private String note;// email
	
	@Column
	private String idcard;// email
	
	@Column
	private String ma_work;// email

	@Column
	private String ba_work;// email
	
	public String getMa_name() {
		return ma_name;
	}

	public void setMa_name(String ma_name) {
		this.ma_name = ma_name;
	}

	public String getBa_name() {
		return ba_name;
	}

	public void setBa_name(String ba_name) {
		this.ba_name = ba_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMa_work() {
		return ma_work;
	}

	public void setMa_work(String ma_work) {
		this.ma_work = ma_work;
	}

	public String getBa_work() {
		return ba_work;
	}

	public void setBa_work(String ba_work) {
		this.ba_work = ba_work;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMa_tel() {
		return ma_tel;
	}

	public void setMa_tel(String ma_tel) {
		this.ma_tel = ma_tel;
	}

	public String getBa_tel() {
		return ba_tel;
	}

	public void setBa_tel(String ba_tel) {
		this.ba_tel = ba_tel;
	}

	public String getNai_tel() {
		return nai_tel;
	}

	public void setNai_tel(String nai_tel) {
		this.nai_tel = nai_tel;
	}

	public String getYe_tel() {
		return ye_tel;
	}

	public void setYe_tel(String ye_tel) {
		this.ye_tel = ye_tel;
	}

	public String getWaipo_tel() {
		return waipo_tel;
	}

	public void setWaipo_tel(String waipo_tel) {
		this.waipo_tel = waipo_tel;
	}

	public String getWaigong_tel() {
		return waigong_tel;
	}

	public void setWaigong_tel(String waigong_tel) {
		this.waigong_tel = waigong_tel;
	}

	public String getOther_tel() {
		return other_tel;
	}

	public void setOther_tel(String other_tel) {
		this.other_tel = other_tel;
	}

	public String getGroupuuid() {
		return groupuuid;
	}

	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}

	public String getClassuuid() {
		return classuuid;
	}

	public void setClassuuid(String classuuid) {
		this.classuuid = classuuid;
	}



}
