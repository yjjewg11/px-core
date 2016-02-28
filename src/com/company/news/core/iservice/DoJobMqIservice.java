package com.company.news.core.iservice;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	
}
