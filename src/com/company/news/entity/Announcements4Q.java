package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.company.news.query.PageQueryResult;
import com.company.news.vo.DianzanListVO;

@Entity
@Table(name="px_announcements") 
public class Announcements4Q extends IdEntity{
	
	  @Column
	  private Timestamp create_time;//创建时间
	  @Column
	  private String create_user;//品牌名称
	  @Column
	  private String create_useruuid;//公司全称
	  @Column
	  private Integer isimportant;//类型：'0:普通 1：重要',
	  @Column
	  private String title;//坐标
	  @Column
	  private Integer type;//类型'0:普通通知 1:内部通知 2：班级通知',
	  @Column
	  private String groupuuid;//坐标	  
	  @Column
	  private Integer status;//类型'0:发布,1:未发布.2:屏蔽',
	  @Column
	  private String url;//支持粘贴url
	  @Column
	  private Timestamp start_time;//开始时间
	  @Column
	  private Timestamp end_time;//结束时间
	  @Transient
	  private Long count;//计数
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}
	public Integer getIsimportant() {
		return isimportant;
	}
	public void setIsimportant(Integer isimportant) {
		this.isimportant = isimportant;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Transient
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	  


}
