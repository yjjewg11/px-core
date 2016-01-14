package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fp_photo_item")
/**
 * 照片条目(照片相关属性)
 * 家庭照片集合.-关联家庭成员(读写)-关联订阅成员(只读)
 * @author liumingquan
 *
 */
public class FPPhotoItemOfUpdate extends IdEntity {
	
	@Column
	private String family_uuid;// 关联家庭uuid
//	@Column
//	private Timestamp create_time;// 创建时间
//	@Column
//	private Timestamp photo_time;// 拍摄时间
//	@Column
//	private String create_useruuid;// 创建人uuid
//	@Column
//	private String path;// 相对路径.128.fp/uuid.png
//	
//	@Column
//	private String content_type;// [必填]文件类型："image/jpg","image/jpeg","image/png","image/gif"
//	@Column
//	private Long file_size;// [必填]文件大小
	@Column
	private String address;//拍摄地址100字
	@Column
	private String note;// 描述.300字符
//	@Column
//	private String md5;// 唯一表示
	public String getFamily_uuid() {
		return family_uuid;
	}
	public void setFamily_uuid(String family_uuid) {
		this.family_uuid = family_uuid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	

}
