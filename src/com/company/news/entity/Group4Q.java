package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_group") 
public class Group4Q extends IdEntity{
	
	  @Column
	  private Timestamp create_time;//创建时间
	  @Column
	  private String brand_name;//品牌名称
	  @Column
	  private String company_name;//公司全称
	  @Column
	  private Integer type;//类型：2，培训机构，1:幼儿园
	  @Column
	  private Integer status;//类型：0:未审核.1:提交审核,2:审核驳回.9:审核通过,允许公告.
	  @Column
	  private String map_point;//坐标
	  @Column
	  private String link_tel;//电话
	  @Column
		private String summary;// 摘要,100字内
	  @Column
	  private String img;//组织logo
	  @Column
		private Integer ct_stars;//有效值,0-50.5星评价后计算平均值.(默认值30).
		@Column
		private Long ct_study_students;//统计已经学习过的学生数量
		
		  @Column
		  private String prov;//省,比如:四川
		  @Column
		  private String city;//市:比如:成都

	public String getLink_tel() {
		return link_tel;
	}
	public void setLink_tel(String linkTel) {
		link_tel = linkTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column
	  private String address;//地址
	  
	  
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp createTime) {
		create_time = createTime;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brandName) {
		brand_name = brandName;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String companyName) {
		company_name = companyName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMap_point() {
		return map_point;
	}
	public void setMap_point(String mapPoint) {
		map_point = mapPoint;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCt_stars() {
		return ct_stars;
	}
	public void setCt_stars(Integer ct_stars) {
		this.ct_stars = ct_stars;
	}
	public Long getCt_study_students() {
		return ct_study_students;
	}
	public void setCt_study_students(Long ct_study_students) {
		this.ct_study_students = ct_study_students;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}


}
