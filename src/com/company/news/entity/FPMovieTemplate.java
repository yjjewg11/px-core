package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_movie_template")
/**
 *精品照相模版
 * @author liumingquan
 *
 */
public class FPMovieTemplate extends IdEntity {
	
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String key;// 模版key.45
	@Column
	private String title;// 家庭照片集合.128字符.XX的家
	@Column
	private String herald;//128
	@Column
	private Integer status=0;//类型'0:发布,1:未发布.2:屏蔽',
	
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHerald() {
		return herald;
	}
	public void setHerald(String herald) {
		this.herald = herald;
	}//  模版封面.
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
