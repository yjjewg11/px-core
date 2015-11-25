package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_group_auth") 
/**
 * 机构认证表
 * @author liumingquan
 *
 */
public class GroupAuth extends IdEntity{

	  @Column
	  private String group_uuid;//关联机构uuid
	 
	  @Column
	  private String company_name;//公司全称.长度45
	  @Column
	  private String address;//地址.长度128
	 
	  @Column
	  private String license_num;//营业执照号.长度20
	  @Column
	  private String legal_name;//法人姓名.长度45
	  @Column
	  private String link_tel;//公司电话.
	  @Column
	  private Integer status;//类型：0:未审核.1:提交审核,2:审核驳回.9:审核通过,允许公告.
	  @Column
	  private String reject_msg;//审核驳回时,填写的理由.长度300
	  
	  @Column
	  private Timestamp create_time;//创建时间
	  @Column
	  private Timestamp check_time;//创建时间
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicense_num() {
		return license_num;
	}
	public void setLicense_num(String license_num) {
		this.license_num = license_num;
	}
	public String getLegal_name() {
		return legal_name;
	}
	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}
	public String getLink_tel() {
		return link_tel;
	}
	public void setLink_tel(String link_tel) {
		this.link_tel = link_tel;
	}
	public String getReject_msg() {
		return reject_msg;
	}
	public void setReject_msg(String reject_msg) {
		this.reject_msg = reject_msg;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Timestamp check_time) {
		this.check_time = check_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
