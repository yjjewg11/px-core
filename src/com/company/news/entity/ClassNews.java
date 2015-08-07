package com.company.news.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.company.news.query.PageQueryResult;
import com.company.news.vo.DianzanListVO;

@Entity
@Table(name = "px_classnews")
public class ClassNews extends IdEntity {
	@Transient
	private String share_url;// 分享地址.//非数据库字段.
	@Transient
	private Integer count;// 统计浏览次数.//非数据库字段.
	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private Timestamp reply_time;// 回复时间
	@Column
	private Timestamp update_time;// 更新时间
	@Column
	private String classuuid;// 关联班级uuid(显示班级名称)
	@Column
	private String title;// 标题
	@Column
	private String content;// 内容(ubb格式)

	@Column
	private String create_user;// 创建人
	@Column
	private String create_useruuid;// 创建人uuid
	@Column
	private Integer usertype;//0:老师 1：家长
	@Column
	private String imgs;// img 的uuid多个逗号分割.
	@Transient
	private List imgsList;// 统计浏览次数.//非数据库字段.
	@Transient
	private PageQueryResult replyPage;// 回复第一页数据
	@Transient
	private DianzanListVO dianzan;// 点赞数据
	@Transient
	public DianzanListVO getDianzan() {
		return dianzan;
	}

	public void setDianzan(DianzanListVO dianzan) {
		this.dianzan = dianzan;
	}
	@Transient
	public PageQueryResult getReplyPage() {
		return replyPage;
	}

	public void setReplyPage(PageQueryResult replyPage) {
		this.replyPage = replyPage;
	}
	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Timestamp getReply_time() {
		return reply_time;
	}

	public void setReply_time(Timestamp reply_time) {
		this.reply_time = reply_time;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getClassuuid() {
		return classuuid;
	}

	public void setClassuuid(String classuuid) {
		this.classuuid = classuuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	@Transient
	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
	@Transient
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	@Transient
	public List getImgsList() {
		return imgsList;
	}

	public void setImgsList(List imgsList) {
		this.imgsList = imgsList;
	}
	

}
