package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_basedatalist") 
public class BaseDataList extends IdEntity{
	

	  @Column
	  private String typeuuid;//分类名
	  @Column
	  private String datavalue;//值
	  @Column
	  private Integer datakey;//key
	  @Column
	  private String description;//描述或其他
	  @Column
	  private Integer enable;//0:禁用.1:显示
	  @Column
	  private Integer ind;//排序默认0.
	  
	public String getTypeuuid() {
		return typeuuid;
	}
	public void setTypeuuid(String typeuuid) {
		this.typeuuid = typeuuid;
	}

	public String getDatavalue() {
		return datavalue;
	}
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}
	public Integer getDatakey() {
		return datakey;
	}
	public void setDatakey(Integer datakey) {
		this.datakey = datakey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Integer getInd() {
		return ind;
	}
	public void setInd(Integer ind) {
		this.ind = ind;
	}
	  


}
