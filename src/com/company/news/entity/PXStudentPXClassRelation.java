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
@Table(name = "px_pxstudentpxclasrselation")
public class PXStudentPXClassRelation extends IdEntity {

	@Column
	private String student_uuid;// 学生uuid
	@Column
	private String class_uuid;// 班级uuid

}
