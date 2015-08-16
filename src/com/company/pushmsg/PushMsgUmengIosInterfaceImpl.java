package com.company.pushmsg;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import push.ios.IOSUnicast;

import com.company.news.ProjectProperties;
import com.company.news.commons.util.IOSPushUtils;
import com.company.news.dao.NSimpleHibernateDao;

public class PushMsgUmengIosInterfaceImpl implements PushMsgIosInterface {
	 private static Logger logger = LoggerFactory.getLogger(PushMsgUmengIosInterfaceImpl.class);
	  
	@Override
	public void iosPushMsgToSingleDevice_to_parentByChannelId(String title,
			String msg, List deviceTokenList) throws Exception {

		String appkey  = ProjectProperties.getProperty("ios_umeng_appkey_parent", "55be15a4e0f55a624c007b24");
		String appMasterSecret = ProjectProperties.getProperty("ios_umeng_appMasterSecret_parent", "qt1p4egb8vgptvd7l4ojqqutqlsvfsep");
		String production_mode = ProjectProperties.getProperty("ios_umeng_production_mode_parent", "false");
		
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		
//		System.out.println("ios_umeng_appkey_parent="+appkey);
		IOSUnicast unicast = new IOSUnicast();
		
		if(deviceTokenList==null||deviceTokenList.size()<0){
			logger.error("PushMsgUmengIosInterfaceImpl :deviceTokenList==0");
			return;
		}else if(deviceTokenList.size()>1){
			unicast.setPredefinedKeyValue("type", "listcast");
		}
		List list=null;
		//所谓的“非结构性修改”，是指不涉及到list的大小改变的修改。相反，结构性修改，指改变了list大小的修改。
		if(deviceTokenList.size()>499){
			list=deviceTokenList.subList(0, 499);
			//递归调用发送完毕.
			iosPushMsgToSingleDevice_to_parentByChannelId(title,msg,deviceTokenList.subList(500, deviceTokenList.size()-1));
			
		}else{
			list=deviceTokenList;
		}
		String device_tokens=StringUtils.join(list, ",");
		unicast.setAppMasterSecret(appMasterSecret);
		unicast.setPredefinedKeyValue("appkey", appkey);
		unicast.setPredefinedKeyValue("timestamp", timestamp);
		// TODO Set your device token
		unicast.setPredefinedKeyValue("device_tokens", device_tokens);
		unicast.setPredefinedKeyValue("alert", title+":"+msg);
		unicast.setPredefinedKeyValue("badge", 0);
		unicast.setPredefinedKeyValue("sound", "chime");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		unicast.setPredefinedKeyValue("production_mode", production_mode);
		// Set customized fields
//		unicast.setCustomizedField("test", "helloworld");
		unicast.send();
		logger.info("Umeng IOS send to parent ok! list.size="+list.size()+".title="+title+":"+msg);
	}

	@Override
	public void iosPushMsgToSingleDevice_to_TeacherByChannelId(String title,
			String msg, List deviceTokenList) throws Exception {
		// TODO Auto-generated method stub

	}

}
