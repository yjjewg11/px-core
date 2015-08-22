package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_mydailytask") 
public class TeacherDailyTask extends IdEntity{
	
	  @Column
	  private Timestamp create_time;//创建时间
	  @Column
	  private String user_uuid;//关联人uuid
	  
	  @Column
	  private String title;//标题
	  @Column
	  private Integer type;//类型.参考模块类型
	  @Column
	  private String rel_uuid;//关联uuid.type=1,2,rel_uuid关联班级uuid..type=3:表示关联幼儿园.用于更新任务状态.
	  @Column
	  private Integer status;//状态:.0:已经完成,1:待完成.2:已过期.
	  @Column
	  private Timestamp update_time;//更新状态时间
	  @Column
	  private String group_uuid;//关联幼儿园
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRel_uuid() {
		return rel_uuid;
	}
	public void setRel_uuid(String rel_uuid) {
		this.rel_uuid = rel_uuid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}

}
