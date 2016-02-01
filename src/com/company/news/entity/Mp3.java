package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_mp3")
/**
 *  *rel_uuid,user_uuid 联合主键.不使用uuid
 *  直接使用sql
 * @author liumingquan
 *
 */
public class Mp3 extends IdEntity {

	@Column
	private String title;//20字符
	@Column
	private String path;// 1024
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	


}
