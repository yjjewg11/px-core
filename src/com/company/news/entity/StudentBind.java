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
@Table(name = "px_studentbind")
public class StudentBind extends IdEntity {

	@Column
	private String studentuuid;// 登录名。手机号码或邮箱
	@Column
	private String cardid;// 姓名.
	@Column
	private String create_user;// 密码，md5加密。（UTF-8）
	@Column
	private String create_useruuid;// 昵称
	@Column
	private Integer type;// 0:男,1:女
	@Column
	private Date createtime;// 最后一次登录时间。
	@Column
	private String note;// 头像
	@Column
	private String name;// 生日.2015-07-14
	public String getStudentuuid() {
		return studentuuid;
	}
	public void setStudentuuid(String studentuuid) {
		this.studentuuid = studentuuid;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
