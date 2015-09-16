package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 学生绑定卡信息
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_studentbind")
public class StudentBind extends IdEntity {
	//门禁要求最大18未唯一用户标识
	@Column
	private String userid;// 用户唯一标识(同一学校唯一)
	@Column
	private String groupuuid;// 学校uuid
	
	@Column
	private String studentuuid;// 学生guuid
	@Column
	private String cardid;// 门禁系统,卡号id(卡上面显示的卡号).
	@Column
	private String card_factory;// 门禁系统,IC原始卡号.
	@Column
	private String create_user;// 绑定人 
	@Column
	private String create_useruuid;// 绑定人 uuid
	@Column
	private Integer type;// 
	@Column
	private Date createtime;// 最后一次登录时间。
	@Column
	private String note;// 
	@Column
	private String name;// 卡号绑定的用户名
	public String getStudentuuid() {
		return studentuuid;
	}
	public void setStudentuuid(String studentuuid) {
		this.studentuuid = studentuuid;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard_factory() {
		return card_factory;
	}
	public void setCard_factory(String card_factory) {
		this.card_factory = card_factory;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}

}
