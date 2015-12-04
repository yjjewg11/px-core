package com.company.news.cache;

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
	private final static String Redis_type=ProjectProperties.getProperty("Redis_type", "redis");

	private static PxRedisCacheInterface pxRedisCache=null;
	static {
		if("redis".equals(Redis_type)){
			pxRedisCache=new PxRedisCacheImpl();
		}
	}
	
	public static Long getCountByExt_uuid(String ext_uuid) {
		// TODO Auto-generated method stub
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getCountByExt_uuid(ext_uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setCountByExt_uuid(String ext_uuid, Long count) {
		try {
			if(pxRedisCache==null)return ;
			pxRedisCache.setCountByExt_uuid(ext_uuid, count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getUploadFilePath(String uuid) {
		try {
			if(pxRedisCache==null)return null;
			return pxRedisCache.getUploadFilePath(uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setUploadFilePath(String uuid, String path) {
		try {
			if(pxRedisCache==null)return ;
			pxRedisCache.setUploadFilePath(uuid, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
