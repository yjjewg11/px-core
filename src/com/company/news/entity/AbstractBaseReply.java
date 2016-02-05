package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractBaseReply extends IdEntity {
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String rel_uuid;//关联对象uuid
	@Column
	private String content;// 内容(HTML)
	@Column
	private String to_useruuid;//给指定人回复的回复.
	@Column
	private String create_useruuid;// 回复人(uuid)
	
	@Transient
	private String create_user;// 创建人
	@Transient
	private String create_img;// 创建人头像
	
	@Column
	private Integer status;//类型'0:发布,1:未发布.2:屏蔽',
	@Column
	private Integer type;//点赞类型 0：互动 1：公告 2：课程表 3：食谱
	
	@Column
	  private Long illegal;//举报次数.用于优先审查
	@Column
	private Timestamp illegal_time;// 举报时间
	  
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getRel_uuid() {
		return rel_uuid;
	}
	public void setRel_uuid(String rel_uuid) {
		this.rel_uuid = rel_uuid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTo_useruuid() {
		return to_useruuid;
	}
	public void setTo_useruuid(String to_useruuid) {
		this.to_useruuid = to_useruuid;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Transient
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	@Transient
	public String getCreate_img() {
		return create_img;
	}
	public void setCreate_img(String create_img) {
		this.create_img = create_img;
	}
	public Long getIllegal() {
		return illegal;
	}
	public void setIllegal(Long illegal) {
		this.illegal = illegal;
	}
	public Timestamp getIllegal_time() {
		return illegal_time;
	}
	public void setIllegal_time(Timestamp illegal_time) {
		this.illegal_time = illegal_time;
	}

}
