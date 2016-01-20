package com.company.news.cache.redis;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.company.news.ProjectProperties;
import com.company.news.json.JSONUtils;
import com.company.news.rest.util.TimeUtils;


/**
 * 增加自定义session管理缓存.需要配置.applicationContext.xml和ehcache.xml
 * @author liumingquan
 */
public class PxRedisCacheImpl  {
//	private CountService countService;

	private  static long  faildTime=0;
	private final static long  faildTimeInterval=1000*60*5;//间隔10分钟
//	private final static String hostname=ProjectProperties.getProperty("redis.hostname", "127.0.0.1");
	//px2 内网地址
	private  static String hostname=ProjectProperties.getProperty("redis.hostname", "10.169.128.139");
	
	
	
	private final static int port = ProjectProperties.getPropertyAsInt("redis.port", 6379);
	private  static String auth = ProjectProperties.getProperty("redis.auth", "8a29000b512652bf0151337f27fd00e5");//
	//缺陷100年后,溢出
    public static final String Date_YYYYMMDD = "yyMMdd";
    @Deprecated
	public static String Redis_Hash_Count_table_key="Hash_Count";
    @Deprecated
	public static String Redis_SortedSet_Count_table_key="SortedSet_Count";
	
	//上传图片的路径地址,和过期是24小时后
	public static String Redis_String_Path="P_";
	public static int Redis_String_Path_Expire=60*60*24;
	//今日最新总数统计.(数量小),和过期是24小时后
	public static String Redis_String_TodayNewMsgNumber="TodayNewMsg_";
	public static int Redis_String_TodayNewMsgNumber_Expire=60*60*24;
	
	
	public static long PageSize=100;
	
	
	private static Logger logger = Logger.getLogger("RedisCache");
	
	//公共点击次数
	private PxRedisCounter px_count=null;
	//话题点击次数
	private SnsTopicCounter sns_topic=null;
	
	private static JedisPool jedisPool=null;
	
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		 
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		 config.setBlockWhenExhausted(true);
		//最大空闲连接数, 默认8个
		 config.setMaxIdle(8);
		 //最大连接数, 默认8个
		 config.setMaxTotal(8);
//		 config.setMaxTotal(100);
		 config.setMaxWaitMillis(10000);
         //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
         //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		//获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
		 config.setMaxWaitMillis(-1);
         //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
         config.setTestOnBorrow(true);
       //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)

