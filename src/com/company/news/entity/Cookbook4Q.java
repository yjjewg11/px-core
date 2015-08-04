package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_cookbook") 
public class Cookbook4Q extends IdEntity{
	
	  @Column
	  private String name;//品牌名称
	  @Column
	  private String img;//公司全称

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
}
