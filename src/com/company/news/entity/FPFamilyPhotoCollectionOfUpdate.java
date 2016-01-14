package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_family_photo_collection")
/**
 * 家庭照片集合.FPFamilyPhotoCollection
 * -关联家庭成员(读写)FPFamilyMembers
 * -关联订阅成员(只读)(第2期开发)
 * -以天为集合归类照片.FPPhotoDayCollection
 * -照片条目(属性).FPPhotoItem
 * 
 * FPFamilyPhotoCollection(1)->FPPhotoDayCollection(N)->FPPhotoItem(N)
 * FPPhotoDayCollection(1)->FPPhotoItem(N)
 * FPFamilyPhotoCollection(1)->FPFamilyMembers(N)
 * 
 * @author liumingquan
 *
 */
public class FPFamilyPhotoCollectionOfUpdate extends IdEntity {
	
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String herald;// 封面.
	@Column
	private String title;// 家庭照片集合.128字符.XX的家
//	@Column
//	private Long photo_count=0l;// 照片计数
	@Column
	private Integer status=0;//类型'0:发布,1:未发布.2:屏蔽',
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getCreate_useruuid() {
		return create_useruuid;
	}
	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}
	public String getHerald() {
		return herald;
	}
	public void setHerald(String herald) {
		this.herald = herald;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	  

}
