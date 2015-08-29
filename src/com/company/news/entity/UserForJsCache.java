package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户基本信息表.用于客户端缓存
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_user")
public class UserForJsCache extends IdEntity  {
	@Column
	private String name;// 昵称
	@Column
	private String tel;// 电话号码。
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
