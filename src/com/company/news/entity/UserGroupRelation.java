package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户基本信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_usergrouprelation")
public class UserGroupRelation extends IdEntity {

	@Column
	private String groupuuid;// 登录名。手机号码或邮箱
	@Column
	private String useruuid;// 昵称
	
	@Column
	private Integer disable;// 是否被管理员禁用学校关系。0：不禁用。1：禁用
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	public Integer getDisable() {
		return disable;
	}
	public void setDisable(Integer disable) {
		this.disable = disable;
	}

}
