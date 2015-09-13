package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 培训机构班级表
 * 
 * @author liumingquan
 *
 */
@Entity
@Table(name = "px_pxclass")
public class PXClass extends IdEntity {

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
	@Column
	private String context;// 班级课程详细介绍.

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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
