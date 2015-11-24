package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="px_pushmessage") 
public class PushMessage extends IdEntity{
	
	 @Column
	  private String group_uuid;//幼儿园uuid

	  @Column
	  private Timestamp create_time;//创建时间

	  @Column
	  private String revice_useruuid;//接收人
	  @Column
	  private String title;//标题
	  @Column
	  private String message;//内容
	  @Column
	  private Integer type;//类型,标识模块.模块类型.0:公告,3:精品文章.4:招生计划.5:课程表.6:食谱.99:班级互动.
	  @Column
	  private String rel_uuid;//模块下的uuid
	  @Column
	  private Integer isread;//是否阅读
	  @Column
	  private String url;//访问地址.
	  @Column
	  private Integer count;//记录rel_uuid相同情况下,为阅读的次数.举例.互动回复了10条记录.点击看了后,清空为0.
//	  @Column
//	  private String send_useruuid;//发送人
//	  @Column
//	  private String revice_user;//接收人
//	  @Column
//	  private String group_name;//组织uuid
//	  @Column
//	  private String group_img;//组织uuid
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getRevice_useruuid() {
		return revice_useruuid;
	}
	public void setRevice_useruuid(String revice_useruuid) {
		this.revice_useruuid = revice_useruuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsread() {
		return isread;
	}
	public void setIsread(Integer isread) {
		this.isread = isread;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRel_uuid() {
		return rel_uuid;
	}
	public void setRel_uuid(String rel_uuid) {
		this.rel_uuid = rel_uuid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
