package com.company.news.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.company.news.entity.Parent;
import com.company.news.entity.User;
import com.company.news.json.JSONUtils;
import com.company.news.query.PageQueryResult;
import com.company.news.query.PaginationData;

@Repository
public class NSimpleHibernateDao extends HibernateDaoSupport {
  @Resource(name="sessionFactory")
  private void setMySessionFactory(SessionFactory sessionFactory){
  //这个方法名可以随便写，@Resource可以通过name 或者type来装载的。
  super.setSessionFactory(sessionFactory);
  }
  
  private static Logger logger = LoggerFactory.getLogger(NSimpleHibernateDao.class);
  
  
  /**
   * @param className
   * @param id
   * @return 2013-6-3 TODO 根据对象ID获取对象，没有代理的方式，方便结果处理
   * @author yl
   */
  public Object getObjectById(Class className, Serializable id) {
    String hql = "from " + className.getName() + " where id = ?";
    List l = this.getHibernateTemplate().find(hql, id);
    if (l != null && l.size() != 0) {
      return (Object) l.get(0);
    }

    return null;
  }
  
  /**
   * 按id获取对象.
   */
  @SuppressWarnings("unchecked")
  public Object getObject(Class clazz, final Serializable id) {
    //代理的方式，拷贝属性方式有问题。
//    return this.getHibernateTemplate().load(clazz, id);
    String hql = "from " + clazz.getName() + " where id = ?";
    List l = this.getHibernateTemplate().find(hql, id);
    if (l != null && l.size() != 0) {
      return (Object) l.get(0);
    }

    return null;
  }
  private Session  getSession() {
    // TODO Auto-generated method stub
    return this.getSessionFactory().openSession();
  }


  /**
   * 分页查询
   * 
   * @param hql
   * @param pData
   * @return
   */
  public PageQueryResult findByPaginationToHql(String hql, PaginationData pData) {
    String listhql = hql;
    if (StringUtils.isNotBlank(pData.getOrderFiled())) {
      if (StringUtils.isBlank(pData.getOrderType())) pData.setOrderType(PaginationData.SORT_DESC);
      listhql += " order by " + pData.getOrderFiled() + " " + pData.getOrderType();
    }
    long startTime = 0;
    long endTime = 0;
    startTime = System.currentTimeMillis();
    List list =
        this.getSession().createQuery(listhql).setFirstResult(pData.getStartIndex()).setMaxResults(
            pData.getPageSize()).list();
    endTime = System.currentTimeMillis() - startTime;
    this.logger.info("findByPaginationToHql list  count time(ms)="+endTime);
   
    long total = 0;
    
    if(pData.getPageNo()==1){//效率优化,只有第一页查询时,返回总数,其他的时候不在查询总数
      if (list.size() < pData.getPageSize()) {// 小于当前页,就不用单独计算总数.
        total = list.size();
      } else {
        startTime = System.currentTimeMillis();
        total =
          Long.valueOf(this.getSession().createQuery("select count(*) " + hql).uniqueResult()
            .toString());
        this.logger.info("findByPaginationToHql total  count time(ms)="+endTime);
      }
    }else{
      total=999999;
    }
    endTime = System.currentTimeMillis() - startTime;
   
    return new PageQueryResult(pData.getPageSize(), pData.getPageNo(), list, total);
  }


  /**
   * 用户或老师资料修改时变更数据.
   * @param uuid
   * @param name
   * @param img
   */
  public void updateUserInfoToBusinessData(String uuid,String name,String img) {
	  int count=0;
	  
	  count= this.getHibernateTemplate().bulkUpdate(
				"update ClassNewsReply set create_user=?,create_img=? where uuid =?",
				name,img, uuid);
	  this.logger.info("update ClassNewsReply count="+count);
	  
	  
  }

	

  /**
   * @param className
   * @param id
   * @return 2013-6-3 TODO 根据对象ID获取对象，没有代理的方式，方便结果处理
   * @author yl
   */
  public Object getObjectById(Class className, long id) {
    String hql = "from " + className.getName() + " where id = ?";
    List l = this.find(hql, id);
    if (l != null && l.size() != 0) {
      return (Object) l.get(0);
    }

    return null;
  }
  
  
  private List find(String hql, Object value) {
    // TODO Auto-generated method stub
    return this.getHibernateTemplate().find(hql, value);
  }
  /**
   * @param className
   * @param id
   * @return 2013-6-3 TODO 根据对象ID获取对象，没有代理的方式，方便结果处理
   * @author yl
   */
  public Object getObjectByAttribute(Class className, String attribute,Object value) {
    String hql = "from " + className.getName() + " where   " + attribute + " = ? ";
    List l = this.find(hql,value);
    if (l != null && l.size() != 0) {
      return (Object) l.get(0);
    }

    return null;
  }
  
  /**
   * 判断家长用户是否注册
   * @param tel
   * @return
   */
  public boolean isRegBy_parentTel(String tel) {
	  if( this.getObjectByAttribute(Parent.class,"loginname", tel)==null)return false;
	  return true;
  }
  
  /**
   * 判断老师用户是否注册
   * @param tel
   * @return
   */
  public boolean isRegBy_UserTel(String tel) {
	  if( this.getObjectByAttribute(User.class,"loginname", tel)==null)return false;
	  return true;
  }

  /**
   * @param entityClass
   * @param id 根据ID逐条删除实体
   */
  public void delete(Object obj) {
    this.getHibernateTemplate().delete(obj);
  }

  /**
   * @param entityClass
   * @param id 根据ID逐条删除实体
   */
  public void deleteObjByAttribute(Class className, String attribute,Object value) {
      String hql = "delete from " + className.getName() + " where "+attribute+" = ?  ";
      this.getHibernateTemplate().bulkUpdate(hql, new Object[]{value});
  }
  
  /**
   * @param entityClass
   * @param id 根据ID逐条删除实体
   */
  public void deleteObjectById(Class className, Object id) {
      String hql = "delete from " + className.getName() + " where id = ? ";
      this.getHibernateTemplate().bulkUpdate(hql, new Object[]{id});
  }
  public void save(Object entity) throws Exception{
    try {
      this.getHibernateTemplate().saveOrUpdate(entity);
    } catch (Exception e) {
      this.logger.error("dao save:"+JSONUtils.getJsonString(entity));
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw e;
    }
    
  }

    
    
}