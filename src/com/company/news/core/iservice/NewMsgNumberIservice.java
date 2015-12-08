package com.company.news.core.iservice;

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
import com.company.news.cache.PxRedisCache;
import com.company.news.commons.util.IOSPushUtils;
import com.company.news.commons.util.PxStringUtil;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.PushMessage;
import com.company.news.interfaces.SessionUserInfoInterface;
import com.company.news.rest.util.DBUtil;
import com.company.news.rest.util.TimeUtils;
import com.company.news.service.ClassNewsService;
import com.company.pushmsg.PushMsgAndoridInterface;
import com.company.pushmsg.PushMsgBaiduAndoridImpl;
import com.company.pushmsg.PushMsgIosInterface;
import com.company.pushmsg.PushMsgIosInterfaceImpl;
import com.company.pushmsg.PushMsgUmengAndoridImpl;
import com.company.pushmsg.PushMsgUmengIosInterfaceImpl;

/**
 *   获取各种消息总数
 * @author liumingquan
 *
 */
@Service
public class NewMsgNumberIservice {
	//缺陷100年后,溢出
    public static final String Date_YYYYMMDD = "yyMMdd"    		;
	  protected static Logger logger = LoggerFactory.getLogger(NewMsgNumberIservice.class);
	  @Autowired
	  @Qualifier("NSimpleHibernateDao")
	  protected NSimpleHibernateDao nSimpleHibernateDao;
	  
	  /**
	   * 今日话题总数
	   * @return
	   */
	  public Object today_snsTopic(){
		  String key="snsTopic_"+TimeUtils.getCurrentTime(Date_YYYYMMDD);
		  Object count=PxRedisCache.getCountOfNewMsgNumber(key);
		  if(count!=null)return count;
		  String sql = "select count(1) from sns_topic t1 where t1.status=0 and t1.create_time>="+DBUtil.stringToDateYMDByDBType(TimeUtils.getCurrentTime(TimeUtils.YYYY_MM_DD_FORMAT));
		  Session s = this.nSimpleHibernateDao.getHibernateTemplate()
					.getSessionFactory().openSession();
			 count= s.createSQLQuery(sql).uniqueResult();
			 PxRedisCache.setCountOfNewMsgNumber(key, count+"");
		return count;
	  }
	  
	  
	  /**
	   * 缓存,今日话题总数+1.
	   * @return
	   */
	  public void today_snsTopic_incrCountOfNewMsgNumber(){
		  String key="snsTopic_"+TimeUtils.getCurrentTime(Date_YYYYMMDD);
		  PxRedisCache.incrCountOfNewMsgNumber(key);
	  }
	  
	  /**
	   * 今日精品文章总数
	   * @return
	   */
	  public Object today_goodArticle(){
		  String key="goodArticle_"+TimeUtils.getCurrentTime(Date_YYYYMMDD);
		  Object count=PxRedisCache.getCountOfNewMsgNumber(key);
		  if(count!=null)return count;
		  String sql = "  select count(1) from px_announcements t1 where t1.status=0  and t1.type=3 and t1.create_time>="+DBUtil.stringToDateYMDByDBType(TimeUtils.getCurrentTime(TimeUtils.YYYY_MM_DD_FORMAT));
		  Session s = this.nSimpleHibernateDao.getHibernateTemplate()
					.getSessionFactory().openSession();
			 count= s.createSQLQuery(sql).uniqueResult();
			 PxRedisCache.setCountOfNewMsgNumber(key, count+"");
		return count;
	  }
	  

	  /**
	   * 缓存,今日精品文章+1.
	   * @return
	   */
	  public void today_goodArticle_incrCountOfNewMsgNumber(){
		  String key="goodArticle_"+TimeUtils.getCurrentTime(Date_YYYYMMDD);
		  PxRedisCache.incrCountOfNewMsgNumber(key);
	  }
	  
	  
	  
	  /**
	   * 今日精品文章总数
	   * @return
	   */
	  public Object today_pxbenefit(){
		  String key="pxbenefit"+TimeUtils.getCurrentTime(Date_YYYYMMDD);
		  Object count=PxRedisCache.getCountOfNewMsgNumber(key);
		  if(count!=null)return count;
			String crrentTime=TimeUtils.getCurrentTime(TimeUtils.YYYY_MM_DD_FORMAT);
		  String sql = "  select count(1) from px_announcements t1 where t1.status=0   and t1.type=85 ";
			sql+=" and start_time<="+DBUtil.stringToDateByDBType(crrentTime);
			sql+=" and end_time>="+DBUtil.stringToDateByDBType(crrentTime);
			
		  Session s = this.nSimpleHibernateDao.getHibernateTemplate()
					.getSessionFactory().openSession();
			 count= s.createSQLQuery(sql).uniqueResult();
			 PxRedisCache.setCountOfNewMsgNumber(key, count+"");
		return count;
	  }
	  

	  /**
	   * 缓存,今日精品文章+1.
	   * @return
	   */
	  public void today_pxbenefit_incrCountOfNewMsgNumber(){
		  String key="pxbenefit_"+TimeUtils.getCurrentTime(Date_YYYYMMDD);
		  PxRedisCache.incrCountOfNewMsgNumber(key);
	  }
	  
	  /**
	   * 今日精品文章总数
	   * @return
	   */
	  public Object today_unreadPushMessage(SessionUserInfoInterface parent){
		  if(parent==null)return 0;
		  String sql = "select count(1) from px_pushmessage t1 where t1.isread=0  and t1.create_time>="+DBUtil.stringToDateYMDByDBType(TimeUtils.getCurrentTime(TimeUtils.YYYY_MM_DD_FORMAT));
			sql+=" 	and t1.revice_useruuid='"+parent.getUuid()+"' ";
			
		  Session s = this.nSimpleHibernateDao.getHibernateTemplate()
					.getSessionFactory().openSession();
		  Object count= s.createSQLQuery(sql).uniqueResult();
			 
		return count;
	  }
	
}
