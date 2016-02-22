package com.company.mq;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

import com.company.common.SpringContextHolder;
import com.company.news.json.JSONUtils;

public class PxBaseDoJob implements DoJob {

	private static Logger logger = Logger.getLogger("PxBaseDoJob");
	@Override
	public String doJob(String job) {
		// TODO Auto-generated method stub
		try {
			JobDetails jobDetails=(JobDetails)JSONUtils.jsonToObject(job, JobDetails.class);
			
			Object obj=SpringContextHolder.getBean(jobDetails.getBeanName());
			//test
//			Object obj=new PxBaseDoJob();
			
			
			
			Method method =ReflectionUtils.findMethod(obj.getClass(), jobDetails.getMethodName(),Map.class);
			long startTime = 0;
			long endTime = 0;
			startTime = System.currentTimeMillis();
			ReflectionUtils.invokeMethod(method, obj, jobDetails.getArgs());
			endTime = System.currentTimeMillis() - startTime;
			String msg="cost time(ns)="+endTime+",PxBaseDoJob.doJob:"+job;
			//System.out.println(msg);
			logger.info(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("not JobDetails json fomat!job="+job);
		}
		
		
		return "0";
	}
	public void test(Map job) {
		// TODO Auto-generated method stub
		System.out.println("test===="+job.get("a"));
	
	}

}
