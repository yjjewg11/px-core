package com.company.news.core.iservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.company.im.taobao.ImTaoBaoConstants;
import com.company.news.ProjectProperties;
import com.company.news.commons.util.PxStringUtil;
import com.company.news.commons.util.UUIDGenerator;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.Group4QBaseInfo;
import com.company.news.interfaces.SessionUserInfoInterface;
import com.company.news.service.UserinfoService;
import com.company.news.vo.ResponseMessage;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.OpenImUser;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimTribeCreateRequest;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimTribeCreateResponse;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;

@Service
public class ImTaobaoIservice {
	
    static String url = "http://gw.api.taobao.com/router/rest";
    
//    正式环境	http://gw.api.taobao.com/router/rest	https://eco.taobao.com/router/rest
//    	沙箱环境	http://gw.api.tbsandbox.com/router/rest	https://gw.api.tbsandbox.com/router/rest

    /**
     这是一个测试App
     */
    static String appkey=ProjectProperties.getProperty(
			"IM_taobao_appkey", "23285145");
    static String secret=ProjectProperties.getProperty(
			"IM_taobao_secret", "123123");
    static TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
    

	  protected static Logger logger = LoggerFactory.getLogger(ImTaobaoIservice.class);
	  @Autowired
	  @Qualifier("NSimpleHibernateDao")
	  protected NSimpleHibernateDao nSimpleHibernateDao;
	

	  /**
	   * 创建学校群
	   * @param uuid
	   * @param group
	   * @return
	   * @throws ApiException
	   */
	  public ResponseMessage   getTribe(String uuid,Group4QBaseInfo group) throws ApiException {
			ResponseMessage responseMessage=null;
		  OpenimTribeCreateRequest req = new OpenimTribeCreateRequest();
		  OpenImUser obj1 = new OpenImUser();
		  req.setUser(obj1);
		  
		  req.setTribeName(group.getBrand_name());
		  req.setNotice(group.getCompany_name());
		  req.setTribeType(0L);
		  
		  OpenimTribeCreateResponse rsp = client.execute(req);
		 
		  if (rsp.getErrorCode() == null) {
			  	return null;
        } else {
        	 responseMessage=new ResponseMessage();
        	responseMessage.setMessage(rsp.getSubCode()+":"+rsp.getErrorCode());
        	
        }
	        return responseMessage; 
		  
	  }
	  
	  
	  /**
	   * 创建学校群
	   * @param uuid
	   * @param group
	   * @return
	   * @throws ApiException
	   */
	  public boolean   createTribe(SessionUserInfoInterface user,Group4QBaseInfo group,ResponseMessage responseMessage) throws ApiException {
		  
		  
		  OpenimTribeCreateRequest req = new OpenimTribeCreateRequest();
		  OpenImUser obj1 = new OpenImUser();
		  obj1.setUid(user.getUuid());
//		  obj1.setAppKey(appkey);
		  req.setUser(obj1);
		  
		  req.setTribeName(group.getBrand_name());
		  req.setNotice(group.getCompany_name());
		  req.setTribeType(0L);
		  
		  OpenimTribeCreateResponse rsp = client.execute(req);
		 
		  if (rsp.getErrorCode() == null) {
			  	return true;
        } else {
        	 responseMessage=new ResponseMessage();
        	responseMessage.setMessage(rsp.getSubCode()+":"+rsp.getErrorCode());
        	
        }
	        return false; 
		  
	  }
	  
