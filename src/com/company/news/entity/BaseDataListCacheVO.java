package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="px_basedatalist") 
public class BaseDataListCacheVO  extends IdEntity {
	  @Column
	  private String typeuuid;//
	  @Column
	  private String datavalue;//
	  @Column
	  private Integer datakey;//
	  
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

}
