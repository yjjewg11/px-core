package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.company.news.interfaces.CreateUserInterface;
import com.company.news.vo.DianzanListVO;

@Entity
@Table(name = "px_classnewsreply")
public class ClassNewsReply extends IdEntity implements CreateUserInterface {

	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String newsuuid;// 关联互动id
	@Column
	private String content;// 内容(HTML)
	@Column
	private String create_user;// 回复人
	
	@Column
	private String create_useruuid;// 回复人(uuid)
	@Column
	private String create_img;// 创建人头像
	
//	@Column
//	private Timestamp update_time;// 创建时间
	@Column
	private Integer type;//点赞类型 0：互动 1：公告 2：课程表 3：食谱
	@Column
	private Integer usertype;//0:老师 1：家长
	
	@Column
	private Integer status;//类型'0:发布,1:未发布.2:屏蔽',
	@Transient
	private DianzanListVO dianzan;// 点赞数据
	@Transient
	public DianzanListVO getDianzan() {
		return dianzan;
	}

	public void setDianzan(DianzanListVO dianzan) {
		this.dianzan = dianzan;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
//	public Timestamp getUpdate_time() {
//		return update_time;
//	}
//
//	public void setUpdate_time(Timestamp update_time) {
//		this.update_time = update_time;
//	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getNewsuuid() {
		return newsuuid;
	}

	public void setNewsuuid(String newsuuid) {
		this.newsuuid = newsuuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getCreate_useruuid() {
		return create_useruuid;
	}

	public void setCreate_useruuid(String create_useruuid) {
		this.create_useruuid = create_useruuid;
	}

	public String getCreate_img() {
		return create_img;
	}

	public void setCreate_img(String create_img) {
		this.create_img = create_img;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
