package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sns_topic")
/**
 * 话题
 * @author liumingquan
 *
 */
public class SnsTopic extends IdEntity {
	@Column
	private Integer section_id;//分类id
	@Column
	private Timestamp create_time;// 评价时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String create_user;// 创建人
	@Column
	private String create_img;// 创建人头像
	@Column
	private String title;// 话题板块.128字符
	@Column
	private String content;// HTML
	@Column
	private String summary;// 从内容中提取100字.
	@Column
	private String imguuids;// 从内容中提取图片uuid,最多3张.3*45+2=137字符
	@Column
	private Long reply_count;//话题数量
	@Column
	private Long yes_count;//赞成票数.
	@Column
	private Long no_count;//反对票数
	@Column
	private Long click_count;//浏览次数
	 @Column
	  private Integer status;//类型'0:发布,1:未发布.2:屏蔽',
	@Column
	private Integer level;//等级.0:普通.1:热帖,9:精华
	
	 @Column
	  private Long illegal;//举报次数.用于优先审查
	  
	  @Column
		private Timestamp illegal_time;// 举报时间
	  
	
	//@Column
//	private Long click_count;// 点击次数.
	public Integer getSection_id() {
		return section_id;
	}
	public void setSection_id(Integer section_id) {
		this.section_id = section_id;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getReply_count() {
		return reply_count;
	}
	public void setReply_count(Long reply_count) {
		this.reply_count = reply_count;
	}
	public Long getYes_count() {
		return yes_count;
	}
	public void setYes_count(Long yes_count) {
		this.yes_count = yes_count;
	}
	public Long getNo_count() {
		return no_count;
	}
	public void setNo_count(Long no_count) {
		this.no_count = no_count;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getIllegal() {
		return illegal;
	}
	public void setIllegal(Long illegal) {
		this.illegal = illegal;
	}
	public Timestamp getIllegal_time() {
		return illegal_time;
	}
	public void setIllegal_time(Timestamp illegal_time) {
		this.illegal_time = illegal_time;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImguuids() {
		return imguuids;
	}
	public void setImguuids(String imguuids) {
		this.imguuids = imguuids;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getCreate_img() {
		return create_img;
	}
	public void setCreate_img(String create_img) {
		this.create_img = create_img;
	}
	public Long getClick_count() {
		return click_count;
	}
	public void setClick_count(Long click_count) {
		this.click_count = click_count;
	}

}
