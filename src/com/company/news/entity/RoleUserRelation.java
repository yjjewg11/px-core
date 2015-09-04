package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_roleuserrelation") 
public class RoleUserRelation extends IdEntity{
	

	  @Column
	  private String roleuuid;//角色ID
	  @Column
	  private String useruuid;//用户id
	  
	  @Column
	  private String groupuuid;//幼儿园id.角色关联到用户关联的幼儿园
	  	
	  @Column
	  private String create_user;//品牌名称
	  @Column
	  private String create_useruuid;//公司全称
	  @Column
	  private Timestamp create_time;//创建时间
	  
	public String getRoleuuid() {
		return roleuuid;
	}
	public void setRoleuuid(String roleuuid) {
		this.roleuuid = roleuuid;
	}
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	  



}
