package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractClass extends IdEntity {
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String name;// 班级名称
	@Column
	private String groupuuid;// 培训机构
	@Column
	private String create_user;// 创建人
	@Column
	private String create_useruuid;// 创建人id
	
	private String headTeacher_name;// 班主任
	
	private String teacher_name;// 老师
	
	private String headTeacher;//
	
	private String teacher;//
	@Transient
	public String getHeadTeacher_name() {
		return headTeacher_name;
	}

	public void setHeadTeacher_name(String headTeacher_name) {
		this.headTeacher_name = headTeacher_name;
	}
	@Transient
	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	@Transient
	public String getHeadTeacher() {
		return headTeacher;
	}

	public void setHeadTeacher(String headTeacher) {
		this.headTeacher = headTeacher;
	}
	@Transient
	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
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

	public String getGroupuuid() {
		return groupuuid;
	}

	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
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

}
