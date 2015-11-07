package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_group") 
public class GroupOfUpdate extends IdEntity{
	
	  @Column
	  private Timestamp create_time;//创建时间
	  @Column
	  private String brand_name;//品牌名称
	  @Column
	  private String company_name;//公司全称
	  @Column
	  private Integer type;//类型：2，培训机构，1:幼儿园
	  @Column
	  private Integer status;//类型：0:为审核.1:提交审核,2:审核驳回.9:审核通过,允许公告.
	  @Column
	  private String map_point;//坐标
	  @Column
	  private String link_tel;//电话
	  @Column
	  private String description;//  
	  @Column
	  private String private_key;// 密钥
	  @Column
		private String summary;// 摘要,100字内
	  @Column
	  private String img;//组织logo
	//ct_开头字段更新时,不允许修改.
//	  @Column
//		private Integer ct_stars;//有效值,0-50.5星评价后计算平均值.(默认值30).
//	  @Column
//		private Long ct_stars_count;//记录5星评价的总数.ct_stars=(((ct_stars*ct_stars_count)+new_ct_start)/(ct_stars_count+1)+0.5)*10
//		@Column
//		private Long ct_study_students;//统计已经学习过的学生数量
		  @Column
		  private String prov;//省,比如:四川
		  @Column
		  private String city;//市:比如:成都
		  
		  
		  private Double lng;//经度
		  private Double lat;//纬度
		  
		  
	public String getPrivate_key() {
		return private_key;
	}
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}


}
