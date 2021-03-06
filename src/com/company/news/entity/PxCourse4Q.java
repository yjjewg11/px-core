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
	@Transient
	private Group group;// 关联的机构
	@Transient
	private String distance;// 距离，KM单位
	@Column
	private String logo;//
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
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
	@Column
	private Integer ct_stars;//有效值,0-50.5星评价后计算平均值.(默认值30).
	@Column
	private Long ct_study_students;//统计已经学习过的学生数量
	@Column
	private Integer age_min;//有效年龄范围最小值（不能小于0）
	@Column
	private Integer age_max;//有效年龄范围最大值
	public Integer getAge_min() {
		return age_min;
	}
	public void setAge_min(Integer age_min) {
		this.age_min = age_min;
	}
	public Integer getAge_max() {
		return age_max;
	}
	public void setAge_max(Integer age_max) {
		this.age_max = age_max;
	}
	@Transient
	public String getDistance() {
		return distance;
	}
	@Transient
	public void setDistance(String distance) {
		this.distance = distance;
	}
	@Transient
	public Group getGroup() {
		return group;
	}
	@Transient
	public void setGroup(Group group) {
		this.group = group;
	}
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
//	public String getContext() {
//		return context;
//	}
//	public void setContext(String context) {
//		this.context = context;
//	}

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
	public Integer getCt_stars() {
		return ct_stars;
	}
	public void setCt_stars(Integer ct_stars) {
		this.ct_stars = ct_stars;
	}
	public Long getCt_study_students() {
		return ct_study_students;
	}
	public void setCt_study_students(Long ct_study_students) {
		this.ct_study_students = ct_study_students;
	}

}
