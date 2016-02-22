package com.company.mq;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.news.ProjectProperties;
import com.github.xetorthio.jedisque.Jedisque;
import com.github.xetorthio.jedisque.Job;

/**
 * 消息订阅者 
 * @author liumingquan
 *1.有bug.能去到已经取出的.
 *2.超时不稳定.
 */
@Deprecated
public class JedissqueSubscribeClient extends Thread {
	
	private static Logger logger = Logger.getLogger("JedissqueSubscribeClient");
	Jedisque jedisque =null;
	boolean isRun=true;
	/**
	 * 是否正在执行jobb,执行中不允许强制退出
	 */
	boolean isDoingJob=false;
	   private void connect()  throws URISyntaxException{
//		   String urisString=ProjectProperties.getProperty("jedisque.uris", "disque://120.25.212.44:7711,disque://120.25.212.44:7711");
		   String urisString=ProjectProperties.getProperty("jedisque.uris", "disque://120.25.212.44:7711");
			
		   logger.info("jedisque.uris="+urisString);
		   String enable=ProjectProperties.getProperty("jedisque.enable", "true");
			if(!enable.equals("true")){
				return;
			}
			String[] urisStringArr=urisString.split(",");
			URI[] uris=new URI[urisStringArr.length];
			for(int i=0;i<urisStringArr.length;i++){
				uris[i]=new URI(urisStringArr[i]);
			}
			jedisque= new Jedisque(uris);
			jedisque.setConnectionTimeout(9999999);
//			jedisque= PXMQUtils.getJedisque();
	   }
	
	String queueNames=null;
	DoJob doJob;
	   private void subscribe(){

	       
	        
	        try {
//				 System.out.println(jedisque.info());
//	        	 System.out.println("qlen="+jedisque.qlen(queueNames));
	          //  connect();
	        	long dd=jedisque.qlen(queueNames);
	        	 System.out.println("qlen="+dd);
	        	if(dd==0l){
	        		
	        		this.sleep(500);
	        		return;
	        	}
				List<Job> list=  jedisque.getJob(99999,1,queueNames);
//				jedisque.close();
//				jedisque.disconnect();
				Job job=list.get(0);
				isDoingJob=true;
				doJob.doJob(job.getBody());
				isDoingJob=false;
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				try {
					this.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 System.out.println("next");
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
	            System.out.println();
	            System.out.println("----------SUBSCRIBE start-------queueNames="+this.queueNames);
	        
	           while(isRun){
	        	   subscribe();
//	        	   System.out.println("isRun="+isRun);
	           }
	           jedisque.close();
	           jedisque.disconnect();
	           System.out.println("----------SUBSCRIBE end-------queueNames="+this.queueNames);
	            System.out.println();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            System.out.println("finally===========================================");
	        	if(jedisque!=null)jedisque.close();
	        	if(jedisque!=null)jedisque.disconnect();
	        }

	    }
	    
	    /**
	     * 停止订阅
	     * @throws InterruptedException 
	     */
	    public void stopSubscribe() throws InterruptedException{
	    	isRun=false;
	    	 while(isDoingJob=false){
	    		 this.sleep(1000);
	           }
	    	 //强制关闭.订阅是阻塞.
	    	 this.stop();
	    	 logger.info("stopSubscribe");
	    }

	public static void main(String[] s) throws Exception{
		JedissqueSubscribeClient c=new JedissqueSubscribeClient(MQConstants.QueueName_px_test,new PxBaseDoJob());
		c.start();
		Thread.sleep(5*1000);
		//c.stopSubscribe();
	}
	public JedissqueSubscribeClient(String queueNames, DoJob doJob) {
		super();
		this.queueNames = queueNames;
		this.doJob = doJob;
	}
}
