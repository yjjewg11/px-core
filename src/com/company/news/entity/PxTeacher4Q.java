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
public class PxTeacher4Q extends IdEntity {
	@Column
	private String groupuuid;// (继承user)电话号码。
	@Column
	private String useruuid;// 关联用户uuid
		@Column
	private String name;// 姓名
		@Column
		private String img;// 显示图片id
	@Column
	private Integer ct_stars;// 有效值,0-50.5星评价后计算平均值.(默认值30).
	  @Column
		private Long ct_stars_count;//记录5星评价的总数.ct_stars=(((ct_stars*ct_stars_count)+new_ct_start)/(ct_stars_count+1)+0.5)*10
		
	@Column
	private String summary;// 简介,100字内.
	@Column
	private Integer type;// 类型.英语,舞蹈,美术
	@Column
	private Integer status;// 是否公开发布.1:未发布.0:发布.同课程发布.
	@Column
	private Timestamp update_time;//更新时间

	@Transient
	private String course_uuids;//教授课程,uuid.多个逗号分割
	@Column
	private String course_title;//教授课程,45字内.多个逗号分割
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getCt_stars_count() {
		return ct_stars_count;
	}
	public void setCt_stars_count(Long ct_stars_count) {
		this.ct_stars_count = ct_stars_count;
	}

	public String getCourse_title() {
		return course_title;
	}
	public void setCourse_title(String course_title) {
		this.course_title = course_title;
	}
	@Transient
	public String getCourse_uuids() {
		return course_uuids;
	}
	public void setCourse_uuids(String course_uuids) {
		this.course_uuids = course_uuids;
	}
}
