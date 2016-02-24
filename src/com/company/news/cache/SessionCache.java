package com.company.news.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.company.common.SpringContextHolder;
import com.company.http.PxHttpSession;

/**
 * 增加自定义session管理缓存.需要配置.applicationContext.xml和ehcache.xml
 * @author liumingquan
 */
public class SessionCache {
	private static Logger logger = Logger.getLogger(SessionCache.class);
	//需要在spring配置文件中配置.
	/**
	 * 
	 * applicationContext.xml
<bean id="cacheManager"
		class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean">
		<property name="configLocation">
			<value>classpath:ehcache.xml</value>
		</property>
	</bean>
	
	<bean id="sessionCache" class="org.springframework.cache.ehcache.EhCacheFactoryBean">
		<property name="cacheManager">
			<ref local="cacheManager" />
		</property>
		<!-- 用于session使用缓存 关联ehcache.xml中的缓存配置 -->
		<property name="cacheName" value="sessionCache" />
	</bean>

ehcache.xml
<cache name="sessionCache" maxElementsInMemory="100000"
		eternal="false" overflowToDisk="false"
		timeToIdleSeconds="3600" memoryStoreEvictionPolicy="LRU" />
	 */
	private static Cache sessionCache = (Cache) SpringContextHolder
			.getBean("sessionCache");

	public static void putPxHttpSession(PxHttpSession session) {
		Element e = new Element(session.getId(), session);
		e .setTimeToIdle(3600);//设置缓存在失效前的允许闲置时间。仅当缓存不是永久有效时使用(timeToLiveSeconds != 0)  
//		logger.info("MemoryStoreSize1,="+sessionCache.getMemoryStoreSize());
//		logger.info("putPxHttpSession,id="+session.getId());
		sessionCache.put(e);
//		logger.info("MemoryStoreSize2,="+sessionCache.getMemoryStoreSize());
		logger.info("pxsessionSize="+sessionCache.getSize());
	}

	public static PxHttpSession getPxHttpSession(String jessionid) {

		try {
			Element e = sessionCache.get(jessionid);
			if (e != null)
				return (PxHttpSession) e.getObjectValue();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void removePxHttpSession(String jessionid) {

		sessionCache.remove(jessionid);
		logger.info("pxsessionSize.remove="+sessionCache.getSize());
	}

}
