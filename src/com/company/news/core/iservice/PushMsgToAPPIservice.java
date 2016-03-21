package com.company.news.core.iservice;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

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
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.rest.util.DBUtil;
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
public class PushMsgToAPPIservice {

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
			.getLogger(PushMsgToAPPIservice.class);
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
	private List getTeacherChannelIdsByGroup(String device_type,String group_uuid) {
		// t1.status=0 and
		String sql="select distinct t1.device_id from px_pushmsgdevice t1 LEFT JOIN px_usergrouprelation t2 on t1.user_uuid=t2.useruuid ";
		sql+=" where t1.device_type='"+device_type+"'";
		sql+=" and t2.groupuuid='"+group_uuid+"'";
//		String hql = "select distinct device_id from px_pushmsgdevice where status=0 and device_type='"
//				+ device_type + "'";
//		hql += " and type=" + type;
//		hql += " and group_uuid=?";

//		 this.nSimpleHibernateDao.getHibernateTemplate().find(hql,
//				group_uuid);
		 
		 return this.nSimpleHibernateDao.createSqlQuery(sql).list();

	}
	
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
	/**
	 * 获取设备id用于推送.根据幼儿园组织uuid
	 * 
	 * @param device_type
	 * @param type
	 * @param group_uuid
	 * @return
	 */
	private List getParentChannelIdsByKindergarten(String device_type,String group_uuid) {
		// t1.status=0 and
		String sql="select distinct t1.device_id from px_pushmsgdevice t1 LEFT JOIN px_studentcontactrealation t2 on t1.user_uuid=t2.parent_uuid ";
		sql+=" where t1.device_type='"+device_type+"'";
		sql+=" and t2.groupuuid='"+group_uuid+"'";
//		String hql = "select distinct device_id from px_pushmsgdevice where status=0 and device_type='"
//				+ device_type + "'";
//		hql += " and type=" + type;
//		hql += " and group_uuid=?";

//		 this.nSimpleHibernateDao.getHibernateTemplate().find(hql,
//				group_uuid);
		 
		 return this.nSimpleHibernateDao.createSqlQuery(sql).list();

	}
	/**
	 * 获取设备id用于推送.根据教育机构组织uuid
	 * 
	 * @param device_type
	 * @param type
	 * @param group_uuid
	 * @return
	 */
	private List getParentChannelIdsByPxSchool(String device_type,String group_uuid) {
		// t1.status=0 and
		String sql="select distinct t1.device_id from px_pushmsgdevice t1 LEFT JOIN px_pxstudentcontactrealation t2 on t1.user_uuid=t2.parent_uuid ";
		sql+=" where t1.device_type='"+device_type+"'";
		sql+=" and t2.groupuuid='"+group_uuid+"'";
//		String hql = "select distinct device_id from px_pushmsgdevice where status=0 and device_type='"
//				+ device_type + "'";
//		hql += " and type=" + type;
//		hql += " and group_uuid=?";

//		 this.nSimpleHibernateDao.getHibernateTemplate().find(hql,
//				group_uuid);
		 
		 return this.nSimpleHibernateDao.createSqlQuery(sql).list();

	}
//	/**
//	 * 获取设备id用于推送.根据组织uuid
//	 * 
//	 * @param device_type
//	 * @param type
//	 * @param group_uuid
//	 * @return
//	 */
//	private List getChannelIdBy(String device_type, Integer type,
//			String group_uuid) {
//		String hql = "select distinct device_id from PushMsgDevice where status=0 and device_type='"
//				+ device_type + "'";
//		hql += " and type=" + type;
//		hql += " and group_uuid=?";
//
//		return this.nSimpleHibernateDao.getHibernateTemplate().find(hql,
//				group_uuid);
//
//	}

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
		if(user_uuids.isEmpty())return new ArrayList();
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
	public void pushMsg_to_teacher_app(final String useruuid,
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
		final PushMsgToAPPIservice that = this;
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
	public void pushMsgToAll_to_teacher_app(final String group_uuid,
			final String title, final String msg) throws Exception {

		// String apiKey = ProjectProperties.getProperty("baidu_apiKey_teacher",
		// "El4au0Glwr7Xt8sPgZFg2UP7");
		// String secretKey =
		// ProjectProperties.getProperty("baidu_secretKey_teacher",
		// "4rtqyA96S6GDNVcgB8D1Cqh0Wm4Vohq8");
		// this.androidPushMsgToAll(msg, apiKey, secretKey);
		final List<String> anroidlist = this.getTeacherChannelIdsByGroup(
				SystemConstants.PushMsgDevice_device_type_android, group_uuid);
		// 1.发布
		final List<String> iosList = getTeacherChannelIdsByGroup(
				SystemConstants.PushMsgDevice_device_type_ios, group_uuid);
		final PushMsgToAPPIservice that = this;
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
	public void pushMsg_to_teacher_app_byUserList(
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
				SystemConstants.PushMsgDevice_type_1, user_uuids);
		// 1.发布
		final List<String> iosList = getChannelIdByUserUuids(
				SystemConstants.PushMsgDevice_device_type_ios,
				SystemConstants.PushMsgDevice_type_1, user_uuids);
		
		final PushMsgToAPPIservice that = this;
		new Thread(new Runnable() {
			public void run() {

				try {

					that.iosPushMsgToSingleDevice_to_TeacherByChannelId(title,
							msg, iosList);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					for (String o : anroidlist) {
						that.androidPushMsgToSingleDevice_to_TeacherByChannelId(
								title, msg, o);
					}
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
	public void pushMsg_to_parent_app_byUserList(
			final List<String> user_uuids, final String title, final String msg)
			throws Exception {
		if(user_uuids==null||user_uuids.isEmpty())return;
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
		final PushMsgToAPPIservice that = this;
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
	public void pushMsg_to_parent_app_android(final String useruuid,
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
		final PushMsgToAPPIservice that = this;
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
	public void pushMsg_to_parent_app(final String useruuid,
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
		final PushMsgToAPPIservice that = this;
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
		final PushMsgToAPPIservice that = this;
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
		final PushMsgToAPPIservice that = this;
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


}
