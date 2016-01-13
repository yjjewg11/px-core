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
public class FPPhotoItem extends IdEntity {
	
	@Column
	private String family_uuid;// 关联家庭uuid
	@Column
	private Integer type;// 类型.1:照片,0:按照天归类集合.
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private Timestamp photo_time;// 拍摄时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String path;// 相对路径.128.fp/uuid.png
	
	@Column
	private String content_type;// [必填]文件类型："image/jpg","image/jpeg","image/png","image/gif"
	@Column
	private Long file_size;// [必填]文件大小
	@Column
	private String address;//拍摄地址45字
	@Column
	private String note;// 描述.300字符
	@Column
	private String md5;// 唯一表示
	
	public String getFamily_uuid() {
		return family_uuid;
	}
	public void setFamily_uuid(String family_uuid) {
		this.family_uuid = family_uuid;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getPhoto_time() {
		return photo_time;
	}
	public void setPhoto_time(Timestamp photo_time) {
		this.photo_time = photo_time;
	}
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
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public Long getFile_size() {
		return file_size;
	}
	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
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
