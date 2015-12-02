package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_vote_topic")
/**
 * 投票题目
 * @author liumingquan
 *未启用
 */
@Deprecated
public class VoteTopic extends IdEntity {

	@Column
	private Timestamp create_time;// 评价时间
	@Column
	private String title;// 300个字符串.
	@Column
	private String content;// 内容(HTML)
	@Column
	private String create_user;// 创建人
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private Long count;//参与投票人数.
	
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}


}
