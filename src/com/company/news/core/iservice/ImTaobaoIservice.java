package com.company.news.core.iservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.company.news.commons.util.UUIDGenerator;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.Group4QBaseInfo;
import com.company.news.vo.ResponseMessage;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.OpenImUser;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimTribeCreateRequest;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.response.OpenimTribeCreateResponse;
import com.taobao.api.response.OpenimUsersAddResponse;

@Service
public class ImTaobaoIservice {
	
    static String url = "http://gw.api.taobao.com/router/rest";

    /**
     这是一个测试App
     */
    static String appkey="23285145";
    static String secret="df45389c86d51a64f91b6f3e386d0685";
    static TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
    
    
    
	  protected static Logger logger = LoggerFactory.getLogger(ImTaobaoIservice.class);
	  @Autowired
	  @Qualifier("NSimpleHibernateDao")
	  protected NSimpleHibernateDao nSimpleHibernateDao;
	  
	  
	  /**
	   * 创建
	   * @param uuid
	   * @param group
	   * @return
	   * @throws ApiException
	   */
	  public ResponseMessage   createGroup(String uuid,Group4QBaseInfo group) throws ApiException {
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
	  public ResponseMessage   addOpenImUserByUserUuid(String uuid) throws ApiException {
		  
		  List<Userinfos> list2 = new ArrayList<Userinfos>();
		  Userinfos obj3 = new Userinfos();
		  list2.add(obj3);
//		  obj3.setNick("king");
//		  obj3.setIconUrl("http://xxx.com/xxx");
//		  obj3.setEmail("uid@taobao.com");
//		  obj3.setMobile("18600000000");
//		  obj3.setTaobaoid("tbnick123");
		  obj3.setUserid(uuid);
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
		 return  addOpenImUser(list2);
	  }
	
	
	  public ResponseMessage   addOpenImUser(List<Userinfos> listUinfos) throws ApiException {
	        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
//	        List<Userinfos> listUinfos = new ArrayList<Userinfos>();
//	        Userinfos uinfos1 = initUserinfos(userid1);
//	        Userinfos uinfos2 = initUserinfos(userid2);
//	        listUinfos.add(uinfos1);
//	        listUinfos.add(uinfos2);
	        //isv.data-duplicate-error	重复添加	取消调用
	        req.setUserinfos(listUinfos);
	        OpenimUsersAddResponse rsp = client.execute(req);
//	        System.out.println(rsp.getBody());
	    	ResponseMessage responseMessage=null;
	        if (rsp.getErrorCode() == null) {
	            //代表Api正确执行，可以通过UidSucc得到创建成功的Uid
	            System.out.println(rsp.getUidSucc());

	            if (rsp.getUidSucc() != null) {
	            	return responseMessage;
//	                for (int i = 0; i < rsp.getUidSucc().size(); i++) {
//	                    System.out.println("您成功的添加用户:" + rsp.getUidSucc().get(i));
//	                }
	            }

	        } else {
	        	
	        	 responseMessage=new ResponseMessage();
	        	responseMessage.setMessage(rsp.getSubCode()+":"+rsp.getErrorCode());
//	            //没有任何用户成功插入，可以打印错误代码，消息等
//	            System.out.println(rsp.getErrorCode());
//	            System.out.println(rsp.getSubCode());
	        	
	        }
	        return responseMessage;
	    }
	
	  
}
