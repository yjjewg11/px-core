package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "px_teacherjudge")
public class TeacherJudge extends IdEntity {

	@Column
	private Date create_time;// 评价时间
	@Column
	private String teacheruuid;// 关联教师teacheruuid
	@Column
	private String content;// 内容(HTML)
	@Column
	private String create_user;// 评价人
	@Column
	private String create_useruuid;//  评价人(uuid)
	@Column
	private Integer type;//点赞类型  1：满意 2：一般 3：不满意


	@Transient
	private String teacher_name;// 关联教师teacheruuid
	public String getTeacheruuid() {
		return teacheruuid;
	}
	public void setTeacheruuid(String teacheruuid) {
		this.teacheruuid = teacheruuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}


	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	@Transient
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

}
