package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 对外公布授课老师
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_pxteacher")
public class PxTeacher extends IdEntity {
	@Column
	private String groupuuid;// (继承user)电话号码。
	@Column
	private String useruuid;// 关联用户uuid
		@Column
	private String name;// 姓名
		@Column
		private String img;// 显示图片id
	@Column
	private String content;// html内容介绍
	@Column
	private Integer ct_stars;// 有效值,0-50.5星评价后计算平均值.(默认值30).
	@Column
	private String summary;// 摘要,100字内.
	@Column
	private String course_uuid;// 教授课程
	@Column
	private Integer status;// 是否公开发布.1:未发布.0:发布.同课程发布.
	@Column
	private Timestamp update_time;//更新时间
	@Transient
	private String course_title;//教授课程,显示名
	
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCt_stars() {
		return ct_stars;
	}
	public void setCt_stars(Integer ct_stars) {
		this.ct_stars = ct_stars;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCourse_uuid() {
		return course_uuid;
	}
	public void setCourse_uuid(String course_uuid) {
		this.course_uuid = course_uuid;
	}
	public String getCourse_title() {
		return course_title;
	}
	public void setCourse_title(String course_title) {
		this.course_title = course_title;
	}

	
}
