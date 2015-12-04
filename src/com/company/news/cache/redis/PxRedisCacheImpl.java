package com.company.news.cache.redis;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.company.news.ProjectProperties;
import com.company.news.cache.PxRedisCacheInterface;
import com.company.news.rest.util.TimeUtils;


/**
 * 增加自定义session管理缓存.需要配置.applicationContext.xml和ehcache.xml
 * @author liumingquan
 */
@Entity public class PxRedisCacheImpl  implements PxRedisCacheInterface{
	

	private final static String hostname=ProjectProperties.getProperty("redis.hostname", "127.0.0.1");
	private final static int port = ProjectProperties.getPropertyAsInt("redis.port", 6379);
	private final static String auth = ProjectProperties.getProperty("redis.auth", "");
	//缺陷
    public static final String Date_YYYYMMDD = "yyMMdd"
    		;
	public static String Redis_Hash_Count_table_key="Hash_Count";
	public static String Redis_SortedSet_Count_table_key="SortedSet_Count";
	
	//上传图片的路径地址,和过期是24小时后
	public static String Redis_String_Path="P_";
	public static int Redis_String_Path_Expire=60*60*24;
	
	private static Logger logger = Logger.getLogger("RedisCache");
	
	
	private JedisPool jedisPool=null;

	public PxRedisCacheImpl() {
		super();
		 jedisPool =  new JedisPool(new JedisPoolConfig(),hostname, port, 2000);
	}

	 public  Jedis getJedis() {
		 
//		 Jedis jedis = pool.getResource();
//		    jedis.auth("foobared");
	        return jedisPool.getResource();
	    }
	
	public  Long getCountByExt_uuid(String ext_uuid){
		Jedis jedis=getJedis();
//		Long dd=jedis.hincrBy(Redis_Hash_Count_table_key, ext_uuid,1l);
//		jedis.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
//		
		Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
		 Pipeline p = jedis.pipelined();
		 Response<Long>  countResponse= p.hincrBy(Redis_Hash_Count_table_key, ext_uuid,1l);
		    p.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
		    p.sync();
//		    List<Object> results = p.syncAndReturnAll();
		jedis.close();
		//count==1 表示缓存中没值
		return countResponse.get();
	}
	
	public  void setCountByExt_uuid(String ext_uuid,Long count){
		Jedis jedis=getJedis();
		jedis.hset(Redis_Hash_Count_table_key, ext_uuid, count+"");
		jedis.close();
	}
	
	/**
	 * 获取上传地址,每次调用缓存延长24小时
	 * @param uuid
	 * @return
	 */
	public  String getUploadFilePath(String uuid){
		Jedis jedis=getJedis();
		String key=Redis_String_Path+uuid;
		 Pipeline p = jedis.pipelined();
		 Response<String>  pathResponse= p.get(key);
		 p.expire(key, Redis_String_Path_Expire);
		    p.sync();
//		    List<Object> results = p.syncAndReturnAll();
		jedis.close();
		
		return pathResponse.get();
	}
	/**
	 * 设置上传地址到缓存中.缓存延长24小时
	 * @param uuid
	 * @param path
	 */
	public  void setUploadFilePath(String uuid,String path){
		Jedis jedis=getJedis();
		String key=Redis_String_Path+uuid;
		jedis.setex(key, Redis_String_Path_Expire, path);
		jedis.close();
	}
	
	
	public static void main(String[] s){
		PxRedisCacheImpl pxRedisCacheImpl=new PxRedisCacheImpl();
		System.out.println(pxRedisCacheImpl.getCountByExt_uuid("aa"));
		pxRedisCacheImpl.setCountByExt_uuid("aa", 20l);
		System.out.println(pxRedisCacheImpl.getCountByExt_uuid("aa"));
	}

}
