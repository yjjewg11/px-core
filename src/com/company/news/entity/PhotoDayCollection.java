package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_family_photo_collection")
/**
 * 照片条目
 * 家庭照片集合.-关联家庭成员(读写)-关联订阅成员(只读)
 * @author liumingquan
 *
 */
public class PhotoDayCollection extends IdEntity {
	
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private Timestamp photo_time;// 拍摄设计
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String create_user;// 创建人
	@Column
	private String herald;// 封面
	@Column
	private String content;// 内容.300字符
	@Column
	private Long photo_count;// 照片计数
	@Column
	private String path;// 相对路径
	  

}
