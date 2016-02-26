package com.company.news.core.iservice;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.company.news.SystemConstants;
import com.company.news.commons.util.PxStringUtil;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.PushMessage;
import com.company.news.rest.util.DBUtil;
import com.company.news.rest.util.TimeUtils;

/**
 * //1.保存及时消息. //2.推送及时消息.
 * 
 * @author liumingquan
 * 
 */
@Service
public class PushMsgIservice {

	protected static Logger logger = LoggerFactory
			.getLogger(PushMsgIservice.class);
	@Autowired
	@Qualifier("NSimpleHibernateDao")
	protected NSimpleHibernateDao nSimpleHibernateDao;

	
	@Autowired
	protected PushMsgToAPPIservice pushMsgToAPPIservice;

	/**
	 * 获取group的类型
	 * @param group_uuid
	 * @return
	 */
	private Integer getGroupType(String group_uuid){
		String sql="select type from px_group where uuid='"+group_uuid+"'";
//		String hql = "select distinct device_id from px_pushmsgdevice where status=0 and device_type='"
//				+ device_type + "'";
//		hql += " and type=" + type;
//		hql += " and group_uuid=?";

//		 this.nSimpleHibernateDao.getHibernateTemplate().find(hql,
//				group_uuid);
		 
		 List list= this.nSimpleHibernateDao.createSqlQuery(sql).list();
		 if(list.size()==0)return null;
		return Integer.valueOf(list.get(0)+"");
	}
	

