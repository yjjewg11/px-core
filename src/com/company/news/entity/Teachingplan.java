package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.company.news.query.PageQueryResult;
import com.company.news.vo.DianzanListVO;

@Entity
@Table(name="px_teachingplan") 
public class Teachingplan extends IdEntity{
	@Transient
	private Long count;// 统计浏览次数.//非数据库字段.
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
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}
