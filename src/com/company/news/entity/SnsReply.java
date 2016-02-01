package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sns_reply")
/**
 * 话题(回复)回复
 * @author liumingquan
 *
 */
public class SnsReply extends IdEntity {
	@Column
	private String topic_uuid;// 话题回复
	@Column
	private String reply_uuid;//回复的回复.未null表示直接回复的话题.
	@Column
	private Timestamp create_time;// 评价时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Transient
	private String create_user;// 创建人
	@Transient
	private String create_img;// 创建人头像
	@Column
	private String content;// HTML
	@Column
	private Long reply_count;//回复数量
	@Column
	private Long yes_count;//赞成票数.
	@Column
	private Long no_count;//反对票数
	
	@Column
	  private Integer status;//类型'0:发布,1:未发布.2:屏蔽',
	
	 @Column
	  private Long illegal;//举报次数.用于优先审查
	  
	  @Column
		private Timestamp illegal_time;// 举报时间
	public String getTopic_uuid() {
		return topic_uuid;
	}
	public void setTopic_uuid(String topic_uuid) {
		this.topic_uuid = topic_uuid;
	}
	public String getReply_uuid() {
		return reply_uuid;
	}
	public void setReply_uuid(String reply_uuid) {
		this.reply_uuid = reply_uuid;
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
	@Transient
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	@Transient
	public String getCreate_img() {
		return create_img;
	}
	public void setCreate_img(String create_img) {
		this.create_img = create_img;
	}


}
