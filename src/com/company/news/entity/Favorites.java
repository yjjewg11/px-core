package com.company.news.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_favorites")
public class Favorites extends IdEntity {

	@Column
	private String user_uuid;// 收藏家长UUid
	@Column
	private Integer type;//参照SystemConstants表的类型定义
	@Column
	private String title;//收藏名称
	@Column
	private String reluuid;//关联的实体UUID
	@Column
	private Date createtime;//收藏时间
	@Column
	private String url;//收藏的外部URL
	@Column
	private String show_uuid;//发布者uuid
	@Column
	private String show_name;//发布者姓名
	@Column
	private String show_img;//发布者头像
	
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReluuid() {
		return reluuid;
	}
	public void setReluuid(String reluuid) {
		this.reluuid = reluuid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShow_uuid() {
		return show_uuid;
	}
	public void setShow_uuid(String show_uuid) {
		this.show_uuid = show_uuid;
	}
	public String getShow_name() {
		return show_name;
	}
	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	public String getShow_img() {
		return show_img;
	}
	public void setShow_img(String show_img) {
		this.show_img = show_img;
	}
	
	
}
