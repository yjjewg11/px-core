package com.company.news.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="px_teachingplan") 
public class Teachingplan extends IdEntity{
	@Transient
	private Integer count;// 统计浏览次数.//非数据库字段.
	  @Column
	  private Date plandate;//日期.年月日
	  @Column
	  private String morning;//上午课程
	  @Column
	  private String afternoon;//下午课程
	  @Column
	  private String classuuid;//关联班级uuid(显示班级名)
	  
		@Column
		  private String create_useruuid;
	  public String getClassuuid() {
		return classuuid;
	}
	public void setClassuuid(String classuuid) {
		this.classuuid = classuuid;
	}
	public Date getPlandate() {
		return plandate;
	}
	public void setPlandate(Date plandate) {
		this.plandate = plandate;
	}
	public String getMorning() {
		return morning;
	}
	public void setMorning(String morning) {
		this.morning = morning;
	}
	public String getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(String afternoon) {
		this.afternoon = afternoon;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}

	@Transient
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
