package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "kd_photo_item")
/**
 * 照片条目(照片相关属性)
 * 家庭照片集合.-关联家庭成员(读写)-关联订阅成员(只读)
 * @author liumingquan
 *
 */
public class KDPhotoItem extends IdEntity {
	
	@Column
	private String group_uuid;// 关联家庭uuid
	@Column
	private String class_uuid;// 关联家庭uuid
	@Column
	private Integer status=0;// 状态：0，正常,1：修改，2：删除
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private Timestamp update_time;// 创建时间
	@Column
	private Timestamp photo_time;// 拍摄时间
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private String path;// 相对路径.128.fp/uuid.png
	@Column
	private String label;// 标签 长度45
//	@Column label
//	private String content_type;// [必填]文件类型："image/jpg","image/jpeg","image/png","image/gif"
	@Column
	private Long file_size;// [必填]文件大小
	@Column
	private String address;//拍摄地址45字
	@Column
	private String note;// 描述.300字符
	@Column
	private String md5;// 唯一表示
	@Column
	private String phone_type;//手机型号.//iPhone 6s,HTC 123
	@Column
	private String phone_uuid;//手机唯一标识
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}
	public String getClass_uuid() {
		return class_uuid;
	}
	public void setClass_uuid(String class_uuid) {
		this.class_uuid = class_uuid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
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
	public Long getFile_size() {
		return file_size;
	}
	public void setFile_size(Long file_size) {
		this.file_size = file_size;
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
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getPhone_type() {
		return phone_type;
	}
	public void setPhone_type(String phone_type) {
		this.phone_type = phone_type;
	}
	public String getPhone_uuid() {
		return phone_uuid;
	}
	public void setPhone_uuid(String phone_uuid) {
		this.phone_uuid = phone_uuid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}