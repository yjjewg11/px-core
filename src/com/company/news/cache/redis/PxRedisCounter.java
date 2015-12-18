package com.company.news.cache.redis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import com.company.common.SpringContextHolder;
import com.company.news.dao.NSimpleHibernateDao;


/**
基于redis 的计数器.
和同步数据部分.
 */
public  class PxRedisCounter extends AbstractRedisCounter {
	private static String keyConstant="px_count";
	private static Logger logger = Logger.getLogger("PxRedisCounter");
	private static  NSimpleHibernateDao nSimpleHibernateDao=SpringContextHolder.getBean("NSimpleHibernateDao");
	
	 public PxRedisCounter(PxRedisCacheImpl pxRedisCacheImpl) {
		 super(keyConstant, pxRedisCacheImpl);
	}

	@Override
	/**
	 * 批量更新计数,提供效率.用于redis 同步到数据库
	 * 
	 * @param entityStr
	 * @param model
	 * @param request
	 * @return
	 */
	public boolean update_synCountRedisToDb(final String[] keys, final List<String> values) throws Exception {
//		if (StringUtils.isBlank(ext_uuids)) {
//			return ;
//		}
		Session session=nSimpleHibernateDao.getSession();
       
        try{
             session.beginTransaction();
             session.doWork(
                     new Work() {
                         public void execute(Connection connection) throws SQLException {
                        	  String sql = "update px_count set count=?,update_time=now() where ext_uuid =?";
                        	 	//经由过程JDBC API执行SQL语句
                        	  logger.info(keys.length+" count,executeBatch");
                        	  // logger.info(keys.length+" count,executeBatch:"+sql);
                             PreparedStatement ps = connection.prepareStatement(sql);

                             for (int i = 0; i <keys.length; i++) {
                            	 String valueStr=values.get(i);
                            	 String key=keys[i];
                            	 logger.debug("key="+key+",val="+valueStr);
                            	 if(valueStr!=null&&key!=null){
                            		 ps.setLong(1, Long.valueOf(valueStr));
                                	 ps.setString(2, key);
                                     ps.addBatch();
                            	 }else{
                            		 logger.error("key="+key+",val="+valueStr);
                            	 }
                            	
                             }

                             int[] dd=ps.executeBatch();
                           
                             //日志记录失败的数据.
                             int error_count=0;
                             for (int i = 0; i <dd.length; i++) {
                            	 if(i==0){
                            		 error_count++;
                            		 logger.error("update px_count count error ,key="+keys[i]+",val="+values.get(i));
                            	 }
                             }
                             if(error_count>0){
                            	 logger.error(sql);
                             }
                             logger.info("update px_count,size="+dd.length+",error_count="+error_count);
                         }
                     }
             );
             session.getTransaction().commit();
             //session.close();
        }catch(Exception ex){
        	  session.getTransaction().rollback();
        }
		
        return true;
	}

}
