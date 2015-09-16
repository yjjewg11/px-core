package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 培训机构
 * 
 * @author liumingquan
 * 
 */
@Entity
@Table(name = "px_pxteachingplan")
public class PxTeachingplan extends IdEntity {
	@Transient
	private Long count;// 统计浏览次数.//非数据库字段.
	@Column
	private Date plandate;// 时间.年月日,时分秒.
	@Column
	private String context;// 课程详细内容.不限字数
	@Column
	private String readyfor;// 需要学生准备的工具.不限字数
	@Column
	private String classuuid;// 关联班级uuid(显示班级名)
	@Column
	private String create_useruuid;
	@Column
	private String duration;// 课程时长
	@Column
	private String name;// 课程名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Date getPlandate() {
		return plandate;
	}

	public void setPlandate(Date plandate) {
		this.plandate = plandate;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getReadyfor() {
		return readyfor;
	}

	public void setReadyfor(String readyfor) {
		this.readyfor = readyfor;
	}

	public String getClassuuid() {
		return classuuid;
	}

	public void setClassuuid(String classuuid) {
		this.classuuid = classuuid;
	}

	public String getCreate_useruuid() {
		return create_useruuid;
	}

	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
