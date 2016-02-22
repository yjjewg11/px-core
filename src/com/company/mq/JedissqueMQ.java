package com.company.mq;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.news.ProjectProperties;
import com.github.xetorthio.jedisque.Jedisque;
import com.github.xetorthio.jedisque.Job;

public class JedissqueMQ {
	
	private static Logger logger = Logger.getLogger("JedissqueMQ");
	
	static Jedisque jedisque =null;
	
	
	static{
		try {
			connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}
	public static void connect() throws URISyntaxException{
		String urisString=ProjectProperties.getProperty("jedisque.uris", "disque://120.25.212.44:7711,disque://120.25.212.44:7711");
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
	}
	
	
	public static String addJob(String queueName, String job, long mstimeout) {
		return jedisque.addJob(queueName, job, mstimeout);
	}


	public static List<Job> getJob(String... queueNames) {
		return jedisque.getJob(queueNames);
	}

	
}
