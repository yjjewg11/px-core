package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 对外发布课程
 * @author liumingquan
 */
@Entity
@Table(name = "px_pxcourse")
public class PxCourse4Q extends IdEntity {
	@Transient
	private Long count;// 统计浏览次数.//非数据库字段.
	
	@Column
	private String groupuuid;//培训机构uuid
	@Column
	private Integer type;// 类型.英语,舞蹈,美术
	@Column
	private String subtype;//子类型,手动输入:比如拉丁舞.
	@Column
	private String title;//标题(100字内)
	@Column
	private String address;//上课地点.
	@Column
	private String schedule;//课时设置:每次45分钟共20课次.
//	@Column
//	private String context;//课程详细内容介绍.(html).
	@Column
	private Double fees;//收费价格(可以不填写)
	@Column
	private Double discountfees;//优惠价格(可以不填写)
	@Column
	private String status;// 发布状态:0:发布.1:不发布.
	@Column
	private String update_useruuid;//创建人uuid.(后台设置)
	@Column
	private Timestamp updatetime;//更新时间
	@Transient
	public Long getCount() {
		return count;
	}
	@Transient
	public void setCount(Long count) {
		this.count = count;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	public Double getDiscountfees() {
		return discountfees;
	}
	public void setDiscountfees(Double discountfees) {
		this.discountfees = discountfees;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdate_useruuid() {
		return update_useruuid;
	}
	public void setUpdate_useruuid(String update_useruuid) {
		this.update_useruuid = update_useruuid;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}

}
