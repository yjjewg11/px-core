package com.company.mq;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.company.news.ProjectProperties;
import com.company.news.cache.redis.PxRedisCacheImpl;
import com.github.xetorthio.jedisque.Jedisque;
import com.github.xetorthio.jedisque.Job;

/**
 * 消息订阅者 
 * @author liumingquan
 *
 */
public class RedisSubscribeClient extends Thread {
	
	private static Logger logger = Logger.getLogger("RedisSubscribeClient");
	boolean isRun=true;
	Jedis jedis;


	/**
	 * 是否正在执行jobb,执行中不允许强制退出
	 */
	boolean isDoingJob=false;
	   private void connect(){
		   
		   jedis=PxRedisCacheImpl.getJedis();
	   }
	
	String queueNames=null;
	DoJob doJob;
	   private void subscribe(){

	       
	        
	        try {
//				 System.out.println(jedisque.info());
//	        	 System.out.println("qlen="+jedisque.qlen(queueNames));
	          //  connect();
	        	String jobJson=jedis.rpop(queueNames);
	        	if(jobJson!=null){
	        		
//					jedisque.close();
//					jedisque.disconnect();
					isDoingJob=true;
					doJob.doJob(jobJson);
					isDoingJob=false;
	        	}else{
	        		this.sleep(1500);
	        		// System.out.print(".");
	        	}
	        	
			} catch (Exception e) {
				e.printStackTrace();
				
				// TODO Auto-generated catch block
				try {
					this.sleep(60000);
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 connect();
//				 System.out.print(".");
			}
			
	    }

	    public void unsubscribe(String channel){
	        System.out.println("  >>> 取消订阅(UNSUBSCRIBE) > Channel:"+channel);
	        System.out.println();

	    }

	    @Override
	    public void run() {
	        try{
	        	 connect();
//	            System.out.println();
//	            System.out.println("----------SUBSCRIBE start-------queueNames="+this.queueNames);
	        logger.warn("----------SUBSCRIBE start-------queueNames="+this.queueNames);
	           while(isRun){
	        	   subscribe();
//	        	   System.out.println("isRun="+isRun);
	           }
	           jedis.close();
	           logger.warn("----------SUBSCRIBE end-------queueNames="+this.queueNames);
//	           System.out.println("----------SUBSCRIBE end-------queueNames="+this.queueNames);
//	            System.out.println();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            System.out.println("finally===========================================");
	        }

	    }
	    
	    /**
	     * 停止订阅
	     * @throws InterruptedException 
	     */
	    public void stopSubscribe(){
	    	isRun=false;
	    	 while(isDoingJob=false){
	    		 try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           }
	    	 //强制关闭.订阅是阻塞.
	    	 this.stop();
	    	 logger.info("stopSubscribe");
	    }

	public static void main(String[] s) throws Exception{
		RedisSubscribeClient c=new RedisSubscribeClient(MQConstants.QueueName_px_test,new PxBaseDoJob());
		c.start();
		Thread.sleep(5*1000);
		//c.stopSubscribe();
	}
	public RedisSubscribeClient(String queueNames, DoJob doJob) {
		super();
		this.queueNames = queueNames;
		this.doJob = doJob;
	}
}
