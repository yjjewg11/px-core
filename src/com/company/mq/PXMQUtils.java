package com.company.mq;

import java.util.List;

import com.company.mq.pool.JedisquePool;
import com.github.xetorthio.jedisque.Jedisque;
import com.github.xetorthio.jedisque.Job;

public class PXMQUtils {
	
	static JedisquePool pool=new JedisquePool();

	static public Jedisque getJedisque() {
		Jedisque jedis = pool.getResource();
		return jedis;
	}
	
	public static String addJob(String queueName, String job, long mstimeout) {
		return JedissqueMQ.addJob(queueName, job, mstimeout);
	}


	public String getJob(String queueNames) {
		
		List<Job> list=JedissqueMQ.getJob(queueNames);
		Job job=list.get(0);
		return job.getBody();
	}
}
