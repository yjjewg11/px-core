package com.company.news.cache.redis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.company.news.rest.util.TimeUtils;


/**
基于redis 的计数器.
 */
public abstract class AbstractRedisCounter  implements  SynPxRedisToDbInterface{
	private  String key=null;
	//用于计数
	private String _hashKeyName=null;
	//用于清除2天前,不再更新的数据.
	private String _sortKeyName=null;
	
	//分页同步数据
	public static long PageSize=100;
	
	private static Logger logger = Logger.getLogger("PxRedisCounter");
	
	private PxRedisCacheImpl pxRedisCacheImpl;
	
	

	 public AbstractRedisCounter(String key, PxRedisCacheImpl pxRedisCacheImpl) {
		super();
		this.key = key;
		//_hashKeyName="H_Count_key";
		_hashKeyName="Hash_Count_"+key;
		_sortKeyName="SortedSet_Count_"+key;
		this.pxRedisCacheImpl = pxRedisCacheImpl;
		
		if(StringUtils.isBlank(key)){
			throw new RuntimeException("key is not allow empty!");
		}
	}

	private  Jedis getJedis() {
		 return pxRedisCacheImpl.getJedis();
	    }
	
	public  Long getIncrCountByExt_uuid(String ext_uuid) throws Exception{
		Jedis jedis=getJedis();
		try {
			Double score=Double.valueOf(TimeUtils.getCurrentTime(PxRedisCacheImpl.Date_YYYYMMDD));
			 Pipeline p = jedis.pipelined();
			 Response<Long>  countResponse= p.hincrBy(_hashKeyName, ext_uuid,1l);
			   p.zadd(_sortKeyName, score, ext_uuid);
			    p.sync();
			    p.close();
			//count==1 表示缓存中没值
			return countResponse.get();
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	/**
	 * null 表示没有命中.
	 * @param ext_uuid
	 * @return
	 * @throws IOException 
	 */
	public  Long getCountByExt_uuid(String ext_uuid) throws IOException{
		Jedis jedis=getJedis();
		try {
			Double score=Double.valueOf(TimeUtils.getCurrentTime(PxRedisCacheImpl.Date_YYYYMMDD));
			 Pipeline p = jedis.pipelined();
			 Response<String>  countResponse= p.hget(_hashKeyName, ext_uuid);
			 p.zadd(_sortKeyName, score, ext_uuid);
			    p.sync();
			    p.close();//防止JedisConnectionException: Unexpected end of stream.
			//count==1 表示缓存中没值
			String countStr= countResponse.get();
			Long count=null;
			if(countStr!=null){
				count=Long.valueOf(countStr);
			}
			return count;
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	

	/**
	 * 返回list,不加一:
	 * 1://count==null 表示缓存中没值
	 * @param ext_uuids
	 * @return
	 * @throws IOException 
	 */
	public   List<String> getCountByExt_uuids(String[] ext_uuids) throws IOException{
		Jedis jedis=getJedis();
//		
		try {
			Double score=Double.valueOf(TimeUtils.getCurrentTime(PxRedisCacheImpl.Date_YYYYMMDD));
			
			 Pipeline p = jedis.pipelined();
			 Map<String,Double> zMap=new HashMap();
			 for(int i=0;i<ext_uuids.length;i++){
				 
				 zMap.put(ext_uuids[i], score);
			 }
			 Response<List<String>> results= p.hmget(_hashKeyName, ext_uuids);
			  p.zadd(_sortKeyName, zMap);
			 p.sync();
			    p.close();
			//count==1 表示缓存中没值
			return results.get();
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally{
			jedis.close();
		}
	}
	
	/**
	 * 返回list:
	 * 1://count==1 表示缓存中没值
	 * @param ext_uuids
	 * @return
	 * @throws IOException 
	 */
	public   List<Object> getIncrCountByExt_uuids(String[] ext_uuids) throws IOException{
		Jedis jedis=getJedis();
//		
		try {
			Double score=Double.valueOf(TimeUtils.getCurrentTime(PxRedisCacheImpl.Date_YYYYMMDD));
			
			 Pipeline p = jedis.pipelined();
			 Map<String,Double> zMap=new HashMap();
			 for(int i=0;i<ext_uuids.length;i++){
				 
				p.hincrBy(_hashKeyName, ext_uuids[i],1l);
				 zMap.put(ext_uuids[i], score);
			 }
			p.zadd(_sortKeyName, zMap);
			 List<Object> results = p.syncAndReturnAll();
			    p.close();
			//count==1 表示缓存中没值
			return results;
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}finally{
			jedis.close();
		}
	}

	
	public  void setCountByExt_uuid(String ext_uuid,Long count) throws Exception{
		Jedis jedis=getJedis();
		try {
			Double score=Double.valueOf(TimeUtils.getCurrentTime(PxRedisCacheImpl.Date_YYYYMMDD));
			
			 Pipeline p = jedis.pipelined();
			 p.hset(_hashKeyName, ext_uuid, count+"");
			 p.zadd(_sortKeyName, score, ext_uuid);
			    p.sync();
			    p.close();//防止JedisConnectionException: Unexpected end of stream.
			    
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}finally{
			jedis.close();
		}
	}

	public  void setCountByExt_uuids( Map<String,String> zMap){
		Jedis jedis=getJedis();
		try {
			jedis.hmset(_hashKeyName, zMap);
		}catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}finally{
			jedis.close();
		}
	}
	
	
	/**
	 * 同步当天的所有数据到数据
	 * @param countService
	 * @return
	 * @throws Exception
	 */
	public   boolean synAllCountRedisToDb() throws Exception{
		
		
		Long startTime = System.currentTimeMillis() ;
		logger.warn("synAllCountRedisToDb start-----------------------------");
		
		
		Jedis jedis=getJedis();
		logger.info("jedis dbsize:[" + jedis.dbSize() + "] .. ");
//		jedis.zadd(_sortKeyName, score, ext_uuid);
//		
		
		try {
			//1.读取缓存.当天时间的前一天以前的数据.
//			Double score=Double.valueOf(TimeUtils.getCurrentTime(PxRedisCacheImpl.Date_YYYYMMDD));
			//score--;
			Long countSet=0l;
			
			countSet=jedis.zcard(_sortKeyName);
			Long startIndex=0l;
			Long endIndex=0l;
			logger.info("zcard "+_sortKeyName+",count="+countSet);
			
			if(countSet>0l){
				do{
					
					 endIndex=startIndex+PageSize;
				
					Long startTime_sub = System.currentTimeMillis();
					
					if(countSet<endIndex){
						endIndex=countSet;
					}
					endIndex--;//0 99 共一100个
					logger.info("zrange "+_sortKeyName+" "+startIndex+" "+endIndex+",count="+countSet);
					Set<String> set=jedis.zrange(_sortKeyName,startIndex, endIndex);
					 String[] keys=new String[set.size()];
					 set.toArray(keys);
					 List<String> values= jedis.hmget(_hashKeyName,keys );
					 
					 //2.同步数据到数据库save db
					 update_synCountRedisToDb(keys,values);
					 
					 
					 //3.同步数据成功后从缓存移除.
//					 Pipeline p = jedis.pipelined();
//					 p.hdel(_sortKeyName, keys);
//					p.zremrangeByRank(_sortKeyName,  0l, endIndex);
//					 p.sync();
//					    p.close();
					 startIndex=endIndex+1;
					Long endTime = System.currentTimeMillis() - startTime_sub;
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
			
			 Long endTime = System.currentTimeMillis() - startTime;
				logger.warn("synAllCountRedisToDb start----------count time(ms)="+endTime);
		}
	}
	public   boolean synCountRedisToDb() throws Exception{
		Long startTime = System.currentTimeMillis() ;
		logger.warn("synCountRedisToDb start-----------------------------");
	
		Jedis jedis=getJedis();
		try {
			logger.info("jedis dbsize:[" + jedis.dbSize() + "] .. ");
//		jedis.zadd(_sortKeyName, score, ext_uuid);
			//1.读取缓存.当天时间的前一天以前的数据.凌晨1点执行.执行前2天
			Double score=Double.valueOf(TimeUtils.getCurrentTime(PxRedisCacheImpl.Date_YYYYMMDD));
			score=score-2;
			Long countSet=0l;
			do{
				//分页处理,每页最大100
				Long pageSize=PageSize;
				//取出2天前,的总数.
				countSet=jedis.zcount(_sortKeyName, 0l, score);
				logger.info("min=0,max="+score+",count="+countSet);
				logger.info("zcount "+_sortKeyName+" 0 "+score+",count="+countSet);
				Long startTime_sub = System.currentTimeMillis();
				if(countSet==0l){
					break;
				}
				if(countSet<pageSize){
					pageSize=countSet;
				}
				pageSize--;//0 99 共一100个
				//分页取第一页数据,同步到数据库
				logger.info("zrange "+_sortKeyName+" 0 "+pageSize);
				 Set<String> set=jedis.zrange(_sortKeyName, 0l, pageSize);
				 String[] keys=new String[set.size()];
				 set.toArray(keys);
				 List<String> values= jedis.hmget(_hashKeyName,keys );
				 
				 //2.同步数据到数据库save db.正式启用时才使用
				 update_synCountRedisToDb(keys,values);
				 
				 
				 //3.同步数据成功后从缓存移除.
				 Pipeline p = jedis.pipelined();
				 logger.info("hdel "+_hashKeyName+"  "+keys.length);
				 p.hdel(_hashKeyName, keys);
				 logger.info("zremrangeByRank "+_sortKeyName+"  0 "+pageSize);
				  p.zremrangeByRank(_sortKeyName,  0l, pageSize);
				  p.sync();
				  p.close();
				
				Long endTime = System.currentTimeMillis() - startTime_sub;
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
			Long endTime = System.currentTimeMillis() - startTime;
			logger.warn("synCountRedisToDb start----------count time(ms)="+endTime);
			jedis.close();
		}
	}
	
	
	
	public static void main(String[] s){
		
		
	}

}
