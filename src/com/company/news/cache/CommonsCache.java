package com.company.news.cache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.company.common.SpringContextHolder;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.BaseDataList;


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
	

	// 获取自动保存内容
	public static String getUploadFileOfFile_path(String uuid) {
		
		String path=null;
		try {
			path = PxRedisCache.getUploadFilePath(uuid);
			if(path!=null)return path;
			
			List list =nSimpleHibernateDao.getHibernateTemplate().find("select file_path from UploadFile4Q where uuid='"+uuid+"'");
			if (list != null&&list.size()>0){
				path=(String) list.get(0);
				PxRedisCache.setUploadFilePath(uuid, path);
				return  path;
			}
			return  path;
		} catch (Exception e1) {//异常情况下,启用当前服务器缓存.
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String key=uuid+"_UpPath";
		Element e = dbDataCache.get(key);
		if (e != null)
			return  (String)e.getObjectValue();
		else {
			List list =nSimpleHibernateDao.getHibernateTemplate().find("select file_path from UploadFile4Q where uuid='"+uuid+"'");
			if (list != null&&list.size()>0){
				path=(String) list.get(0);
				 e = new Element(key, path);
					logger.info(key);
				dbDataCache.put(e);
				return  (String)e.getObjectValue();
			}
				
			return null;
		}
	}

	// 存入自动保存内容
	public static void put(String uuid, Object user) {
		logger.info(user.getClass().getSimpleName()+":put:uuid-->" + uuid);
		Element e = new Element(uuid+"_"+user.getClass().getSimpleName(), user);
		dbDataCache.put(e);
	}
	
	// 获取自动保存内容.不是数据库db获取
		public static Object getNoDb(String uuid,Class clazz) {
			logger.info(clazz.getSimpleName()+":get:uuid-->" + uuid);
			Element e = dbDataCache.get(uuid+"_"+clazz.getSimpleName());
	 
			if (e != null)
				return  e.getObjectValue();
			
			return null;
		}


}
