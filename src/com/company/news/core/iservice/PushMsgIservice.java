package com.company.news.core.iservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.company.news.ProjectProperties;
import com.company.news.SystemConstants;
import com.company.news.commons.util.IOSPushUtils;
import com.company.news.commons.util.PxStringUtil;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.PushMessage;
import com.company.news.rest.util.DBUtil;
import com.company.news.rest.util.TimeUtils;
import com.company.pushmsg.PushMsgAndoridInterface;
import com.company.pushmsg.PushMsgBaiduAndoridImpl;
import com.company.pushmsg.PushMsgIosInterface;
import com.company.pushmsg.PushMsgIosInterfaceImpl;
import com.company.pushmsg.PushMsgUmengAndoridImpl;
import com.company.pushmsg.PushMsgUmengIosInterfaceImpl;

/**
 * //1.保存及时消息. //2.推送及时消息.
 * 
 * @author liumingquan
 * 
 */
@Service
public class PushMsgIservice {

	public static final String andoridPushMsg_type = ProjectProperties
			.getProperty("andoridPushMsg_type", "umeng");

	private static PushMsgAndoridInterface pushMsgAndoridInterface;
	static {
		if (andoridPushMsg_type.equals("baidu"))
			pushMsgAndoridInterface = new PushMsgBaiduAndoridImpl();
		else {

			pushMsgAndoridInterface = new PushMsgUmengAndoridImpl();
		}
	}

	public static final String iosPushMsg_type = ProjectProperties.getProperty(
			"iosPushMsg_type", "umeng");

	private static PushMsgIosInterface pushMsgIosInterface;
	static {
		if (andoridPushMsg_type.equals("umeng"))
			pushMsgIosInterface = new PushMsgUmengIosInterfaceImpl();
		else {

			pushMsgIosInterface = new PushMsgIosInterfaceImpl();
		}
	}
	protected static Logger logger = LoggerFactory
			.getLogger(PushMsgIservice.class);
	@Autowired
	@Qualifier("NSimpleHibernateDao")
	protected NSimpleHibernateDao nSimpleHibernateDao;

	/**
	 * 广播所有android消息_给老师
	 * 
	 * @param msg
	 * @return
	 */
	private void androidPushMsgToSingleDevice_to_parentByChannelId(
			String title, String msg, String channelId) throws Exception {
		pushMsgAndoridInterface
				.androidPushMsgToSingleDevice_to_parentByChannelId(title, msg,
						channelId);
	}

	/**
	 * 广播所有android消息_给老师
	 * 
	 * @param msg
	 * @return
	 */
	private void androidPushMsgToSingleDevice_to_TeacherByChannelId(
			String title, String msg, String channelId) throws Exception {
		pushMsgAndoridInterface
				.androidPushMsgToSingleDevice_to_TeacherByChannelId(title, msg,
						channelId);
	}

	/**
	 * 获取设备id用于推送.根据组织uuid
	 * 
	 * @param device_type
	 * @param type
	 * @param group_uuid
	 * @return
	 */
	private List getChannelIdBy(String device_type, Integer type,
			String group_uuid) {
		String hql = "select distinct device_id from PushMsgDevice where status=0 and device_type='"
				+ device_type + "'";
		hql += " and type=" + type;
		hql += " and group_uuid=?";

		return this.nSimpleHibernateDao.getHibernateTemplate().find(hql,
				group_uuid);

	}

	/**
	 * 获取设备id用于推送.获取所有,不管状态
	 * 
	 * @param device_type
	 * @param type
	 * @param group_uuid
	 * @return
	 */
	private List getChannelIdByAll(String device_type, Integer type) {
		String hql = "select distinct device_id from PushMsgDevice where  device_type='"
				+ device_type + "'";
		hql += " and type=" + type;
		return this.nSimpleHibernateDao.getHibernateTemplate().find(hql);

	}

	/**
	 * 获取设备id用于推送.根据uuid
	 * 
	 * @param device_type
	 * @param type
	 * @param group_uuid
	 * @return
	 */
	private List getChannelIdByUser_uuid(String device_type, Integer type,
			String user_uuid) {
		String hql = "select distinct device_id from PushMsgDevice where status=0 and device_type='"
				+ device_type + "'";
		hql += " and type=" + type;
		hql += " and user_uuid=?";

		return this.nSimpleHibernateDao.getHibernateTemplate().find(hql,
				user_uuid);

	}

