package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 报表-学生和老师 考勤月报表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_stat_month_attendance")
public class StatMonthAttendance extends IdEntity {
	@Column
	private String useruuid;// 
	private String username;// 
	private Integer type;//0,学生,1,老师 
	@Column
	private String groupuuid;// 学校uuid
	@Column
	private String classuuid;// 班级uuid
	@Column
	private String yyyy_mm;// 年-月.格式 2015-09
	private String jsonstr;//jsonstr.格式.{"d_01":"13:56-16:56","d_02":"13:56-16:56"}
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getClassuuid() {
		return classuuid;
	}
	public void setClassuuid(String classuuid) {
		this.classuuid = classuuid;
	}
	public String getJsonstr() {
		return jsonstr;
	}
	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	public String getYyyy_mm() {
		return yyyy_mm;
	}
	public void setYyyy_mm(String yyyy_mm) {
		this.yyyy_mm = yyyy_mm;
	}
	
	
	//一月31天,每天上下午.格式d_天数(1-31)_上下午(1-2)
//	private String d_01_01;//  1号上午
//	private String d_01_02;//  1号下午
//	
//	private String d_02_01;//  1号上午
//	private String d_02_02;//  1号下午
//	
//	private String d_02_01;//  1号上午
//	private String d_02_02;//  1号下午
	
	

}
