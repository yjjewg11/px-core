package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sns_topic_vote_item")
/**
 * 话题投票
 * 配置投票人员关联表
 * String insertsql="insert into sns_topic_voteitem_user(topic_uuid,item_uuid,user_uuid) values('"+topic_uuid+"','"+item_uuid+"',"+user_uuid+")";

 * @author liumingquan
 *
 */
public class SnsTopicVoteItem extends IdEntity {
	@Column
	private String topic_uuid;//分类id
	@Column
	private String title;// 话题投票内容.300字符
	@Column
	private Long vote_count=0l;//投票票数.
	 @Column
	private Integer ind;//排序默认0.
	public String getTopic_uuid() {
		return topic_uuid;
	}
	public void setTopic_uuid(String topic_uuid) {
		this.topic_uuid = topic_uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getVote_count() {
		return vote_count;
	}
	public void setVote_count(Long vote_count) {
		this.vote_count = vote_count;
	}
	public Integer getInd() {
		return ind;
	}
	public void setInd(Integer ind) {
		this.ind = ind;
	}
	  
	

}
