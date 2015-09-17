package com.company.news.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="px_group_heartbeat") 
/**
 * 门禁同步程序心跳
 * @author liumingquan
 *
 */
public class GroupHeartBeat  extends IdEntity {
	  @Column
	  private String group_uuid;//学校id
	  @Column
	  private String app_id;//配置的设备id.设备标号,用于区分多个
	  @Column
	  private String frequency;//心跳频率
	  @Column
	  private String msg;//返回信息
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
