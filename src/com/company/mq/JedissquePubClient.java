package com.company.mq;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.company.news.json.JSONUtils;
import com.github.xetorthio.jedisque.Jedisque;

/**
 * 消息发布者 (即publish client)
 * @author liumingquan
 *
 */
public class JedissquePubClient {
	String queueName=null;
	public JedissquePubClient(String queueName) {
		super();
		this.queueName = queueName;
	}


	private static Logger logger = Logger.getLogger("JedissqueSubscribeClient");
	Jedisque jedisque =null;
	
	  private void connect() {
			jedisque= PXMQUtils.getJedisque();
	   }
	  
	  
	  /**
	   * 发布消息
	   * @param queueName
	   * @param job
	   */
	    public void publish(String queueName,JobDetails job){
	    	 String jobJson = JSONUtils.getJsonString(job);
	        jedisque.addJob(queueName, jobJson, 0);
	    }
	    
	    
		  
		  /**
		   * 发布消息
		   * @param queueName
		   * @param job
		   */
		    public void publish(JobDetails job){
		    	 String jobJson = JSONUtils.getJsonString(job);
		    	 connect();
		        jedisque.addJob(queueName, jobJson, 0);
		        jedisque.close();
		    }
		    
	    public static void main(String[] s) throws Exception{
	    	JedissquePubClient c=new JedissquePubClient(MQConstants.QueueName_px_test);
	    	Map map=new HashMap();
	    	map.put("a", "a1");
	    	map.put("b", "b1");
	    	
	    	JobDetails job=new JobDetails("BeanName","test",map);
	    	c.publish(job);
			//c.stopSubscribe();
		}
	
}
