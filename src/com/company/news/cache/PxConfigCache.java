package com.company.news.cache;

import java.util.List;

import org.apache.log4j.Logger;

import com.company.common.SpringContextHolder;
import com.company.news.dao.NSimpleHibernateDao;


public class PxConfigCache{
	private static Logger logger = Logger.getLogger("PxConfigCache");
    private static NSimpleHibernateDao nSimpleHibernateDao=SpringContextHolder.getBean("NSimpleHibernateDao");
    static String  Config_sns_url=null;
	  /**
	   * 获取话题url
	   * @return
	   */
    static  public String getConfig_sns_url(){
			
			try {
				if(Config_sns_url==null){
						List list=nSimpleHibernateDao
								.getHibernateTemplate().find(
										"select description from BaseDataList where typeuuid='KDWebUrl' and datakey=2");
							if(list!=null&&list.size()>0){
								Config_sns_url=list.get(0)+"";
							}else{
								Config_sns_url="http://kd.wenjienet.com/px-rest/sns/index.html?v1=1";
							}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		return Config_sns_url;
	  }
	  

}
