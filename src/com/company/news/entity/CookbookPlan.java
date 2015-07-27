package com.company.news.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="px_cookbookplan") 
public class CookbookPlan extends IdEntity{
	@Transient
	private String share_url;// 分享地址.//非数据库字段.
	@Transient
	private Integer count;// 统计浏览次数.//非数据库字段.
	  @Column
	  private String groupuuid;//幼儿园id(显示名称)
	  @Column
	  private String time_1;//早餐
	  @Column
	  private String time_2;//早上加餐
	  @Column
	  private String time_3;//午餐
	  @Column
	  private String time_4;//下午加餐
	  @Column
	  private String time_5;//晚餐
	  @Column
	  private Date plandate;//日期(年月日)
	  @Column
	  private String analysis;//营养分析(纯文本)
	  @Transient
	  private List list_time_1;//早餐
	@Transient
	  private List list_time_2;//早上加餐
	@Transient
	  private List list_time_3;//午餐
	@Transient
	  private List list_time_4;//下午加餐
	@Transient
	  private List list_time_5;//晚餐


	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public Date getPlandate() {
		return plandate;
	}
	public void setPlandate(Date plandate) {
		this.plandate = plandate;
	}
	public String getTime_1() {
		return time_1;
	}
	public void setTime_1(String time_1) {
		this.time_1 = time_1;
	}
	public String getTime_2() {
		return time_2;
	}
	public void setTime_2(String time_2) {
		this.time_2 = time_2;
	}
	public String getTime_3() {
		return time_3;
	}
	public void setTime_3(String time_3) {
		this.time_3 = time_3;
	}
	public String getTime_4() {
		return time_4;
	}
	public void setTime_4(String time_4) {
		this.time_4 = time_4;
	}
	public String getTime_5() {
		return time_5;
	}
	public void setTime_5(String time_5) {
		this.time_5 = time_5;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	@Transient
	public List getList_time_1() {
		return list_time_1;
	}
	public void setList_time_1(List list_time_1) {
		this.list_time_1 = list_time_1;
	}
	@Transient
	public List getList_time_2() {
		return list_time_2;
	}
	public void setList_time_2(List list_time_2) {
		this.list_time_2 = list_time_2;
	}
	@Transient
	public List getList_time_3() {
		return list_time_3;
	}
	public void setList_time_3(List list_time_3) {
		this.list_time_3 = list_time_3;
	}
	@Transient
	public List getList_time_4() {
		return list_time_4;
	}
	public void setList_time_4(List list_time_4) {
		this.list_time_4 = list_time_4;
	}
	@Transient
	public List getList_time_5() {
		return list_time_5;
	}
	public void setList_time_5(List list_time_5) {
		this.list_time_5 = list_time_5;
	}
	@Transient
	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
	@Transient
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
