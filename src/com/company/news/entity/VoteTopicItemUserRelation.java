package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_vote_topic_item_user_relation")
/**
 * 投票条目_用户投票,并评论.
 * @author liumingquan
 *
 */
@Deprecated
public class VoteTopicItemUserRelation extends IdEntity {
	@Column
	private String item_uuid;//关联题目条目uuid
	@Column
	private Timestamp create_time;// 评价时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private Integer vote_status;//0:赞成.1:反对
	public String getItem_uuid() {
		return item_uuid;
	}
	public void setItem_uuid(String item_uuid) {
		this.item_uuid = item_uuid;
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
	public Integer getVote_status() {
		return vote_status;
	}
	public void setVote_status(Integer vote_status) {
		this.vote_status = vote_status;
	}
}
