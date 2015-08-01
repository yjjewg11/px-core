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
@Table(name = "px_studentsignrecord")
public class StudentSignRecord extends IdEntity {
	@Column
	private Integer type;//1:家长打卡,2:老师签到,3:家长签到
	@Column
	private String studentuuid;// 关联学生id
	@Column
	private String studentname;// 关联学生name
	@Column
	private String cardid;// 卡号
	@Column
	private Date sign_time;// 刷卡时间
	@Column
	private String groupuuid;// 机构UUID
	@Column
	private String groupname;// 幼儿园名
	@Column
	private String sign_name;// 签到人
	@Column
	private String sign_uuid;// 签到人uuid
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
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
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public Date getSign_time() {
		return sign_time;
	}
	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getSign_name() {
		return sign_name;
	}
	public void setSign_name(String sign_name) {
		this.sign_name = sign_name;
	}
	public String getSign_uuid() {
		return sign_uuid;
	}
	public void setSign_uuid(String sign_uuid) {
		this.sign_uuid = sign_uuid;
	}
	
	
	

}