         config.setMinEvictableIdleTimeMillis(1800000);
         //最小空闲连接数, 默认0
         config.setMinIdle(0);
         
         
		 if(StringUtils.isNotBlank(auth)){
				logger.info("Redis Server:"+hostname+":"+port+",auth="+auth);
			 jedisPool =  new JedisPool(config,hostname, port, 10000,auth);
		 }else{
				logger.info("1Redis Server:"+hostname+":"+port+",auth="+auth);
			 jedisPool =  new JedisPool(config,hostname, port, 10000);
		 }
	}

	public PxRedisCacheImpl() {
		super();
		 px_count=new PxRedisCounter(this);
		 sns_topic=new SnsTopicCounter(this);
		
		
	}

	 public  Jedis getJedis() {
		 long tmpTime=System.currentTimeMillis();
		 
		 //加入超时机制
		 if(tmpTime-faildTime<faildTimeInterval){
			 return null;
		 }
	
	        try {
	        	
	       	Jedis jedis = jedisPool.getResource();
//	       	 System.out.println("jedis="+	jedis);
//	       System.out.println("getNumActive="+	jedisPool.getNumActive());
//	       System.out.println("getNumWaiters="+	jedisPool.getNumWaiters());
//	       System.out.println("getNumIdle="+	jedisPool.getNumIdle());
				return jedis;
			} catch (Exception e) {
				faildTime=tmpTime;
				logger.info("getJedis error,Redis Server:"+hostname+":"+port+",auth="+auth);
				//e.printStackTrace();
				throw e;
			}
	    }

	/**
	 * 同步当天的所有数据到数据
	 * @param countService
	 * @return
	 * @throws Exception
	 */
	 @Deprecated
	public   boolean synAllCountRedisToDb(SynPxRedisToDbInterface countService ) throws Exception{
		Jedis jedis=getJedis();
		logger.info("jedis dbsize:[" + jedis.dbSize() + "] .. ");
//		jedis.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
//		
		
		try {
			//1.读取缓存.当天时间的前一天以前的数据.
//			Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
			//score--;
			Long countSet=0l;
			
			countSet=jedis.zcard(Redis_SortedSet_Count_table_key);
			Long startIndex=0l;
			Long endIndex=0l;
			logger.info("zcard "+Redis_SortedSet_Count_table_key+",count="+countSet);
			
			if(countSet>0l){
				do{
					
					 endIndex=startIndex+PageSize;
				
					Long startTime = System.currentTimeMillis();
					
					if(countSet<endIndex){
						endIndex=countSet;
					}
					endIndex--;//0 99 共一100个
					logger.info("zrange "+Redis_SortedSet_Count_table_key+" "+startIndex+" "+endIndex+",count="+countSet);
					Set<String> set=jedis.zrange(Redis_SortedSet_Count_table_key,startIndex, endIndex);
					 String[] keys=new String[set.size()];
					 set.toArray(keys);
					 List<String> values= jedis.hmget(Redis_Hash_Count_table_key,keys );
					 
					 //2.同步数据到数据库save db
					 countService.update_synCountRedisToDb(keys,values);
					 
					 
					 //3.同步数据成功后从缓存移除.
//					 Pipeline p = jedis.pipelined();
//					 p.hdel(Redis_Hash_Count_table_key, keys);
//					p.zremrangeByRank(Redis_SortedSet_Count_table_key,  0l, endIndex);
//					 p.sync();
//					    p.close();
					 startIndex=endIndex+1;
					Long endTime = System.currentTimeMillis() - startTime;
					logger.info(" countService.update_synCountRedisToDb,count time(ms)="+endTime);
				}while(endIndex+1<countSet);
			}
			
			
			//count==1 表示缓存中没值
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}finally{
			jedis.close();
		}
	}
	 @Deprecated
	public   boolean synCountRedisToDb(SynPxRedisToDbInterface countService) throws Exception{
		
	
		Jedis jedis=getJedis();
		try {
			logger.info("jedis dbsize:[" + jedis.dbSize() + "] .. ");
//		jedis.zadd(Redis_SortedSet_Count_table_key, score, ext_uuid);
			//1.读取缓存.当天时间的前一天以前的数据.凌晨1点执行.执行前2天
			Double score=Double.valueOf(TimeUtils.getCurrentTime(Date_YYYYMMDD));
			score=score-2;
			Long countSet=0l;
			do{
				//分页处理,每页最大100
				Long pageSize=PageSize;
				//取出2天前,的总数.
				countSet=jedis.zcount(Redis_SortedSet_Count_table_key, 0l, score);
				logger.info("min=0,max="+score+",count="+countSet);
				logger.info("zcount "+Redis_SortedSet_Count_table_key+" 0 "+score+",count="+countSet);
				Long startTime = System.currentTimeMillis();
				if(countSet==0l){
					break;
				}
				if(countSet<pageSize){
					pageSize=countSet;
				}
				pageSize--;//0 99 共一100个
				//分页取第一页数据,同步到数据库
				logger.info("zrange "+Redis_SortedSet_Count_table_key+" 0 "+pageSize);
				 Set<String> set=jedis.zrange(Redis_SortedSet_Count_table_key, 0l, pageSize);
				 String[] keys=new String[set.size()];
				 set.toArray(keys);
				 List<String> values= jedis.hmget(Redis_Hash_Count_table_key,keys );
				 
				 //2.同步数据到数据库save db.正式启用时才使用
				 countService.update_synCountRedisToDb(keys,values);
				 
				 
				 //3.同步数据成功后从缓存移除.
				 Pipeline p = jedis.pipelined();
				 p.hdel(Redis_Hash_Count_table_key, keys);
				  p.zremrangeByRank(Redis_SortedSet_Count_table_key,  0l, pageSize);
				  p.sync();
				  p.close();
				
				Long endTime = System.currentTimeMillis() - startTime;
				logger.info(" countService.update_synCountRedisToDb,count time(ms)="+endTime);
				//
				countSet=countSet-pageSize;
			}while(countSet>0);
			//count==1 表示缓存中没值
			return true;
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	/**
	 * 获取上传地址,每次调用缓存延长24小时
	 * @param uuid
	 * @return
	 * @throws IOException 
	 */
	public  String getUploadFilePath(String uuid) throws IOException{
		Jedis jedis=getJedis();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//		}
		Response<String> pathResponse;
		try {
			String key=Redis_String_Path+uuid;
			
			//if(true)return jedis.get(key);
			 Pipeline p = jedis.pipelined();
			 pathResponse = p.get(key);
			 p.expire(key, Redis_String_Path_Expire);
			    p.sync();
			    p.close();
//		    List<Object> results = p.syncAndReturnAll();
				return pathResponse.get();
		}catch (Exception e) {
			////e.printStackTrace();
			throw e;
		}finally{
			jedis.close();
		}
	
	}
	/**
	 * 设置上传地址到缓存中.缓存延长24小时
	 * @param uuid
	 * @param path
	 */
	public  void setUploadFilePath(String uuid,String path){
		Jedis jedis=getJedis();
		try {
			String key=Redis_String_Path+uuid;
			jedis.setex(key, Redis_String_Path_Expire, path);
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	
	/**
	 * 获取今日统计数量,缓存延长24小时
	 * @param uuid
	 * @return
	 * @throws IOException 
	 */
	public  String getCountOfNewMsgNumber(String uuid) throws IOException{
		Jedis jedis=getJedis();
		try {
			String key=Redis_String_TodayNewMsgNumber+uuid;
			 Pipeline p = jedis.pipelined();
			 Response<String>  pathResponse= p.get(key);
			 p.expire(key, Redis_String_TodayNewMsgNumber_Expire);
			 p.sync();
			    p.close();
			
			return pathResponse.get();
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}finally{
			jedis.close();
		}
	}
	
	/**
	 *  今日统计数量加一,缓存延长24小时
	 * @param uuid
	 * @throws IOException 
	 */
	public  void incrCountOfNewMsgNumber(String uuid) throws IOException{
		Jedis jedis=getJedis();
		try {
			String key=Redis_String_TodayNewMsgNumber+uuid;
			 Pipeline p = jedis.pipelined();
			 p.incr(key);
			p.expire(key, Redis_String_TodayNewMsgNumber_Expire);
			 p.sync();
			    p.close();
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
		
	}
	/**
	 * 设置统计数量,缓存延长24小时
	 * @param uuid
	 * @param path
	 */
	public  void setCountOfNewMsgNumber(String uuid,String number){
		Jedis jedis=getJedis();
		try {
			
			String key=Redis_String_TodayNewMsgNumber+uuid;
			logger.info("jedis.setex("+key+", "+Redis_String_TodayNewMsgNumber_Expire+", "+number+")");
			jedis.setex(key, Redis_String_TodayNewMsgNumber_Expire, number);
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	/**
	 * 设置对象.转换成String
	 * @param uuid
	 * @param path
	 */
	public  void setObject(String key,Object obj){
		Jedis jedis=getJedis();
		try {
			 String objectJson = JSONUtils.getJsonString(obj);
			 
		      jedis.set(key, objectJson);
			logger.info("jedis.set("+key+", "+objectJson+")");
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	/**
	 * 设置对象.转换成String
	 * @param uuid
	 * @param path
	 */
	public  JSONObject  getJSONObject(String key){
		Jedis jedis=getJedis();
		try {
			
		      String value = jedis.get(key);
		      if(StringUtils.isBlank(value))return null;
		      JSONObject jsonObject = JSONObject.fromObject(value);
		      return jsonObject;
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	/**
	 * 设置对象.转换成String
	 * @param uuid
	 * @param path
	 */
		public  <T> T getObject(String key,Class<T> clazz){
		Jedis jedis=getJedis();
		try {
			
		      String value = jedis.get(key);
		      if(StringUtils.isBlank(value))return null;
		      return (T) JSONUtils.jsonToObject(value, clazz);
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	
	public static void main(String[] s){
		
		
		
		try {
			//hostname="120.24.61.97";
			hostname="127.0.0.1";
			auth="";
			System.out.println("Redis Server:"+hostname+":"+port+",auth="+auth);
		final PxRedisCacheImpl pxRedisCacheImpl=new PxRedisCacheImpl();
		
		
		System.out.println(pxRedisCacheImpl.getUploadFilePath("dd615439-d43c-452e-90ba-d99e25fe26ad"));
//		System.out.println(pxRedisCacheImpl.getIncrCountByExt_uuid("aa"));
//		//pxRedisCacheImpl.setCountByExt_uuid("aa", 20l);
//		System.out.println(pxRedisCacheImpl.getIncrCountByExt_uuid("aa"));
		long dd=4294967295l;
		for(long i=0;i<dd;i++){
//			pxRedisCacheImpl.setCountByExt_uuid("ext_uuid_"+i, i);
//			System.out.println(i);
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					try {
//						System.out.println(pxRedisCacheImpl.getUploadFilePath("dd615439-d43c-452e-90ba-d99e25fe26ad"));
//						
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						//e.printStackTrace();
//					}
//					
//				}
//			}).run();

		}
		//pxRedisCacheImpl.setUploadFilePath("uuid","uuid1");
		
//			System.out.println(pxRedisCacheImpl.getUploadFilePath("uuid111"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("aaa", e);
		}
		
	}

	public PxRedisCounter getPx_count() {
		return px_count;
	}

	public SnsTopicCounter getSns_topic() {
		return sns_topic;
	}

}
