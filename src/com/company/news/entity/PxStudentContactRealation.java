package com.company.news.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户基本信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_pxstudentcontactrealation")
/**
 * 用于邀请家长,和家长注册时关联用
 * @author liumingquan
 */
public class PxStudentContactRealation extends AbstractStudentContactRealation {

}
