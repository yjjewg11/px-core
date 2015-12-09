package com.company.news.cache;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.company.news.ProjectProperties;
import com.company.news.cache.redis.PxRedisCacheImpl;

/**
 * redis缓存
 * px_count 计数缓存.
 * 上传地址缓存.
 * @author liumingquan
 *
 */
public class PxRedisCache{
	
	private static Logger logger = Logger.getLogger("PxRedisCache");
//	private final static String Redis_type=ProjectProperties.getProperty("Redis_type", "redis");

	private static PxRedisCacheImpl pxRedisCache=null;
	static {
//		if("redis".equals(Redis_type)){
			pxRedisCache=new PxRedisCacheImpl();
//		}
	}
	/**
	 * null,表示不启用或者失败.1表示缓存没有数据.
	 * @param ext_uuid
	 * @return
	 */
	public static Long getIncrCountByExt_uuid(String ext_uuid) {
		// TODO Auto-generated method stub
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getIncrCountByExt_uuid(ext_uuid);
		} catch (NullPointerException e) {
			logger.error("redis Connection failure!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取阅读计数,表px_count.
	 * null 表示没有命中.
	 * @param ext_uuid
	 * @return
	 */
	public static Long getCountByExt_uuid(String ext_uuid) {
		// TODO Auto-generated method stub
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getCountByExt_uuid(ext_uuid);
		} catch (NullPointerException e) {
			logger.error("redis Connection failure!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * null,表示不启用或者失败.1表示缓存没有数据.
	 * @param ext_uuid
	 * @return
	 */
	public  static List<String> getCountByExt_uuids(String[] ext_uuids){
		// TODO Auto-generated method stub
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getCountByExt_uuids(ext_uuids);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * null,表示不启用或者失败.1表示缓存没有数据.
	 * @param ext_uuid
	 * @return
	 */
	public static  List<Object> getIncrCountByExt_uuids(String[] ext_uuids){
		// TODO Auto-generated method stub
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getIncrCountByExt_uuids(ext_uuids);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 批量设置阅读计数到缓存中.
	 * @param zMap
	 */
	public static void setCountByExt_uuids( Map<String,String> zMap){
				try {
					if(pxRedisCache==null)return ;
					 pxRedisCache.setCountByExt_uuids(zMap);
				} catch (NullPointerException e) {
					logger.error("redis Connection failure!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ;
	}
	public static void setCountByExt_uuid(String ext_uuid, Long count) {
		try {
			if(pxRedisCache==null)return ;
			pxRedisCache.setCountByExt_uuid(ext_uuid, count);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getUploadFilePath(String uuid) throws Exception{
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getUploadFilePath(uuid);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
			throw e;
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		}
	}
	
	public static void setUploadFilePath(String uuid, String path) {
		try {
			if(pxRedisCache==null)return ;
			pxRedisCache.setUploadFilePath(uuid, path);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取今日统计数量,缓存延长24小时
	 * @param uuid
	 * @return
	 * @throws Exception 
	 */
	public static  String getCountOfNewMsgNumber(String uuid) throws Exception{
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getCountOfNewMsgNumber(uuid);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
			throw e;
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 今日统计数量加一,缓存延长24小时
	 * @param uuid
	 * @return
	 * @throws Exception 
	 */
	public static  void incrCountOfNewMsgNumber(String uuid) throws Exception{
		try {
			if(pxRedisCache==null)return ;
			 pxRedisCache.incrCountOfNewMsgNumber(uuid);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
			throw e;
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 设置统计数量,缓存延长24小时
	 * @param uuid
	 * @param number
	 */
	public  static void setCountOfNewMsgNumber(String uuid,String number){
		try {
			if(pxRedisCache==null)return ;
			pxRedisCache.setCountOfNewMsgNumber(uuid, number);
		}catch (NullPointerException e) {
			logger.error("redis Connection failure!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