	  /**
	   * 添加用户到im
	   * @param uuid
	   * @return
	   * @throws ApiException
	   */
	  public List<Userinfos>    getImUserByUser(SessionUserInfoInterface user,ResponseMessage responseMessage) throws ApiException {
		  OpenimUsersGetRequest req = new OpenimUsersGetRequest();
		  req.setUserids(user.getUuid());
		  
		  OpenimUsersGetResponse rsp = client.execute(req);
		  System.out.println(rsp.getBody());
		  	//有则返回,没有则创建
	        if (rsp.getErrorCode() == null) {
	        	List<Userinfos> userinfos=rsp.getUserinfos();
	          if(userinfos!=null&&userinfos.size()>0)return userinfos;

	        } 
	        
	        //创建成功返回
	        return addOpenImUserByUser(user,responseMessage);
	        
	  }

	  
	  /**
	   * taobao.openim.users.update (批量更新用户信息)
	   * @param user
	   * @param responseMessage
	   * @return
	   * @throws ApiException
	   */
	  public Userinfos   updateImUserByUser(SessionUserInfoInterface user,ResponseMessage responseMessage) throws ApiException {
		  
		  List<Userinfos> list2 = new ArrayList<Userinfos>();
		  Userinfos obj3 = new Userinfos();
		  list2.add(obj3);
		  obj3.setNick(user.getName());
		  obj3.setIconUrl(PxStringUtil.imgUrlByUuid(user.getImg()));
		  obj3.setUserid(user.getUuid());
		  obj3.setPassword(new UUIDGenerator().generate().toString());
		  
		  
		  OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
		  OpenimUsersUpdateResponse rsp = client.execute(req);
		  if (rsp.getErrorCode() == null) {
			  	return obj3;
	      } else {
	      	 responseMessage=new ResponseMessage();
	      	responseMessage.setMessage(rsp.getSubCode()+":"+rsp.getErrorCode());
	      	
	      }
		 return null;
	  }
	  /**
	   * 添加用户到im
	   * @param uuid
	   * @return
	   * @throws ApiException
	   */
	  public List<Userinfos>   addOpenImUserByUser(SessionUserInfoInterface user,ResponseMessage responseMessage) throws ApiException {
		  
		  List<Userinfos> list2 = new ArrayList<Userinfos>();
		  Userinfos obj3 = new Userinfos();
		  list2.add(obj3);
		  obj3.setNick(user.getName());
		  obj3.setIconUrl(PxStringUtil.imgUrlByUuid(user.getImg()));
//		  obj3.setEmail("uid@taobao.com");
//		  obj3.setMobile("18600000000");
//		  obj3.setTaobaoid("tbnick123");
		  obj3.setUserid(user.getUuid());
		  obj3.setPassword(new UUIDGenerator().generate().toString());
//		  obj3.setRemark("demo");
//		  obj3.setExtra("demo");
//		  obj3.setCareer("demo");
//		  obj3.setVip("demo");
//		  obj3.setAddress("demo");
//		  obj3.setName("demo");
//		  obj3.setAge(123L);
//		  obj3.setGender("demo");
//		  obj3.setWechat("demo");
//		  obj3.setQq("demo");
//		  obj3.setWeibo("demo");
		  if( addOpenImUser(list2,responseMessage)){
			  return list2;
		  }
		 return null;
	  }
	
	  	/**
	  	 * taobao.openim.users.add (添加用户)
	  	 * @param listUinfos
	  	 * @param responseMessage
	  	 * @return
	  	 * @throws ApiException
	  	 */
	  private boolean   addOpenImUser(List<Userinfos> listUinfos,ResponseMessage responseMessage) throws ApiException {
	        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
	        req.setUserinfos(listUinfos);
	        OpenimUsersAddResponse rsp = client.execute(req);
//	        System.out.println(rsp.getBody());
	        if (rsp.getErrorCode() == null) {
	            //代表Api正确执行，可以通过UidSucc得到创建成功的Uid
	            System.out.println(rsp.getUidSucc());

	            if (rsp.getUidSucc() != null) {
	            	return true;

	            }

	        } else {
	        	//重复添加.
	        	if(ImTaoBaoConstants.Response_sub_code_duplicate.equals(rsp.getSubCode())){
	        		
	        	}
	        	 responseMessage=new ResponseMessage();
	        	responseMessage.setMessage(rsp.getSubCode()+":"+rsp.getErrorCode());
//	            //没有任何用户成功插入，可以打印错误代码，消息等
//	            System.out.println(rsp.getErrorCode());
//	            System.out.println(rsp.getSubCode());
	        	
	        }
	        return false;
	    }


		public NSimpleHibernateDao getnSimpleHibernateDao() {
			return nSimpleHibernateDao;
		}
	
	  
}
