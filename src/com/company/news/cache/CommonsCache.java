package com.company.news.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.company.common.SpringContextHolder;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.User;


public class CommonsCache{
	private static Logger logger = Logger.getLogger("CommonsCache");
	private static Cache dbDataCache = (Cache) SpringContextHolder
			.getBean("dbDataCache");
    private static NSimpleHibernateDao nSimpleHibernateDao=SpringContextHolder.getBean("NSimpleHibernateDao");

	
	// 获取自动保存内容
	public static Object get(String uuid,Class clazz) {
		logger.info(clazz.getSimpleName()+":get:uuid-->" + uuid);
		Element e = dbDataCache.get(uuid+"_"+clazz.getSimpleName());
 
		if (e != null)
			return  e.getObjectValue();
		else {
			Object object =nSimpleHibernateDao.getObject(clazz, uuid);
			if (object != null){
				nSimpleHibernateDao.getHibernateTemplate().evict(object);
				put(uuid, object);
			}
				
			return object;
		}
	}

	// 存入自动保存内容
	public static void put(String uuid, Object user) {
		logger.info(user.getClass().getSimpleName()+":put:uuid-->" + uuid);
		Element e = new Element(uuid+"_"+user.getClass().getSimpleName(), user);
		dbDataCache.put(e);
	}
	
	

}