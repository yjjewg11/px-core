package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_group") 
public class Group4QBaseInfo extends IdEntity{
	
	  @Column
	  private String brand_name;//品牌名称
	  @Column
	  private String company_name;//公司全称
	  @Column
	  private String img;//组织logo	  
	  @Column
	  private String address;//地址
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
