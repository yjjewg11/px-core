package com.company.news.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "px_dynamicmenu")
public class DynamicMenu extends IdEntity {

	@Column
	private String name;// 权限名
	@Column
	private String iconUrl;// 图标
	@Column
	private String url;//访问地址
	@Column
	private Integer index;//顺序
	@Column
	private Integer enable;//是否启用
	@Column
	private Integer type;//1:表示家长版本,2表示老师版本.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	

}
