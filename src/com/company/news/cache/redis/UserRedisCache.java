package com.company.news.cache.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.company.news.SystemConstants;
import com.company.news.cache.CacheConstants;
import com.company.news.cache.PxRedisCache;
import com.company.news.cache.UserCache;
import com.company.news.commons.util.PxStringUtil;
import com.company.news.interfaces.SessionUserInfoInterface;
import com.company.news.json.JSONUtils;


/**
基于redis 的计数器.抽象类
 */
public  class UserRedisCache  {
	private  String key=null;
	
	//用户信息,全部存到redies中
	private static String _hashKeyName=CacheConstants.Hash_User_BaseInfo;
	
	private static Logger logger = Logger.getLogger("UserRedisCache");
	
	private static PxRedisCacheImpl pxRedisCacheImpl=PxRedisCache.getPxRedisCache();

	private static  Jedis  getJedis() {
	 return pxRedisCacheImpl.getJedis();
    }
	
	/**
	 * 设置对象.转换成String
	 * @param uuid
	 * @param path
	 */
		public static  UserCache getUserCache(String key){
			if(StringUtils.isBlank(key))return null;
		Jedis jedis=getJedis();
		try {
			
			 String value = jedis.hget(_hashKeyName, key);
		      if(StringUtils.isBlank(value)){
		    	  logger.error("redis userCahce is null,uuid="+value);
		    	  return null;
		      }
		      return (UserCache) JSONUtils.jsonToObject(value, UserCache.class);
		}catch (Exception e) {
			e.printStackTrace();
//			throw e;
		} finally{
			if(jedis!=null)jedis.close();
		}
		return null;
	}
		
		/**
		 * 更新一个用户数据
		 * @param uuid
		 * @param path
		 */
		static public  void setUserCache(String key,UserCache obj){
			Jedis jedis=getJedis();
			try {
				 String objectJson = JSONUtils.getJsonString(obj);
				 
			      jedis.hset(_hashKeyName,key, objectJson);
				logger.info("jedis.set("+key+", "+objectJson+")");
			}catch (Exception e) {
				//e.printStackTrace();
				throw e;
			} finally{
				if(jedis!=null)jedis.close();
			}
		}

		
		/**
		 * 设置老师
		 * @param uuid
		 * @param path
		 */
		static public  void setUserCacheByTeacher(SessionUserInfoInterface user){
			setUserCacheBySessionUserInfoInterface( user, SystemConstants.PushMsgDevice_type_1);
		}
		/**
		 * 设置家长
		 * @param uuid
		 * @param path
		 */
		static public  void setUserCacheByParent(SessionUserInfoInterface user){
			setUserCacheBySessionUserInfoInterface( user, SystemConstants.PushMsgDevice_type_0);
		}
		/**
		 * 更新一个用户数据
		 * @param uuid
		 * @param path
		 */
		static private  void setUserCacheBySessionUserInfoInterface(SessionUserInfoInterface user,Integer f){
			UserCache obj=new UserCache();
			obj.setI(user.getImg());
			obj.setL(user.getLoginname());
			obj.setN(user.getName());
			obj.setF(f);
			String key=user.getUuid();
			
			
			Jedis jedis=getJedis();
			try {
				 String objectJson = JSONUtils.getJsonString(obj);
				 
			      jedis.hset(_hashKeyName,key, objectJson);
				logger.info("jedis.set("+key+", "+objectJson+")");
			}catch (Exception e) {
				//e.printStackTrace();
				throw e;
			} finally{
				if(jedis!=null)jedis.close();
			}
		}

		
		/**
		 * 批量设置
		 * @param uuid
		 * @param path
		 */
		static public  void setUserCache(Map<String,String> map){
			Jedis jedis=getJedis();
			try {
				 
			      jedis.hmset(_hashKeyName, map);
				logger.info("jedis.hmset("+_hashKeyName+", [map.size="+map.size()+"])");
			}catch (Exception e) {
				//e.printStackTrace();
				throw e;
			} finally{
				if(jedis!=null)jedis.close();
			}
		}
		/**
		 * 设置对象.Map
		 * @param uuids
		 */
		static public  Map<String,UserCache> getUserCache(String[] uuids){
			Jedis jedis=getJedis();
			Map<String,UserCache> map=new HashMap();
			try {
				List<String> list= jedis.hmget(_hashKeyName, uuids);
				for(int i=0;i<uuids.length;i++){
					String value= list.get(i);
					UserCache userCache=null;
					  if(StringUtils.isBlank(value))return null;
					  userCache= (UserCache) JSONUtils.jsonToObject(value, UserCache.class);
					 map.put(uuids[i],userCache);
				}
			    
			}catch (Exception e) {
				e.printStackTrace();
				//throw e;
			} finally{
				if(jedis!=null)jedis.close();
			}
			return map;
		}
		
		/**
		 * //从缓存中获取用户资料,包装用户名和头像.
		 * @param list
		 * @param useruuid_key
		 * @param username_key
		 * @param userimg_key
		 * @return
		 */
		static public  List<Map> warpListMapByUserCache(List<Map> list,String useruuid_key,String username_key,String userimg_key){
			if(list.size()==0)return list;
			if(StringUtils.isBlank(useruuid_key))return list;
			
			//批量获取redis中用户
			String[] userUuids=new String[list.size()];
			for(int i=0,len=list.size();i<len;i++){
				Map o=list.get(i);
				String useruuid=(String)o.get(useruuid_key);
				if(StringUtils.isBlank(useruuid)){
					useruuid="-1";
				}
				userUuids[i]=useruuid;
			}
			
			Map<String,UserCache> userMap=getUserCache(userUuids);
			
			for(Map o:list){
				String useruuid=(String)o.get(useruuid_key);
				if(StringUtils.isBlank(useruuid)){
					continue;
				}
				
				UserCache userCahce=userMap.get(useruuid);
				if(userCahce==null){
					logger.error("redis userCahce is null,uuid="+useruuid);
					continue;
				}
				//包装姓名
				if(username_key!=null){
					o.put(username_key,userCahce.getN());
				}
				//包装头像
				if(userimg_key!=null){
					o.put(userimg_key,PxStringUtil.imgSmallUrlByUuid(userCahce.getI()));
				}
			}
			
			return list;
		}
	public static void main(String[] s){
		UserCache d=new UserCache();
		 
		System.out.print(JSONUtils.getJsonString(d));
	}

}
