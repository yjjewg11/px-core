package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户基本信息表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name="px_studentcontactrealation") 
/**
 * 用于邀请家长,和家长注册时关联用
 * @author liumingquan
 */
public class StudentContactRealation extends IdEntity {
	 @Column
	  private String student_uuid;//学生关联uuid
	 @Column
	  private String student_name;//学生关联姓名
	 @Column
	  private Integer type;////1:妈妈,2:爸爸,3:爷爷,4:奶奶,5:外公,6:外婆,7:其他
	 @Column
	  private String typename;////1:妈妈,2:爸爸,3:爷爷,4:奶奶,5:外公,6:外婆,7:其他
	  @Column
	  private String tel;//权限名
	  @Column
	  private Integer isreg;//0.表示该手机还没注册,可以发起邀请家长.1表示,已经注册了不能发起邀请
	  @Column
	private Timestamp update_time;// 更新时间,修改学生资料时如果tel有变化,就更新该时间.
	  @Column
	private Timestamp invite_time;// 邀请时间记录
	  @Column
	  private Integer invite_count;//邀请次数记录
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getIsreg() {
		return isreg;
	}
	public void setIsreg(Integer isreg) {
		this.isreg = isreg;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getStudent_uuid() {
		return student_uuid;
	}
	public void setStudent_uuid(String student_uuid) {
		this.student_uuid = student_uuid;
	}
	public Timestamp getInvite_time() {
		return invite_time;
	}
	public void setInvite_time(Timestamp invite_time) {
		this.invite_time = invite_time;
	}
	public Integer getInvite_count() {
		return invite_count;
	}
	public void setInvite_count(Integer invite_count) {
		this.invite_count = invite_count;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}
