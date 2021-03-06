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
@Table(name = "px_parent")
public class Parent extends IdEntity implements SessionUserInfoInterface{

	@Column
	private String loginname;// 登录名。手机号码或邮箱
	@Column
	private String name;// 昵称.用于幼儿园,1.第1优先级,根据学生关系设置.比如:刘小妈妈.   2.用户修改昵称.
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
	private Integer disable;// 是否被管理员封号。0：不封。1：封号，不允许登录。
	@Column
	private Timestamp login_time;// 最后一次登录时间。
	@Column
	private Timestamp create_time;

	@Column
	private Timestamp last_login_time;// 上一次登录时间
	@Column
	private Integer type=1;// '1:妈妈' 2:爸爸 3：爷爷 4：奶奶 5：外公 6：外婆

	@Column
	private String email;// email
	
	@Column
	private String img;// 头像。
	@Column
	private Long count;//记录登入次数
	
	@Column
	private String sessionid;// 保存登录成功后的sessionid
	
	public Timestamp getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Timestamp last_login_time) {
		this.last_login_time = last_login_time;
	}


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


	public Integer getDisable() {
		return disable;
	}

	public void setDisable(Integer disable) {
		this.disable = disable;
	}



	public Timestamp getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Timestamp loginTime) {
		login_time = loginTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp createTime) {
		create_time = createTime;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
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

}
