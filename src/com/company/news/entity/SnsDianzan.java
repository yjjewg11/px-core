package com.company.news.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sns_dianzan")
/**
 * 话题板块
 * @author liumingquan
 *rel_uuid,user_uuid 联合主键
 */
@Deprecated
public class SnsDianzan  implements Serializable {
	@Column
	private String rel_uuid;//分类id
	@Column
	private String user_uuid;//分类id
	@Column
	private Integer status;//1:yes.2:no
	@Column
	private Timestamp create_time;//创建时间,删除多少以前的.
	
	public String getRel_uuid() {
		return rel_uuid;
	}
	public void setRel_uuid(String rel_uuid) {
		this.rel_uuid = rel_uuid;
	}
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}
