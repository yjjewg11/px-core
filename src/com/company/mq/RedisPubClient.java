package com.company.mq;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.company.news.cache.redis.PxRedisCacheImpl;
import com.company.news.json.JSONUtils;

/**
 * 消息发布者 (即publish client)
 * @author liumingquan
 *
 */
public class RedisPubClient {
	String queueName=null;
	public RedisPubClient(String queueName) {
		super();
		this.queueName = queueName;
	}


	private static Logger logger = Logger.getLogger("RedisPubClient");
	Jedis jedis =null;
	
	  private void connect() {
			jedis=PxRedisCacheImpl.getJedis();
	   }
	  
	  
	  /**
	   * 发布消息
	   * @param queueName
	   * @param job
	   */
	    public void publish(String queueName,JobDetails job){
	    	 String jobJson = JSONUtils.getJsonString(job);
	    	 connect();
	    	 jedis.lpush(queueName, jobJson);
	    	 jedis.close();
	    }
	    
	    
		  
		  /**
		   * 发布消息
		   * @param queueName
		   * @param job
		   */
		    public void publish(JobDetails job){
		    	publish(queueName,job);
		    }
		    
	    public static void main(String[] s) throws Exception{
	    	RedisPubClient c=new RedisPubClient(MQConstants.QueueName_px_test);
	    	Map map=new HashMap();
	    	map.put("a", "a1");
	    	map.put("b", "b1");
	    	
	    	JobDetails job=new JobDetails("BeanName","test",map);
	    	c.publish(job);
			//c.stopSubscribe();
		}
	
}
