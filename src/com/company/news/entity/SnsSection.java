package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sns_section")
/**
 * 话题板块
 * @author liumingquan
 *
 */
public class SnsSection extends IdEntity {
	@Column
	private Integer id;//分类id
	@Column
	private Timestamp create_time;// 评价时间
	
	@Column
	private String img;// logo图片
	@Column
	private String title;// 话题板块.20字符
//	@Column
//	private Long click_count;// 点击次数.
	@Column
	private Long topic_count;//话题数量
	@Column
	private Integer ind;//排序.倒叙
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public Long getTopic_count() {
		return topic_count;
	}
	public void setTopic_count(Long topic_count) {
		this.topic_count = topic_count;
	}
	public Integer getInd() {
		return ind;
	}
	public void setInd(Integer ind) {
		this.ind = ind;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

}
