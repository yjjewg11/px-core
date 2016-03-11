package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "utl_wenxin")
/**
 * 刷新access_token有效期
access_token是调用授权关系接口的调用凭证，由于access_token有效期（目前为2个小时）较短，当access_token超时后，可以使用refresh_token进行刷新，access_token刷新结果有两种：
1. 若access_token已超时，那么进行refresh_token会获取一个新的access_token，新的超时时间；
2. 若access_token未超时，那么进行refresh_token不会改变access_token，但超时时间会刷新，相当于续期access_token。
refresh_token拥有较长的有效期（30天），当refresh_token失效的后，需要用户重新授权。
 * @author liumingquan
 *
 */
public class UserThirdLoginWenXin  extends IdEntity {
	@Column
	private String rel_useruuid;//  关联当前用户的uuid
	@Column
	private String appid;// 应用唯一标识，在微信开放平台提交应用审核通过后获得
//	@Column
//	private String scope;// 应用授权作用域，如获取用户个人信息则填写snsapi_userinfo
//	@Column
//	private String state;// 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
//	@Column
//	private String code;// 用户换取access_token的code，仅在ErrCode为0时有效
	@Column
	private String access_token;// 接口调用凭证
	@Column
	private Long expires_in;// access_token接口调用凭证超时时间，单位（秒）
	@Column
	private String refresh_token;// 用户刷新access_token
	@Column
	private String openid;// 授权用户唯一标识.
	@Column
	private String unionid;//  当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
	
	@Column
	private String nickname;//  普通用户昵称
	
	@Column
	private Integer sex;//  用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	
	@Column
	private String province;// 普通用户个人资料填写的省份
	
	@Column
	private String city;//  普通用户个人资料填写的城市

	@Column
	private String country;// 国家，如中国为CN

	@Column
	private String headimgurl;//  用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空

	public String getRel_useruuid() {
		return rel_useruuid;
	}

	public void setRel_useruuid(String rel_useruuid) {
		this.rel_useruuid = rel_useruuid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	
	

}
