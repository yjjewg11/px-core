package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fs_friendship_tel")
/**
朋友电话备份
 * @author liumingquan
 *
 */
public class FSFriendshipTel extends IdEntity {
	@Column
	private String create_useruuid;// 关联家庭uuid
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String friend_uuid;// 关联人uuid(未注册,已注册)
	@Column
	private String friend_name;// 关联人uuid
	@Column
	private String tel;// 电话号码。
	@Column
	private Integer status=0;//类型'0:未加好友,1:已添加好友.2:屏蔽',
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFriend_uuid() {
		return friend_uuid;
	}
	public void setFriend_uuid(String friend_uuid) {
		this.friend_uuid = friend_uuid;
	}

}
