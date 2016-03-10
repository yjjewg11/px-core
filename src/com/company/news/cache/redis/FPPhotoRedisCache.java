 package com.company.news.cache.redis;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.company.common.SpringContextHolder;
import com.company.news.cache.CacheConstants;
import com.company.news.cache.PxRedisCache;
import com.company.news.dao.NSimpleHibernateDao;


/**
基于redis 缓存家庭照片图片uuid
 */
public  class FPPhotoRedisCache  {
	//用户信息,全部存到redies中
	
	private static Logger logger = Logger.getLogger("FPPhotoRedisCache");
	

	private static  Jedis  getJedis() {
	 return PxRedisCacheImpl.getJedis();
    }
	
	private static  NSimpleHibernateDao nSimpleHibernateDao=SpringContextHolder.getBean("NSimpleHibernateDao");


	/**
	 * 获取上传地址,每次调用缓存延长24小时
	 * @param uuid
	 * @return
	 * @throws IOException 
	 */
	public static String getUploadFilePath(String uuid){
		Jedis jedis=getJedis();
		Response<String> pathResponse;
		try {
			String key=CacheConstants.Redis_String_PhotoPath+uuid;
			
			//if(true)return jedis.get(key);
			 Pipeline p = jedis.pipelined();
			 pathResponse = p.get(key);
			 p.expire(key,  CacheConstants.Redis_String_PhotoPath_Expire);
			    p.sync();
			    p.close();
//		    List<Object> results = p.syncAndReturnAll();
				String path= pathResponse.get();
				if(path!=null)return path;
				List list =nSimpleHibernateDao.createSqlQuery("select path from fp_photo_item where uuid='"+uuid+"'").list();
				
				if (list != null&&list.size()>0){
					path=(String) list.get(0);
					setUploadFilePath(uuid, path);
				}else{//防止暴力查数据库
					setUploadFilePath(uuid, "");
				}
				return  path;
		}catch (Exception e) {
			////e.printStackTrace();
			throw e;
		}finally{
			if(jedis!=null)jedis.close();
		}
	
	}
	/**
	 * 设置上传地址到缓存中.缓存延长24小时
	 * @param uuid
	 * @param path
	 */
	public static void setUploadFilePath(String uuid,String path){
		Jedis jedis=getJedis();
		try {
			String key=CacheConstants.Redis_String_PhotoPath+uuid;
			jedis.setex(key, CacheConstants.Redis_String_PhotoPath_Expire, path);
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			if(jedis!=null)jedis.close();
		}
	}
	
}
