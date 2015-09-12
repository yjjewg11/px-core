package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 培训机构班级表
 * @author liumingquan
 *
 */
@Entity
@Table(name = "px_pxclass")
public class PXClass extends IdEntity {

	  @Column
	  private Timestamp create_time;//创建时间
	  @Column
	  private String name;//班级名称
	  @Column
	  private String groupuuid;//培训机构
	  @Column
	  private String create_user;//创建人
	  @Column
	  private String create_useruuid;//创建人id
	  @Column
	  private String teacher_uuid;//老师uuid,最多3个.(45+1)*3=
	  @Column
	  private String teacher_name;//老师名称,最多3个.(45+1)*3=
	  @Column
	  private String context;//班级课程详细介绍.

}
