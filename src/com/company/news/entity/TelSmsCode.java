package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;




@Entity
@Table(name="px_telsmscode") 
public class TelSmsCode extends IdEntity{

    @Column
  private String tel;//电话
  @Column
  private String code;//验证码
  @Column
  private Timestamp createtime;//创建时间
  @Column
  private Integer type;//0:老师账号 1：家长账号
  
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Timestamp createtime) {
    this.createtime = createtime;
  }
  
}
