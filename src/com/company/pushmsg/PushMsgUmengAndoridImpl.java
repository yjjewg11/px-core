package com.company.pushmsg;

import push.android.AndroidUnicast;

import com.company.news.ProjectProperties;

/**
 * www.umeng.com
 * 推送
 * @author liumingquan
 *
 */
public class PushMsgUmengAndoridImpl implements PushMsgAndoridInterface {

	
	 /**
	   * 广播所有android消息_给所有家长
	   * @param msg
	   * @return
	   */
	@Override
	  public void androidPushMsgToSingleDevice_to_parentByChannelId(String title,String msg,String channelId)throws Exception{
		  
		String appkey  = ProjectProperties.getProperty("umeng_appkey_parent", "55be14c867e58ed6b800592e");
		String appMasterSecret = ProjectProperties.getProperty("umeng_appMasterSecret_parent", "orsys1izcyjrdttcqnpgqzdazlgawxqu");
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		
		  AndroidUnicast unicast = new AndroidUnicast();
			unicast.setAppMasterSecret(appMasterSecret);
			unicast.setPredefinedKeyValue("appkey", appkey);
			unicast.setPredefinedKeyValue("timestamp", timestamp);
			// TODO Set your device token
			unicast.setPredefinedKeyValue("device_tokens", channelId);
			unicast.setPredefinedKeyValue("ticker", title);
			unicast.setPredefinedKeyValue("title",  title);
			unicast.setPredefinedKeyValue("text",   msg);
			unicast.setPredefinedKeyValue("after_open", "go_app");
			unicast.setPredefinedKeyValue("display_type", "notification");
			// TODO Set 'production_mode' to 'false' if it's a test device. 
			// For how to register a test device, please see the developer doc.
			unicast.setPredefinedKeyValue("production_mode", "true");
			// Set customized fields
//			unicast.setExtraField("test", "helloworld");
			unicast.send();
	  }
	  

	  /**
	   * 广播所有android消息_给老师
	   * @param msg
	   * @return
	   */
	@Override
	  public void androidPushMsgToSingleDevice_to_TeacherByChannelId(String title,String msg,String channelId)throws Exception{
		  
		String appkey  = ProjectProperties.getProperty("umeng_appkey_teacher", "55c1960867e58ec39000267d");
		String appMasterSecret = ProjectProperties.getProperty("umeng_appMasterSecret_teacher", "iergffksfnffg2lx0uer7edf3zdlyv2f");
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		
		  AndroidUnicast unicast = new AndroidUnicast();
			unicast.setAppMasterSecret(appMasterSecret);
			unicast.setPredefinedKeyValue("appkey", appkey);
			unicast.setPredefinedKeyValue("timestamp", timestamp);
			// TODO Set your device token
			unicast.setPredefinedKeyValue("device_tokens", channelId);
			unicast.setPredefinedKeyValue("ticker", title);
			unicast.setPredefinedKeyValue("title",  title);
			unicast.setPredefinedKeyValue("text",   msg);
			unicast.setPredefinedKeyValue("after_open", "go_app");
			unicast.setPredefinedKeyValue("display_type", "notification");
			// TODO Set 'production_mode' to 'false' if it's a test device. 
			// For how to register a test device, please see the developer doc.
			unicast.setPredefinedKeyValue("production_mode", "true");
			// Set customized fields
//			unicast.setExtraField("test", "helloworld");
			unicast.send();
	  }
	

}
