package com.company.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 培训学生与班级关联表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_pxstudentpxclassrelation")
public class PxStudentPXClassRelation extends IdEntity {

	@Column
	private String student_uuid;// 学生uuid
	@Column
	private String class_uuid;// 班级uuid
	public String getStudent_uuid() {
		return student_uuid;
	}
	public void setStudent_uuid(String student_uuid) {
		this.student_uuid = student_uuid;
	}
	public String getClass_uuid() {
		return class_uuid;
	}
	public void setClass_uuid(String class_uuid) {
		this.class_uuid = class_uuid;
	}

}
