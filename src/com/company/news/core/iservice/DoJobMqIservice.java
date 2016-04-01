package com.company.news.core.iservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.company.mq.JobDetails;
import com.company.mq.MQUtils;
import com.company.news.SystemConstants;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.right.RightConstants;

/**
 * 
 * 异步执行,MQ 消息
 * //1.保存及时消息. //2.推送及时消息.
 * 
 * @author liumingquan
 * 
Map map=new HashMap();
    	map.put("uuid", baseReplyJsonform.getRel_uuid());
    	map.put("type", baseReplyJsonform.getType());
    	map.put("title",user.getName()+"给你点赞");
		
    	JobDetails job=new JobDetails("doJobMqIservice","sendBaseDianzan",map);
		MQUtils.publish(job);
 * 
 */
@Service
public class DoJobMqIservice {

	protected static Logger logger = LoggerFactory
			.getLogger(DoJobMqIservice.class);
	@Autowired
	@Qualifier("NSimpleHibernateDao")
	protected NSimpleHibernateDao nSimpleHibernateDao;


	@Autowired
	public PushMsgIservice pushMsgIservice;

	/**
	 * 发送消息给园长信息
	 * @param map
	 * @throws Exception
	 */
	public void sendPushMessageKD(Map<String,String> map) throws Exception{
		String uuid=map.get("uuid");
		String groupuuid=map.get("groupuuid");
		String title=map.get("title");
		
		List userlist=nSimpleHibernateDao.getHasRightUserList(groupuuid, RightConstants.KD_Leader_Msg_m);
		pushMsgIservice.pushMsgToTeacherByuseruuidList(SystemConstants.common_type_messageKD, uuid,groupuuid, userlist, title);
	}
	

	/**
	 * 发送照片更新信息到家长
	 * @param map
	 * @throws Exception
	 */
	public void sendFPFamilyPhotoCollection(Map<String,String> map) throws Exception{
		String uuid=map.get("uuid");
		String create_useruuid=map.get("create_useruuid");
		String title=map.get("title");
		String sql="select DISTINCT uuid from px_parent where uuid in(select  user_uuid from fp_family_members where family_uuid='"+uuid+"' ) ";
		if(StringUtils.isNotBlank(create_useruuid)){
			sql+="and uuid!='"+create_useruuid+"'";
		}
		List parentuuidlist=this.nSimpleHibernateDao.createSQLQuery(sql).list();
		
		pushMsgIservice.pushMsgToParentByParentuuidListFilterNoreadCount(SystemConstants.common_type_FPFamilyPhotoCollection, uuid, parentuuidlist, title);
	}
	
	/**
	 * 发送照片更新信息到家长
	 * @param map
	 * @throws Exception
	 */
	public void sendFPPhotoItem(Map<String,String> map) throws Exception{
		String uuid=map.get("uuid");
		String create_useruuid=map.get("create_useruuid");
		String title=map.get("title");
		String sql="select DISTINCT uuid from px_parent where uuid in(select  user_uuid from fp_family_members where family_uuid='"+uuid+"' ) ";
		
		if(StringUtils.isNotBlank(create_useruuid)){
			sql+="and uuid!='"+create_useruuid+"'";
		}
		List parentuuidlist=this.nSimpleHibernateDao.createSQLQuery(sql).list();
		
		pushMsgIservice.pushMsgToParentByParentuuidListFilterNoreadCount(SystemConstants.common_type_FPPhotoItem, uuid, parentuuidlist, title);
	}
	
	/**
	 * 发送照片更新信息到家长(给你点赞
	 * @param map
	 * @throws Exception
	 */
	public void sendBaseDianzan(Map<String,String> map) throws Exception{
		String uuid=map.get("uuid");
		Integer type=Integer.valueOf(map.get("type"));
		String title=map.get("title");
		List parentuuidlist=null;
		if(SystemConstants.common_type_FPPhotoItem.equals(type)){
			String sql="select create_useruuid from fp_photo_item where uuid ='"+uuid+"'";
			 parentuuidlist=this.nSimpleHibernateDao.createSQLQuery(sql).list();
			
		}else if(SystemConstants.common_type_FPMovie.equals(type)){
			String sql="select create_useruuid from fp_movie where uuid ='"+uuid+"'";
			 parentuuidlist=this.nSimpleHibernateDao.createSQLQuery(sql).list();
			
		}
		if(parentuuidlist==null||parentuuidlist.isEmpty()){
			return;
		}
		
		pushMsgIservice.pushMsgToParentByParentuuidListFilterNoreadCount(type, uuid, parentuuidlist, title);
	}
	

	/**
	 * 发送照片更新信息到家长(给你点赞
	 * @param map
	 * @throws Exception
	 */
	public void sendBaseReply(Map<String,String> map) throws Exception{
		String uuid=map.get("uuid");
		Integer type=Integer.valueOf(map.get("type"));
		String title=map.get("title");
		List parentuuidlist=null;
		if(SystemConstants.common_type_FPPhotoItem.equals(type)){
			String sql="select create_useruuid from fp_photo_item where uuid ='"+uuid+"'";
			 parentuuidlist=this.nSimpleHibernateDao.createSQLQuery(sql).list();
			
		}else if(SystemConstants.common_type_FPMovie.equals(type)){
			String sql="select create_useruuid from fp_movie where uuid ='"+uuid+"'";
			 parentuuidlist=this.nSimpleHibernateDao.createSQLQuery(sql).list();
			
		}
		if(parentuuidlist==null||parentuuidlist.isEmpty()){
			return;
		}
		
		pushMsgIservice.pushMsgToParentByParentuuidListFilterNoreadCount(type, uuid, parentuuidlist, title);
	}
	/**
	 * 发送精品相册更新信息到家长
	 * @param map
	 * @throws Exception
	 */
	public void sendFPMovie(Map<String,String> map) throws Exception{
		String uuid=map.get("uuid");
		String create_useruuid=map.get("create_useruuid");
		String title=map.get("title");
		String sql="select  DISTINCT uuid from px_parent where uuid in (";
		sql+="select user_uuid from fp_family_members where  family_uuid in (select family_uuid from fp_family_members where user_uuid='"+create_useruuid+"'  )";
		sql+=")";
//		if(StringUtils.isNotBlank(create_useruuid)){
//			sql+="and uuid!='"+create_useruuid+"'";
//		}
		List parentuuidlist=this.nSimpleHibernateDao.createSQLQuery(sql).list();
		
		pushMsgIservice.pushMsgToParentByParentuuidListFilterNoreadCount(SystemConstants.common_type_FPMovie, uuid, parentuuidlist, title);
	}
	
}
