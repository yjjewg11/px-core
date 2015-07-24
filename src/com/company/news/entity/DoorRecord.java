package com.company.news.entity;

import java.util.Date;

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
@Table(name = "px_doorrecord")
public class DoorRecord extends IdEntity {

	@Column
	private String cardid;// 卡号
	@Column
	private String equno;// 设备号.
	@Column
	private String doorid;// 读头编号
	@Column
	private String errcode;// 错误类型
	@Column
	private Date dt;// 刷卡时间
	@Column
	private String groupuuid;// 机构UUID
	
	
	public String getGroupuuid() {
		return groupuuid;
	}
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getEquno() {
		return equno;
	}
	public void setEquno(String equno) {
		this.equno = equno;
	}
	public String getDoorid() {
		return doorid;
	}
	public void setDoorid(String doorid) {
		this.doorid = doorid;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
	

}
