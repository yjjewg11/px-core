package com.company.news.cache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.company.common.SpringContextHolder;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.BaseDataList;
import com.company.news.entity.User;


public class CommonsCache{
	private static Logger logger = Logger.getLogger("CommonsCache");
	private static Cache dbDataCache = (Cache) SpringContextHolder
			.getBean("dbDataCache");
    private static NSimpleHibernateDao nSimpleHibernateDao=SpringContextHolder.getBean("NSimpleHibernateDao");

	
    
    /**
	 * 查询集成数据列表根据typid
	 * 
	 * @return
	 */
	public static List<BaseDataList> getBaseDataListByTypeuuid(String typeuuid) {
		if(StringUtils.isEmpty(typeuuid))
			return null;
		
		return (List<BaseDataList>) nSimpleHibernateDao
				.getHibernateTemplate().find(
						"from BaseDataList where typeuuid=? order by datakey asc", typeuuid);

	}
	
	 /**
		 * 根据key返回显示Datavalue
		 * 
		 * @return
		 */
		public static String getBaseDatavalue(Integer datakey,List<BaseDataList> list ) {
			if(datakey==null)return "";
			for(BaseDataList s:list){
				if(datakey.equals(s.getDatakey())){
					return s.getDatavalue();
				}
			}
			return "";
		}
	
	
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
