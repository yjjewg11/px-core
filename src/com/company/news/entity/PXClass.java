package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 培训机构班级表
 * 
 * @author liumingquan
 * 
 */
@Entity
@Table(name = "px_pxclass")
public class PxClass extends AbstractClass {

	@Column
	private String context;// 班级课程详细介绍.
	
	@Column
	private String address;// 上课地点

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
