package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_photo_item")
/**
 * 收藏照片.
 * 
 * 家庭照片集合.-关联家庭成员(读写)-关联订阅成员(只读)
 * @author liumingquan
 *不需要uuid字段.主键为:create_useruuid+path
 *
 */
@Deprecated 
public class FPPhotoFavorites extends IdEntity {

	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String path;// 相对路径.128.fp/uuid.png
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	  

}
