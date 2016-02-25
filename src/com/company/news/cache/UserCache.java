package com.company.news.cache;

import java.io.Serializable;

import com.company.news.SystemConstants;
import com.company.news.interfaces.SessionUserInfoInterface;
import com.company.news.json.JSONUtils;

/**
 * 用户转换为json string 存到redis里面。尽量减少长度，
 * @author liumingquan
 *
 */
public class UserCache implements   Serializable{
	private String l;//loginname;// 登录名。手机号码或邮箱
	private String n;//name;// 昵称
	private String i;//img;// 头像。
	private Integer f;//0:SystemConstants.Session_User_Login_Type_Teacher表示老师,1：表示家长.2:禁用
	//
	
	public String getL() {
		return l;
	}
	public void setL(String l) {
		this.l = l;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public String getI() {
		return i;
	}
	public void setI(String i) {
		this.i = i;
	}
	public Integer getF() {
		return f;
	}
	public void setF(Integer f) {
		this.f = f;
	}
	public static void main(String[] s){
		UserCache d=new UserCache();
		 d.setL("dddd");
		 
		System.out.print(JSONUtils.getJsonString(d));
	}
}
