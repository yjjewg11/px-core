package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_photo_favorite")
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
	private String rel_uuid;// 创建人uuid
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private Timestamp create_time;// 创建时间
	

}
