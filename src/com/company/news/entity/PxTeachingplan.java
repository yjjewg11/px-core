package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.company.news.query.PageQueryResult;
import com.company.news.vo.DianzanListVO;

/**
 * 培训机构
 * 
 * @author liumingquan
 * 
 */
@Entity
@Table(name = "px_pxteachingplan")
public class PXTeachingplan extends IdEntity {
	
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
	@Column
	private String address;// 上课地点
	

	@Transient
	private PageQueryResult replyPage;// 回复第一页数据
	@Transient
	private DianzanListVO dianzan;// 点赞数据
	@Transient
	public DianzanListVO getDianzan() {
		return dianzan;
	}

	public void setDianzan(DianzanListVO dianzan) {
		this.dianzan = dianzan;
	}
	@Transient
	public PageQueryResult getReplyPage() {
		return replyPage;
	}

	public void setReplyPage(PageQueryResult replyPage) {
		this.replyPage = replyPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Transient
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
