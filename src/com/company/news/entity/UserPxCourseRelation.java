package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 发布老师关联课程表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_userpxcourserelation")
public class UserPxCourseRelation extends IdEntity {
	@Column
	private String courseuuid;//课程uuid
	@Column
	private String useruuid;// 用户uuid
	@Column
	private String groupuuid;// 班级uuid
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getCourseuuid() {
		return courseuuid;
	}
	public void setCourseuuid(String courseuuid) {
		this.courseuuid = courseuuid;
	}

}