	/**
	 * 获取设备id用于推送.根据uuid
	 * 
	 * @param device_type
	 * @param type
	 * @param group_uuid
	 * @return
	 */
	private List getChannelIdByUserUuids(String device_type, Integer type,
			List user_uuids) {
		String hql = "select distinct device_id from PushMsgDevice where status=0 and device_type='"
				+ device_type + "'";
		hql += " and type=" + type;
		hql += " and user_uuid in("
				+ DBUtil.stringListToWhereInValue(user_uuids) + ")";

		return this.nSimpleHibernateDao.getHibernateTemplate().find(hql);

	}

	static int msg_max_length = 120;

	/**
	 * 广播所有android消息_给所有老师
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

		for (String o : list) {
			PushMessage pushMessage = new PushMessage();
			pushMessage.setGroup_uuid(group_uuid);
			pushMessage.setRevice_useruuid(o);
			pushMessage.setType(type);
			pushMessage.setRel_uuid(type_uuid);
			pushMessage.setTitle(title);

			pushMessage.setMessage(PxStringUtil.getSubString(msg,
					msg_max_length));
			pushMessage.setCreate_time(TimeUtils.getCurrentTimestamp());
			pushMessage.setIsread(0);

			this.nSimpleHibernateDao.save(pushMessage);
		}
		this.logger.info("pushMsgToAll_to_teacher count=" + list.size());
		this.androidPushMsgToAll_to_teacher_app(group_uuid, title, msg);
	}

	
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
	 * 广播所有消息班级关联的家长(教育机构)
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsgToParentByParentuuidList(int type, String type_uuid,
			List<String> parentuuidlist, String msg) throws Exception {
		String title = getPushMsgTitleByType(type);

		for (String o : parentuuidlist) {
			PushMessage pushMessage = new PushMessage();
			// pushMessage.setGroup_uuid(group_uuid);
			pushMessage.setRevice_useruuid(o);
			pushMessage.setType(type);
			pushMessage.setRel_uuid(type_uuid);
			pushMessage.setTitle(title);
			pushMessage.setMessage(PxStringUtil.getSubString(msg,
					msg_max_length));
			pushMessage.setCreate_time(TimeUtils.getCurrentTimestamp());
			pushMessage.setIsread(0);

			this.nSimpleHibernateDao.save(pushMessage);
		}

		this.logger.info("pushMsgToParentByClass count="
				+ parentuuidlist.size());
		this.pushMsg_to_parent_app_byUserList(parentuuidlist, title, msg);
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

		Session s = this.nSimpleHibernateDao.getHibernateTemplate()
				.getSessionFactory().openSession();
		String sql = "";
		Query q = s
				.createSQLQuery("select DISTINCT parent_uuid from px_studentcontactrealation  where groupuuid='"
						+ group_uuid + "'");
		List<String> parentuuidlist = q.list();
		pushMsgToParentByParentuuidList(type, type_uuid, parentuuidlist, msg);
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
		this.pushMsg_to_teacher_app(user_uuid, title, msg);

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
		this.pushMsg_to_parent_app(user_uuid, title, msg);

	}

	/**
	 * 广播所有android消息_给所有老师
	 * 
	 * @param msg
	 * @return
	 */
	private void pushMsg_to_teacher_app(final String useruuid,
			final String title, final String msg) throws Exception {

		// String apiKey = ProjectProperties.getProperty("baidu_apiKey_teacher",
		// "El4au0Glwr7Xt8sPgZFg2UP7");
		// String secretKey =
		// ProjectProperties.getProperty("baidu_secretKey_teacher",
		// "4rtqyA96S6GDNVcgB8D1Cqh0Wm4Vohq8");
		// this.androidPushMsgToAll(msg, apiKey, secretKey);
		final List<String> anroidlist = getChannelIdByUser_uuid(
				SystemConstants.PushMsgDevice_device_type_android,
				SystemConstants.PushMsgDevice_type_1, useruuid);
		// 1.发布
		final List<String> iosList = getChannelIdByUser_uuid(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_1, useruuid);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {
				try {
					for (String o : anroidlist) {
						that.androidPushMsgToSingleDevice_to_TeacherByChannelId(
								title, msg, o);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {

					that.iosPushMsgToSingleDevice_to_TeacherByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * 广播所有android消息_给所有老师
	 * 
	 * @param msg
	 * @return
	 */
	private void androidPushMsgToAll_to_teacher_app(final String group_uuid,
			final String title, final String msg) throws Exception {

		// String apiKey = ProjectProperties.getProperty("baidu_apiKey_teacher",
		// "El4au0Glwr7Xt8sPgZFg2UP7");
		// String secretKey =
		// ProjectProperties.getProperty("baidu_secretKey_teacher",
		// "4rtqyA96S6GDNVcgB8D1Cqh0Wm4Vohq8");
		// this.androidPushMsgToAll(msg, apiKey, secretKey);
		final List<String> anroidlist = getChannelIdBy(
				SystemConstants.PushMsgDevice_device_type_android,
				SystemConstants.PushMsgDevice_type_1, group_uuid);
		// 1.发布
		final List<String> iosList = getChannelIdBy(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_1, group_uuid);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {
				try {
					for (String o : anroidlist) {
						that.androidPushMsgToSingleDevice_to_TeacherByChannelId(
								title, msg, o);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {

					that.iosPushMsgToSingleDevice_to_TeacherByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * 广播android消息_给指定家长
	 * 
	 * @param msg
	 * @return
	 */
	private void pushMsg_to_parent_app_byUserList(
			final List<String> user_uuids, final String title, final String msg)
			throws Exception {

		// String apiKey = ProjectProperties.getProperty("baidu_apiKey_parent",
		// "p9DUFwCzoUaKenaB5ovHch0G");
		// String secretKey =
		// ProjectProperties.getProperty("baidu_secretKey_parent",
		// "GUHR0mniN15LvML8OWnm3GzMdXsVEGbD");
		// this.androidPushMsgToAll(msg, apiKey, secretKey);

		final List<String> anroidlist = this.getChannelIdByUserUuids(
				SystemConstants.PushMsgDevice_device_type_android,
				SystemConstants.PushMsgDevice_type_0, user_uuids);
		// 1.发布
		final List<String> iosList = getChannelIdByUserUuids(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_0, user_uuids);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {

				try {

					that.iosPushMsgToSingleDevice_to_parentByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					for (String o : anroidlist) {
						that.androidPushMsgToSingleDevice_to_parentByChannelId(
								title, msg, o);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	/**
	 * 广播所有android消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	private void pushMsg_to_parent_app_android(final String useruuid,
			final String title, final String msg) throws Exception {

		// String apiKey = ProjectProperties.getProperty("baidu_apiKey_parent",
		// "p9DUFwCzoUaKenaB5ovHch0G");
		// String secretKey =
		// ProjectProperties.getProperty("baidu_secretKey_parent",
		// "GUHR0mniN15LvML8OWnm3GzMdXsVEGbD");
		// this.androidPushMsgToAll(msg, apiKey, secretKey);

		final List<String> anroidlist = getChannelIdByUser_uuid(
				SystemConstants.PushMsgDevice_device_type_android,
				SystemConstants.PushMsgDevice_type_0, useruuid);
		// 1.发布
		final List<String> iosList = getChannelIdByUser_uuid(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_0, useruuid);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {
				try {
					for (String o : anroidlist) {
						that.androidPushMsgToSingleDevice_to_parentByChannelId(
								title, msg, o);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {

					that.iosPushMsgToSingleDevice_to_parentByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 广播所有android消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	private void pushMsg_to_parent_app(final String useruuid,
			final String title, final String msg) throws Exception {

		// String apiKey = ProjectProperties.getProperty("baidu_apiKey_parent",
		// "p9DUFwCzoUaKenaB5ovHch0G");
		// String secretKey =
		// ProjectProperties.getProperty("baidu_secretKey_parent",
		// "GUHR0mniN15LvML8OWnm3GzMdXsVEGbD");
		// this.androidPushMsgToAll(msg, apiKey, secretKey);

		final List<String> anroidlist = getChannelIdByUser_uuid(
				SystemConstants.PushMsgDevice_device_type_android,
				SystemConstants.PushMsgDevice_type_0, useruuid);
		// 1.发布
		final List<String> iosList = getChannelIdByUser_uuid(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_0, useruuid);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {
				try {
					for (String o : anroidlist) {
						that.androidPushMsgToSingleDevice_to_parentByChannelId(
								title, msg, o);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {

					that.iosPushMsgToSingleDevice_to_parentByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 广播所有android消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	private void pushMsgToAll_to_parent_app(final String group_uuid,
			final String title, final String msg) throws Exception {

		// String apiKey = ProjectProperties.getProperty("baidu_apiKey_parent",
		// "p9DUFwCzoUaKenaB5ovHch0G");
		// String secretKey =
		// ProjectProperties.getProperty("baidu_secretKey_parent",
		// "GUHR0mniN15LvML8OWnm3GzMdXsVEGbD");
		// this.androidPushMsgToAll(msg, apiKey, secretKey);

		final List<String> anroidlist = getChannelIdBy(
				SystemConstants.PushMsgDevice_device_type_android,
				SystemConstants.PushMsgDevice_type_0, group_uuid);
		// 1.发布
		final List<String> iosList = getChannelIdBy(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_0, group_uuid);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {
				try {
					for (String o : anroidlist) {
						that.androidPushMsgToSingleDevice_to_parentByChannelId(
								title, msg, o);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {

					that.iosPushMsgToSingleDevice_to_parentByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 广播消息给所有的ios客户端的家长
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsg_to_all_ios_parent_app(final String title,
			final String msg) throws Exception {
		// 1.发布
		final List<String> iosList = getChannelIdByAll(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_0);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {
				try {

					that.iosPushMsgToSingleDevice_to_parentByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 广播消息给所有的ios客户端的老师
	 * 
	 * @param msg
	 * @return
	 */
	public void pushMsg_to_all_ios_teacher_app(final String title,
			final String msg) throws Exception {
		// 1.发布
		final List<String> iosList = getChannelIdByAll(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_1);
		final PushMsgIservice that = this;
		new Thread(new Runnable() {
			public void run() {
				try {
					that.iosPushMsgToSingleDevice_to_TeacherByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 广播所有ios消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	private void iosPushMsgToSingleDevice_to_parentByChannelId(String title,
			String msg, List deviceTokenList) throws Exception {
		pushMsgIosInterface.iosPushMsgToSingleDevice_to_parentByChannelId(
				title, msg, deviceTokenList);

	}

	/**
	 * 广播所有ios消息_给所有家长
	 * 
	 * @param msg
	 * @return
	 */
	private void iosPushMsgToSingleDevice_to_TeacherByChannelId(String title,
			String msg, List deviceTokenList) throws Exception {
		pushMsgIosInterface.iosPushMsgToSingleDevice_to_TeacherByChannelId(
				title, msg, deviceTokenList);
		if (true)
			return;
		String p12FileName = ProjectProperties.getProperty("iosCert_teacher",
				"wenjie_jiazhangtong_push_aps_dev.p12");
		String p12Pass = ProjectProperties.getProperty("iosCert_pwd_teacher",
				"wenjie_123456");
		IOSPushUtils.pushIosMsgByToken(p12FileName, p12Pass, msg,
				deviceTokenList);
	}

	/**
	 * 广播所有android消息
	 * 
	 * @param msg
	 * @return
	 */
	private void androidPushMsgToAll_app(String msg, String apiKey,
			String secretKey) throws Exception {
		// 1. get apiKey and secretKey from developer console
		// String apiKey = "xxxxxxxxxxxxxxxxxxxx";
		// String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments

			JSONObject notification = new JSONObject();
			notification.put("title", "问界互动家园");
			notification.put("description", msg);
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)// 1：通知,0:透传消息.
																		// 默认为0
																		// 注：IOS只有通知.
					.addMessage(notification.toString()) // 添加透传消息
					.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			this.logger.info("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}

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
				this.pushMsg_to_parent_app(pushMessage.getRevice_useruuid(),
						pushMessage.getTitle(), msg);
			} else {
				// 老师
				this.pushMsg_to_teacher_app(pushMessage.getRevice_useruuid(),
						pushMessage.getTitle(), msg);
			}

		}
	}

}
