package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_reply")
public class FPReply extends IdEntity {

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
	@Column
	private Integer type;//点赞类型 0：互动 1：公告 2：课程表 3：食谱
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

}
