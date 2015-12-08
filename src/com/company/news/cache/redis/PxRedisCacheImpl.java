package com.company.news.cache.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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
public class PxRedisCacheImpl  implements PxRedisCacheInterface{
//	private CountService countService;

	private  static long  faildTime=0;
	private final static long  faildTimeInterval=1000*60*5;//间隔10分钟
//	private final static String hostname=ProjectProperties.getProperty("redis.hostname", "127.0.0.1");
	//private final static String hostname=ProjectProperties.getProperty("redis.hostname", "10.169.128.139");
	private final static String hostname=ProjectProperties.getProperty("redis.hostname", "120.24.61.97");
	
	
	
	private final static int port = ProjectProperties.getPropertyAsInt("redis.port", 6379);
	private final static String auth = ProjectProperties.getProperty("redis.auth", "8a29000b512652bf0151337f27fd00e5");
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
		 if(StringUtils.isNotBlank(auth)){
				logger.info("Redis Server:"+hostname+":"+port+",auth="+auth);
			 jedisPool =  new JedisPool(new JedisPoolConfig(),hostname, port, 10000,auth);
		 }else{
				logger.info("1Redis Server:"+hostname+":"+port+",auth="+auth);
			 jedisPool =  new JedisPool(new JedisPoolConfig(),hostname, port, 10000);
		 }
		
	}

	 public  Jedis getJedis() {
		 long tmpTime=System.currentTimeMillis();
		 
		 //加入超时机制
		 if(tmpTime-faildTime<faildTimeInterval){
			 return null;
		 }
	
	        try {
	        	
	       	 Jedis jedis = jedisPool.getResource();
//			   if(StringUtils.isNotBlank(auth))jedis.auth(auth);
			    
				return jedis;
			} catch (Exception e) {
				faildTime=tmpTime;
				logger.info("Redis Server:"+hostname+":"+port+",auth="+auth);
				e.printStackTrace();
				throw e;
			}
	    }
	
	public  Long getIncrCountByExt_uuid(String ext_uuid){
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
	
	/**
	 * null 表示没有命中.
	 * @param ext_uuid
	 * @return
	 */
	public  Long getCountByExt_uuid(String ext_uuid){
		Jedis jedis=getJedis();
//		Long dd=jedis.hincrBy(Redis_Hash_Count_table_key, ext_uuid,1l);
//		jedis.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
//		
		Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
		 Pipeline p = jedis.pipelined();
		 Response<String>  countResponse= p.hget(Redis_Hash_Count_table_key, ext_uuid);
		    p.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
		    p.sync();
//		    List<Object> results = p.syncAndReturnAll();
		jedis.close();
		//count==1 表示缓存中没值
		String countStr= countResponse.get();
		Long count=null;
		if(countStr!=null){
			count=Long.valueOf(countStr);
		}
		return count;
	}
	
	

	/**
	 * 返回list,不加一:
	 * 1://count==null 表示缓存中没值
	 * @param ext_uuids
	 * @return
	 */
	public   List<String> getCountByExt_uuids(String[] ext_uuids){
		Jedis jedis=getJedis();
//		
		Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
		
		 Pipeline p = jedis.pipelined();
		 Map<String,Double> zMap=new HashMap();
		 for(int i=0;i<ext_uuids.length;i++){
			 
			 zMap.put(ext_uuids[i], score);
		 }
		 Response<List<String>> results= p.hmget(Redis_Hash_Count_table_key, ext_uuids);
		 p.zadd(Redis_SortedSet_Count_table_key, zMap);
		 p.sync();
		 
		jedis.close();
		//count==1 表示缓存中没值
		return results.get();
	}
	
	/**
	 * 返回list:
	 * 1://count==1 表示缓存中没值
	 * @param ext_uuids
	 * @return
	 */
	public   List<Object> getIncrCountByExt_uuids(String[] ext_uuids){
		Jedis jedis=getJedis();
//		
		Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
		
		 Pipeline p = jedis.pipelined();
		 Map<String,Double> zMap=new HashMap();
		 for(int i=0;i<ext_uuids.length;i++){
			 
			 p.hincrBy(Redis_Hash_Count_table_key, ext_uuids[i],1l);
			 zMap.put(ext_uuids[i], score);
		 }
		 p.zadd(Redis_SortedSet_Count_table_key, zMap);
		 List<Object> results = p.syncAndReturnAll();
		 
		jedis.close();
		//count==1 表示缓存中没值
		return results;
	}

	/**
	 * 同步当天的所有数据到数据
	 * @param countService
	 * @return
	 * @throws Exception
	 */
	public   boolean synAllCountRedisToDb(SynPxRedisToDbInterface countService ) throws Exception{
		Jedis jedis=getJedis();
//		jedis.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
//		
		//1.读取缓存.当天时间的前一天以前的数据.
		Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
		//score--;
		Long countSet=0l;
		countSet=jedis.zcount(Redis_SortedSet_Count_table_key, score, score);
		Long startIndex=0l;
		do{
			
			Long endIndex=startIndex+100;
			logger.info("min=0,max="+score+",count="+countSet);
			Long startTime = System.currentTimeMillis();
			
			if(countSet<endIndex){
				endIndex=countSet;
			}
			
			 Set<String> set=jedis.zrange(Redis_SortedSet_Count_table_key,startIndex, endIndex);
			 String[] keys=new String[set.size()];
			 set.toArray(keys);
			 List<String> values= jedis.hmget(Redis_Hash_Count_table_key,keys );
			 
			 //2.同步数据到数据库save db
			 countService.update_synCountRedisToDb(keys,values);
			 
			 
			 //3.同步数据成功后从缓存移除.
			 Pipeline p = jedis.pipelined();
			 p.hdel(Redis_Hash_Count_table_key, keys);
			 p.zremrangeByRank(Redis_SortedSet_Count_table_key,  0l, endIndex);
			 p.sync();
//			List<Object> results = p.syncAndReturnAll();
		
			
			 startIndex=endIndex;
			Long endTime = System.currentTimeMillis() - startTime;
			logger.info(" countService.update_synCountRedisToDb,count time(ms)="+endTime);
		}while(countSet>0);
		
		jedis.close();
		//count==1 表示缓存中没值
		return true;
	}
	public   boolean synCountRedisToDb(SynPxRedisToDbInterface countService) throws Exception{
		
	
		Jedis jedis=getJedis();
//		jedis.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
//		
		//1.读取缓存.当天时间的前一天以前的数据.凌晨1点执行.执行前2天
		Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
		score=score-2;
		Long countSet=0l;
		
		do{
			Long pageSize=100l;
			 countSet=jedis.zcount(Redis_SortedSet_Count_table_key, 0l, score);
			logger.info("min=0,max="+score+",count="+countSet);
			Long startTime = System.currentTimeMillis();
			
			if(countSet<pageSize){
				pageSize=countSet;
			}
			countSet=countSet-pageSize;
			 Set<String> set=jedis.zrange(Redis_SortedSet_Count_table_key, 0l, pageSize);
			 String[] keys=new String[set.size()];
			 set.toArray(keys);
			 List<String> values= jedis.hmget(Redis_Hash_Count_table_key,keys );
			 
			 //2.同步数据到数据库save db
			 countService.update_synCountRedisToDb(keys,values);
			 
			 
			 //3.同步数据成功后从缓存移除.
			 Pipeline p = jedis.pipelined();
			 p.hdel(Redis_Hash_Count_table_key, keys);
			 p.zremrangeByRank(Redis_SortedSet_Count_table_key,  0l, pageSize);
			 p.sync();
//			List<Object> results = p.syncAndReturnAll();
		
			
			
			Long endTime = System.currentTimeMillis() - startTime;
			logger.info(" countService.update_synCountRedisToDb,count time(ms)="+endTime);
		}while(countSet>0);
		
		jedis.close();
		//count==1 表示缓存中没值
		return true;
	}
	
	public  void setCountByExt_uuid(String ext_uuid,Long count){
		Jedis jedis=getJedis();
		jedis.hset(Redis_Hash_Count_table_key, ext_uuid, count+"");
		jedis.close();
	}

	public  void setCountByExt_uuids( Map<String,String> zMap){
		Jedis jedis=getJedis();
		jedis.hmset(Redis_Hash_Count_table_key, zMap);
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
		System.out.println(pxRedisCacheImpl.getIncrCountByExt_uuid("aa"));
		//pxRedisCacheImpl.setCountByExt_uuid("aa", 20l);
		System.out.println(pxRedisCacheImpl.getIncrCountByExt_uuid("aa"));
		System.out.println(pxRedisCacheImpl.getUploadFilePath("uuid"));
		pxRedisCacheImpl.setUploadFilePath("uuid","uuid1");
		System.out.println(pxRedisCacheImpl.getUploadFilePath("uuid"));
	}

}
