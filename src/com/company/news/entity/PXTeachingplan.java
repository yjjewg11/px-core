package com.company.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 培训机构
 * @author liumingquan
 *
 */
@Entity
@Table(name="px_pxteachingplan") 
public class PXTeachingplan extends IdEntity{
		@Transient
		private Long count;// 统计浏览次数.//非数据库字段.
	  @Column
	  private Date plandate;//时间.年月日,时分秒.
	  @Column
	  private String context;//课程详细内容.不限字数
	  @Column
	  private String readyfor;//需要学生准备的工具.不限字数
	  @Column
	  private String classuuid;//关联班级uuid(显示班级名)
	  @Column
	  private String create_useruuid;
}
