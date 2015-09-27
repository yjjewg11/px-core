package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 对外发布课程
 * @author liumingquan
 */
@Entity
@Table(name = "px_pxcourse")
public class PxCourseCache extends IdEntity {
	
	@Column
	private String groupuuid;//培训机构uuid
	@Column
	private Integer type;// 类型.英语,舞蹈,美术
	@Column
	private String title;//标题(100字内)
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