	static int msg_max_length = 120;

	
	static Map<Integer,String> TypeNameMap=new HashMap();
	static{
		TypeNameMap.put(SystemConstants.common_type_gonggao, "全校公告");
		TypeNameMap.put(SystemConstants.common_type_neibutongzhi, "老师公告");
		TypeNameMap.put(SystemConstants.common_type_messageTeaher, "信件");
		TypeNameMap.put(SystemConstants.common_type_messageKD, "园长信件");
		
		TypeNameMap.put(SystemConstants.common_type_hudong, "互动");
		TypeNameMap.put(SystemConstants.common_type_shipu, "每日食谱");
		TypeNameMap.put(SystemConstants.common_type_signrecord, "打卡签到");
		TypeNameMap.put(SystemConstants.common_type_jiaoxuejihua, "课程表");
		TypeNameMap.put(SystemConstants.common_type_pxteachingPlan, "特长课程表");
		
		
	}
	
	
	private static String getPushMsgTitleByType(int type) {
		String title =TypeNameMap.get(type);
		if (title==null)title = "消息";
		return title;
	}
	/**
	 * 广播所有消息_给所有老师(group_uuid)
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToAll_to_teacher(int type, String type_uuid,
			String group_uuid, String msg) throws Exception {
		String title = this.getPushMsgTitleByType(type);
		List<String> list = (List<String>) this.nSimpleHibernateDao
				.getHibernateTemplate()
				.find("select DISTINCT useruuid from UserGroupRelation where groupuuid=?",
						group_uuid);
		
		Timestamp nowTime=TimeUtils.getCurrentTimestamp();
		
		for (String o : list) {
			PushMessage pushMessage = new PushMessage();
			pushMessage.setGroup_uuid(group_uuid);
			pushMessage.setRevice_useruuid(o);
			pushMessage.setType(type);
			pushMessage.setRel_uuid(type_uuid);
			pushMessage.setTitle(title);

			pushMessage.setMessage(PxStringUtil.getSubString(msg,
					msg_max_length));
			pushMessage.setCreate_time(nowTime);
			pushMessage.setIsread(0);

			this.nSimpleHibernateDao.save(pushMessage);
		}
		this.logger.info("pushMsgToAll_to_teacher count=" + list.size());
		pushMsgToAPPIservice.pushMsgToAll_to_teacher_app(group_uuid, title, msg);
		
	}


	/**
	 * 广播所有消息班级关联的家长(幼儿园)
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToParentByClass(int type, String type_uuid,
			String class_uuids, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		Session s = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();

		// from StudentContactRealation where student_uuid in( select uuid from
		// Student where classuuid =?)
		String sql = "select DISTINCT parent_uuid from px_studentcontactrealation  where student_uuid in ( select uuid from px_student where  classuuid in("
				+ DBUtil.stringsToWhereInValue(class_uuids) + "))";
		Query q = s.createSQLQuery(sql);
		List<String> parentuuidlist = q.list();

		this.pushMsgToParentByParentuuidList(type, type_uuid, parentuuidlist,
				msg);
	}

	/**
	 * 广播所有消息班级关联的家长(教育机构)
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToParentByPxClass(int type, String type_uuid,
			String class_uuids, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		Session s = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();

		// from StudentContactRealation where student_uuid in( select uuid from
		// Student where classuuid =?)
		String sql = "select DISTINCT parent_uuid from px_pxstudentcontactrealation  where student_uuid in ( select uuid from px_pxstudent where  classuuid in("
				+ DBUtil.stringsToWhereInValue(class_uuids) + "))";
		Query q = s.createSQLQuery(sql);
		List<String> parentuuidlist = q.list();
		pushMsgToParentByParentuuidList(type, type_uuid, parentuuidlist, msg);
	}

	
	/**
	 * 广播所有消息班级关联的家长(幼儿园)
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToParentByStudent(int type, String type_uuid,
			String student_uuids, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		Session s = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();

		// from StudentContactRealation where student_uuid in( select uuid from
		// Student where classuuid =?)
		String sql = "select DISTINCT parent_uuid from px_studentcontactrealation  where student_uuid in ("+DBUtil.stringsToWhereInValue(student_uuids)+")";
		Query q = s.createSQLQuery(sql);
		List<String> parentuuidlist = q.list();

		this.pushMsgToParentByParentuuidList(type, type_uuid, parentuuidlist,
				msg);
	}

	/**
	 * 广播所有消息班级关联的家长(教育机构)
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToParentByPxStudent(int type, String type_uuid,
			String student_uuids, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		Session s = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();

		// from StudentContactRealation where student_uuid in( select uuid from
		// Student where classuuid =?)
		String sql = "select DISTINCT parent_uuid from px_pxstudentcontactrealation  where student_uuid in ("+DBUtil.stringsToWhereInValue(student_uuids)+")";
		Query q = s.createSQLQuery(sql);
		List<String> parentuuidlist = q.list();
		pushMsgToParentByParentuuidList(type, type_uuid, parentuuidlist, msg);
	}

	/**
	 * 广播消息给指定家长的家长
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToParentByParentuuidList(int type, String type_uuid,
			List<String> parentuuidlist, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		Timestamp nowTime=TimeUtils.getCurrentTimestamp();
		
		for (String o : parentuuidlist) {
			PushMessage pushMessage = new PushMessage();
			// pushMessage.setGroup_uuid(group_uuid);
			pushMessage.setRevice_useruuid(o);
			pushMessage.setType(type);
			pushMessage.setRel_uuid(type_uuid);
			pushMessage.setTitle(title);
			pushMessage.setMessage(PxStringUtil.getSubString(msg,
					msg_max_length));
			pushMessage.setCreate_time(nowTime);
			pushMessage.setIsread(0);

			this.nSimpleHibernateDao.save(pushMessage);
		}

		this.logger.info("pushMsgToParentByClass count="
				+ parentuuidlist.size());
		this.pushMsgToAPPIservice.pushMsg_to_parent_app_byUserList(parentuuidlist, title, msg);
	}
	

	/**
	 *获取幼儿园关联所有家长
	 * 
	 * @param msg
	 * @return
	 */
	public List getAllKindergartenParentuuidByGroupuuid(
			String group_uuid) throws Exception {
		
		Session s = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();
		String sql = "select DISTINCT parent_uuid from px_studentcontactrealation  where groupuuid='"
				+ group_uuid + "'";
		
		return  this.nSimpleHibernateDao.createSqlQuery(sql).list();
	}
	/**
	 *获取教育机构关联所有家长
	 * 
	 * @param msg
	 * @return
	 */
	public List getAllPxSchoolParentuuidByGroupuuid(
			String group_uuid) throws Exception {
		
		Session s = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();
		String sql = "select DISTINCT parent_uuid from px_pxstudentcontactrealation  where groupuuid='"
				+ group_uuid + "'";
		
		return  this.nSimpleHibernateDao.createSqlQuery(sql).list();
	}
	/**
	 * 广播所有android消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToAll_to_parent(int type, String type_uuid,
			String group_uuid, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		Integer groupType=this.getGroupType(group_uuid);
		List list=null;
		if(SystemConstants.Group_type_2.equals(groupType)){
			list=this.getAllPxSchoolParentuuidByGroupuuid(group_uuid);
		}else{
			list=this.getAllKindergartenParentuuidByGroupuuid(group_uuid);
		}
		pushMsgToParentByParentuuidList(type, type_uuid, list, msg);
	}

	/**
	 * 广播所有android消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsg_to_teacher(int type, String type_uuid,
			String user_uuid, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		PushMessage pushMessage = new PushMessage();
		// pushMessage.setGroup_uuid(group_uuid);
		pushMessage.setRevice_useruuid(user_uuid);
		pushMessage.setType(type);
		pushMessage.setRel_uuid(type_uuid);
		pushMessage.setTitle(title);
		pushMessage.setMessage(PxStringUtil.getSubString(msg, msg_max_length));
		pushMessage.setCreate_time(TimeUtils.getCurrentTimestamp());
		pushMessage.setIsread(0);

		this.nSimpleHibernateDao.save(pushMessage);
		this.pushMsgToAPPIservice.pushMsg_to_teacher_app(user_uuid, title, msg);

	}

	/**
	 * 广播所有android消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsg_to_parent(int type, String type_uuid, String user_uuid,
			String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		PushMessage pushMessage = new PushMessage();
		// pushMessage.setGroup_uuid(group_uuid);
		pushMessage.setRevice_useruuid(user_uuid);
		pushMessage.setType(type);
		pushMessage.setRel_uuid(type_uuid);
		pushMessage.setTitle(title);
		pushMessage.setMessage(PxStringUtil.getSubString(msg, msg_max_length));
		pushMessage.setCreate_time(TimeUtils.getCurrentTimestamp());
		pushMessage.setIsread(0);

		this.nSimpleHibernateDao.save(pushMessage);
		this.pushMsgToAPPIservice.pushMsg_to_parent_app(user_uuid, title, msg);

	}


	/**
	 * 根据回复内容,推送给对应老师或家长
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsg_replay_to_classNews_to_teacherOrParent(
			String type_uuid, String msg) throws Exception {

		Session session = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();
		String sql = " SELECT t1.uuid,t1.create_useruuid,t1.usertype,t1.groupuuid";
		sql += " FROM px_classnews t1 ";
		sql += " where t1.uuid='" + type_uuid + "'  ";
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map> list = query.list();

		if (list.size() == 0)
			return;

		Map obj = list.get(0);
		if (obj.get("usertype") == null) {
			logger.error("px_classnews usertype is null,uuid" + type_uuid);
			return;
		}

		if ("1".equals((obj.get("usertype").toString()))) {
			// 家长,暂时不开发通知
			return;
		}

		boolean isSendPushMsg = true;

		PushMessage pushMessage = (PushMessage) this.nSimpleHibernateDao
				.getObjectByAttribute(PushMessage.class, "rel_uuid", type_uuid);
		if (pushMessage == null) {
			pushMessage = new PushMessage();
			pushMessage.setCount(1);
		} else {// 已经发过消息的情况下,只修改对应的内容.不新加数据.
			if (SystemConstants.Read_flag_0.equals(pushMessage.getIsread())) {// 未读情况下,推送消息不在发送.
				isSendPushMsg = false;
				if (pushMessage.getCount() == null) {// 计数.
					pushMessage.setCount(1);
				} else {
					pushMessage.setCount(pushMessage.getCount() + 1);
				}
			} else {//
				pushMessage.setCount(1);
			}
		}
		pushMessage.setGroup_uuid((String) obj.get("groupuuid"));
		pushMessage.setRevice_useruuid((String) obj.get("create_useruuid"));
		pushMessage.setType(SystemConstants.common_type_hudong);
		pushMessage.setRel_uuid(type_uuid);
		pushMessage.setTitle("互动(" + pushMessage.getCount() + ")");
		pushMessage.setMessage(PxStringUtil.getSubString(msg, 128));
		pushMessage.setCreate_time(TimeUtils.getCurrentTimestamp());
		pushMessage.setIsread(SystemConstants.Read_flag_0);

		this.nSimpleHibernateDao.save(pushMessage);
		if (isSendPushMsg) {// 不重复发送消息
			if ("1".equals((obj.get("usertype").toString()))) {
				// 家长
				this.pushMsgToAPPIservice.pushMsg_to_parent_app(pushMessage.getRevice_useruuid(),
						pushMessage.getTitle(), msg);
			} else {
				// 老师
				this.pushMsgToAPPIservice.pushMsg_to_teacher_app(pushMessage.getRevice_useruuid(),
						pushMessage.getTitle(), msg);
			}

		}
	}

}
