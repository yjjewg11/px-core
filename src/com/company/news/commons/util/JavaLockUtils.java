package com.company.news.commons.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java 线程同步锁,根据key做同一锁.
 * @author liumingquan
 *
 */
public class JavaLockUtils {
	private static Map<String,String> lockMap=new ConcurrentHashMap();
	
	static public String getLockObj(String key){
		String lockObject=null;
		lockObject=lockMap.get(key);
		if(lockObject==null){
			lockObject=key;
			lockMap.put(key, key);
		}
		return lockObject;
	}
	
	static public void removeLockObj(String key){
		lockMap.remove(key);
		
	}
    
}
