package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.company.news.interfaces.SessionUserInfoInterface;


/**
 * 用户基本信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_user")
public class User4QBaseInfo extends IdEntity  implements SessionUserInfoInterface{

	@Column
	private String loginname;// 登录名。手机号码或邮箱
	@Column
	private String name;// 昵称
	
	@Column
	private String tel;// 电话号码。
	@Column
	private Integer tel_verify;// 电话号码，验证。默认0，0:没验证。1:验证，2：提交验证，3.验证失败
	@Column
	private Integer sex;// 0:男,1:女
	@Column
	private Integer disable;// 是否被管理员封号。0：不封。1：封号，不允许登录。
	
	@Column
	private String office;// 职位。
	@Column
	private String img;// 头像。
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	@Column
	private String email;// email

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getDisable() {
		return disable;
	}

	public void setDisable(Integer disable) {
		this.disable = disable;
	}



	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

}
