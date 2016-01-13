package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_family_members")
/**
 * 关联家庭成员
 * 家庭照片集合.-关联家庭成员(读写)-关联订阅成员(只读)
 * @author liumingquan
 *
 */
public class FPFamilyMembers extends IdEntity {
	@Column
	private String family_uuid;// 关联家庭uuid
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String user_uuid;// 关联人uuid
	@Column
	private String tel;// 电话号码。
	@Column
	private String family_name;//家庭称呼.爸爸,妈妈,20限制
	
	public String getFamily_uuid() {
		return family_uuid;
	}
	public void setFamily_uuid(String family_uuid) {
		this.family_uuid = family_uuid;
	}
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
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
