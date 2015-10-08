package com.company.news.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 培训机构-学生表.(去掉班级uuid字段,可以关联多个班级.)
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_pxstudent")
public class PxStudent extends AbstractStudent {


}
