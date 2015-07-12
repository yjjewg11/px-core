package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "px_accounts")
public class Accounts extends IdEntity {

	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String create_user;// 品牌名称
	@Column
	private String create_useruuid;// 公司全称
	@Column
	private Timestamp accounts_time;// 类型：'0:普通 1：重要',
	@Column
	private String title;// 坐标
	@Column
	private String description;// 电话
	@Column
	private double num;// 类型'0:普通通知 1:内部通知 2：班级通知',
	@Column
	private String groupuuid;// 坐标
	@Column
	private String classuuid;// 坐标
	@Column
	private String studentuuid;// 学生uuid
	@Column
	private String studentname;// 学生名
	@Column
	private Integer type;// 坐标

	public String getStudentuuid() {
		return studentuuid;
	}

	public void setStudentuuid(String studentuuid) {
		this.studentuuid = studentuuid;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
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

	public Timestamp getAccounts_time() {
		return accounts_time;
	}

	public void setAccounts_time(Timestamp accounts_time) {
		this.accounts_time = accounts_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
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
