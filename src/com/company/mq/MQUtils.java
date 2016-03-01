package com.company.mq;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.company.news.ProjectProperties;

/**
 * MQUtils 工具类
 * @author liumingquan
 *
 */
public class MQUtils {
	
	private static Logger logger = Logger.getLogger("MQUtils");
	
	
	
	static RedisPubClient pubClient;
	static RedisSubscribeClient subscribeClient;
	
	/**
	 * 初始话发布
	 * @param queueName
	 */
	public static  void initPubClient(String queueName){
		//发布
		pubClient=new RedisPubClient(queueName);
	
	}
	/**
	 * 启动订阅监听
	 * @param queueName
	 */
	public static  void startSubscribeClient(String queueName){
		subscribeClient=new RedisSubscribeClient(queueName,new PxBaseDoJob());
		subscribeClient.start();
		
	
	}
	
	
	static{
		
		
	}
	public static  void publish(JobDetails job){
		pubClient.publish(job);
	}
	/**
	 * 停止订阅服务器,让已经在执行的服务结束后,在停止
	 */
	public static  void stopSubscribeClient(){
		subscribeClient.stopSubscribe();
	}
	
	public static void main(String[] s) throws Exception{
		
		JedissquePubClient c=new JedissquePubClient(MQConstants.QueueName_px_test);
    	Map map=new HashMap();
    	map.put("a", "a2");
    	map.put("b", "b2");
    	
    	JobDetails job=new JobDetails("BeanName","test",map);
		MQUtils.publish(job);
	}
}
