package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_dianzan")
/**
 *  *rel_uuid,user_uuid 联合主键.不使用uuid
 *  直接使用sql
 * @author liumingquan
 *
 */
@Deprecated
public class FPDianzan extends IdEntity {

	@Column
	private String rel_uuid;//关联对象uuid
	@Column
	private String create_useruuid;// 回复人(uuid)
	@Column
	private Integer type;//点赞类型 0：互动 1：公告 2：课程表 3：食谱
	public String getRel_uuid() {
		return rel_uuid;
	}
	public void setRel_uuid(String rel_uuid) {
		this.rel_uuid = rel_uuid;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}
	
	


}
