package com.company.news.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_vote_topic_item")
/**
 * 投票条目
 * @author liumingquan
 *
 */
@Deprecated
public class VoteTopicItem extends IdEntity {
	@Column
	private String topic_uuid;//关联题目uuid
	@Column
	private Timestamp create_time;// 评价时间
	@Column
	private String title;// 300个字符串.
	@Column
	private String create_useruuid;// 创建人uuid
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
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
