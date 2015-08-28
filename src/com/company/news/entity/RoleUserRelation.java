package com.company.news.entity;

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
	  



}
