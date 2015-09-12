package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 老师详细信息专用表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "px_userteacher")
public class UserTeacher extends IdEntity {
	@Column
	private String tel;// (继承user)电话号码。
	@Column
	private Integer sex;// (继承user)0:男,1:女
	@Column
	private String realname;// 姓名
	@Column
	private String idcard;// 身份证号码
	@Column
	private String introduction;// 电话号码。
	@Column
	private String useruuid;// 关联用户uuid
	@Column
	private Timestamp birthday;// 最后一次登录时间。
	@Column
	private String zhiwu;// 职务.默认:老师.50字符
	@Column
	private Integer xueli;// 学历.下拉选择,基础数据配置.1.本科,2.大专,3.中专,4.职高,5.硕士,
	@Column
	private Integer youxueqianjiaoyu;//是否具有学前教育专业学历,基础数据配置(yesOrNo).0:否.1:是
	@Column
	private Integer youjiaozige;//是否取得幼教资格证.基础数据配置(yesOrNo).0:否.1:是
	@Column
	private String graduated;// 毕业院校及专业.50个字.
	@Column
	private String teaching_subject;// 所教学科.50个字.
	@Column
	private String professional_title;// 专业技术职称.50个字.默认:无
	@Column
	private String teacher_certificate_number;// 教师资格证编号.20个字.
	@Column
	private String work_type;// 工作类型.默认:专职.填写:专职或兼职.//20个字
	@Column
	private String address;// 家庭住址.100个字.
	@Column
	private String note;// 备注.100个字.
	@Column
	private Timestamp update_time;//最后更新时间
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getZhiwu() {
		return zhiwu;
	}
	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}
	public Integer getXueli() {
		return xueli;
	}
	public void setXueli(Integer xueli) {
		this.xueli = xueli;
	}
	public Integer getYouxueqianjiaoyu() {
		return youxueqianjiaoyu;
	}
	public void setYouxueqianjiaoyu(Integer youxueqianjiaoyu) {
		this.youxueqianjiaoyu = youxueqianjiaoyu;
	}
	public Integer getYoujiaozige() {
		return youjiaozige;
	}
	public void setYoujiaozige(Integer youjiaozige) {
		this.youjiaozige = youjiaozige;
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
	public String getGraduated() {
		return graduated;
	}
	public void setGraduated(String graduated) {
		this.graduated = graduated;
	}
	public String getTeaching_subject() {
		return teaching_subject;
	}
	public void setTeaching_subject(String teaching_subject) {
		this.teaching_subject = teaching_subject;
	}
	public String getProfessional_title() {
		return professional_title;
	}
	public void setProfessional_title(String professional_title) {
		this.professional_title = professional_title;
	}
	public String getTeacher_certificate_number() {
		return teacher_certificate_number;
	}
	public void setTeacher_certificate_number(String teacher_certificate_number) {
		this.teacher_certificate_number = teacher_certificate_number;
	}
	public String getWork_type() {
		return work_type;
	}
	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}


}
