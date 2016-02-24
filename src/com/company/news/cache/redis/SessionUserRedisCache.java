package com.company.news.cache.redis;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.company.news.cache.CacheConstants;
import com.company.news.cache.SessionUserCache;
import com.company.news.cache.UserCache;
import com.company.news.json.JSONUtils;
import com.company.news.session.UserOfSession;


/**
基于redis 缓存sessionid关联用户uuid
 */
public  class SessionUserRedisCache  {
	private  String key=null;
	
	//用户信息,全部存到redies中
	private static String _hashKeyName=CacheConstants.Hash_Session_SessionId_User;
	private static String _useruuid_hashKeyName=CacheConstants.Hash_Session_Useruuid_SessionId;
	
	private static Logger logger = Logger.getLogger("SessionUserRedisCache");
	

	private static  Jedis  getJedis() {
	 return PxRedisCacheImpl.getJedis();
    }
	
	/**
	 * 获取UserOfSession
	 * @param uuid
	 * @param path
	 */
		public static  UserOfSession getUserOfSessionBySessionid(String key){
			if(StringUtils.isBlank(key))return null;
		Jedis jedis=getJedis();
		if(jedis==null)return null;
		try {
			
			 String value = jedis.hget(_hashKeyName, key);
		      if(StringUtils.isBlank(value)){
		    	  logger.error("Sessionid is not exist,Sessionid="+key);
		    	  return null;
		      }
		      
		      SessionUserCache  sessionUserCache= (SessionUserCache) JSONUtils.jsonToObject(value, SessionUserCache.class);
		      UserCache  user= UserRedisCache.getUserCache(sessionUserCache.getU());
		      
		      //封装为session 的用户
		  	UserOfSession userOfSession = new UserOfSession();
			userOfSession.setImg(user.getI());
			userOfSession.setLoginname(user.getL());
			userOfSession.setName(user.getN());
			userOfSession.setF(user.getF());
			
			userOfSession.setUuid(sessionUserCache.getU());
			userOfSession.setLoginType(sessionUserCache.getS());
			
		      return userOfSession;
		      
		}catch (Exception e) {
			e.printStackTrace();
//			throw e;
			return null;
		} finally{
			if(jedis!=null)jedis.close();
		}
	}
		
		/**
		 * 更新一个用户数据
		 * @param uuid
		 * @param path
		 */
		static public  void set(String key,	UserOfSession userOfSession ){
			Jedis jedis=getJedis();
			if(jedis==null)return;
			try {
				
				SessionUserCache user=new SessionUserCache();
				user.setU(userOfSession.getUuid());
				user.setS(userOfSession.getLoginType());
				user.setT( System.currentTimeMillis());
				
				 String objectJson = JSONUtils.getJsonString(user);
				 
				 
				 //清除旧的sessionid
				 String oldsessinid = jedis.hget(_useruuid_hashKeyName, user.getU());
				 if(oldsessinid!=null){
					   jedis.hdel(_hashKeyName, oldsessinid);
				 }
			      
			      
			      Pipeline p = jedis.pipelined();
			      //缓存键值关系   sessionidid->user
					 p.hset(_hashKeyName,key, objectJson);
					 //用户uuid->sessionidid
					 p.hset(_useruuid_hashKeyName,user.getU(), key);
					 
//				logger.info("jedis.set("+key+", "+objectJson+")");
			}catch (Exception e) {
				//e.printStackTrace();
				throw e;
			} finally{
				if(jedis!=null)jedis.close();
			}
		}

		/**
		 * 更新一个用户数据
		 * @param uuid
		 * @param path
		 */
		static public  void remove(String key){
			Jedis jedis=getJedis();
			if(jedis==null)return ;
			try {
				
				 String value = jedis.hget(_hashKeyName, key);
			      if(StringUtils.isBlank(value)){
			    	  return ;
			      }
			      SessionUserCache  sessionUserCache= (SessionUserCache) JSONUtils.jsonToObject(value, SessionUserCache.class);
				  jedis.hdel(_useruuid_hashKeyName, sessionUserCache.getU());
			      jedis.hdel(_hashKeyName, key);
			      
				logger.info("jedis.hdel("+_hashKeyName+", "+key+")");
			}catch (Exception e) {
				//e.printStackTrace();
				throw e;
			} finally{
				if(jedis!=null)jedis.close();
			}
		}
	public static void main(String[] s){
		UserCache d=new UserCache();
		 
		System.out.print(JSONUtils.getJsonString(d));
	}

}
