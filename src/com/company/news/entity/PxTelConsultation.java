package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_tel_consultation")
public class PxTelConsultation extends IdEntity {
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String ext_uuid;// 关联uuid.
	@Column
	private String group_uuid;//学校uuid.
	@Column
	private String ext_context;// 咨询内容.系统获取.100字内.
	@Column
	private Integer type;//点赞类型  关联系统参数 common_type.学校或者课程.
	@Column
	private String tel;// 评价人
	@Column
	private String tel_name;//  评价人(uuid)
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getExt_uuid() {
		return ext_uuid;
	}
	public void setExt_uuid(String ext_uuid) {
		this.ext_uuid = ext_uuid;
	}
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}
	public String getExt_context() {
		return ext_context;
	}
	public void setExt_context(String ext_context) {
		this.ext_context = ext_context;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel_name() {
		return tel_name;
	}
	public void setTel_name(String tel_name) {
		this.tel_name = tel_name;
	}



}
