package com.company.news.cache;

import java.io.Serializable;

import com.company.news.interfaces.SessionUserInfoInterface;
import com.company.news.json.JSONUtils;

/**
 * 用户登录session转换为json string 存到redis里面。尽量减少长度，
 * @author liumingquan
 *
 */
public class SessionUserCache implements   Serializable{
	private String u;//uuid;// uuid
	private String s;//login_type;//登录状态.0.管理后台.1幼儿园,2培训机构,3话题模块登录,允许老师和家长登录.
	private long t;//refresh_time;//刷新时间
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}
}
