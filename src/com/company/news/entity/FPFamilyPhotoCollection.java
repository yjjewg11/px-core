package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_family_photo_collection")
/**
 * 家庭照片集合.
 * -关联家庭成员(读写)
 * -关联订阅成员(只读)
 * -关联照片-以天为集合归类.
 * 
 * @author liumingquan
 *
 */
public class FPFamilyPhotoCollection extends IdEntity {
	
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String create_user;// 创建人
	@Column
	private String herald;// 封面
	@Column
	private String title;// 家庭照片集合.128字符
	@Column
	private Long photo_count;// 照片计数
	 @Column
	  private Integer status;//类型'0:发布,1:未发布.2:屏蔽',
	
	  

}
