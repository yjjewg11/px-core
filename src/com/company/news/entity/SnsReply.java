package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sns_section")
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
	@Column
	private String content;// HTML
	@Column
	private Long reply_count;//话题数量
	@Column
	private Long yes_count;//赞成票数.
	@Column
	private Long no_count;//反对票数
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


}
