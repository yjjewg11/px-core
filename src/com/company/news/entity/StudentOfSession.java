package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 *学生信息表,用于家长登录后保存在session中,尽量最小化
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_student")
public class StudentOfSession extends IdEntity {
	@Column
	private String name;// 姓名.
	@Column
	private String nickname;// 昵称
	
	@Column
	private String groupuuid;// 关联学校
	@Column
	private String classuuid;//关联班级
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
