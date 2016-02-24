package com.company.news.session;

import com.company.news.interfaces.SessionUserInfoInterface;

public class UserOfSession implements SessionUserInfoInterface{
	private String uuid;
	private String name;
	private String img;
	private String loginname;
	
	private Integer f;//0:表示老师,1：表示家长.2:禁用
	private String loginType;//登录状态.0.管理后台.1幼儿园,2培训机构,3话题模块登录,允许老师和家长登录.
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Integer getF() {
		return f;
	}
	public void setF(Integer f) {
		this.f = f;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}
