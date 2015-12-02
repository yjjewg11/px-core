package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sns_section")
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
	private String title;// 话题板块.128字符
	@Column
	private String content;// HTML
	@Column
	private Long reply_count;//话题数量
	@Column
	private Long yes_count;//赞成票数.
	@Column
	private Long no_count;//反对票数
	
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

}
