package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 上传文件，头像，身份证，认证照片
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_upload")
public class UploadFile4Q extends IdEntity {

	@Column
	private String file_path;// [必填]相对路径
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}


}
