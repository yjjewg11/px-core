package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * 学校喜好数据表保存.
 * 保存学校常用属性.上下班时间.
 * @author liumingquan
 *
 */
@Entity
@Table(name="px_group_habits") 
public class GroupHabits extends IdEntity{
	
	  @Column
	  private String groupuuid;//分类名
	  @Column
	  private String k;//值
	  @Column
	  private String v;//100字内
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
}
