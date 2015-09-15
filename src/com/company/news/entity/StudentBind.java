package com.company.news.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 用户基本信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_studentbind")
public class StudentBind  implements Serializable {
	private static final long serialVersionUID = 6030326789124757879L;
	//门禁要求18未唯一用户标识
	@Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "userid", unique = true, nullable = false)
	private Long userid;// 用户唯一标识
	
	
	@Column
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
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
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}

}
