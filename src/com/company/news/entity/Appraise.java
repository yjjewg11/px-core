package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "px_appraise")
public class Appraise extends IdEntity {

	@Column
	private Date create_time;// 评价时间
	@Column
	private String ext_uuid;// 关联uuid
	@Column
	private String class_uuid;// 关联具体课程班级
	@Column
	private String content;// 内容(HTML)
	@Column
	private String create_user;// 评价人
	@Column
	private String create_useruuid;//  评价人(uuid)
	@Column
	private Integer type;//点赞类型  关联系统参数 common_type
	@Column
	private Integer score;//得分 按照5星计算 1，2，3，4，5


	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

	public String getExt_uuid() {
		return ext_uuid;
	}
	public void setExt_uuid(String ext_uuid) {
		this.ext_uuid = ext_uuid;
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
	public String getClass_uuid() {
		return class_uuid;
	}
	public void setClass_uuid(String class_uuid) {
		this.class_uuid = class_uuid;
	}


}
