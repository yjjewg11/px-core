package com.company.news.commons.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.company.news.ProjectProperties;

public class IOSPushUtils {
	private static Logger logger = Logger.getLogger(IOSPushUtils.class);
	
	
	
	public static void pushIosMsgByToken(String p12FileName,String p12Pass,String msg, List<String> deviceTokenList) {
		try {
			// 定义消息模式
			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(msg);
			payLoad.addBadge(1);// 消息推送标记数，小红圈中显示的数字。
			payLoad.addSound("default");
			// 注册deviceToken
			PushNotificationManager pushManager = PushNotificationManager
					.getInstance();
			// 连接APNS
			 String host = "gateway.sandbox.push.apple.com";
//			String host = "gateway.push.apple.com";
			 
			 host=ProjectProperties.getProperty("ios_push_host", "gateway.sandbox.push.apple.com");
			int port = 2195;
			String filePath = IOSPushUtils.class.getClassLoader()
					.getResource("").getFile();
			filePath = filePath.split("WEB-INF")[0];
			try {
				filePath = URLDecoder.decode(filePath, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("", e);
			}
			String iosCert =p12FileName;// ProjectProperties.getProperty("ios.cert", "");
			String certificatePassword =p12Pass;
//			ProjectProperties.getProperty(
//					"ios.cert.password", "");// 此处注意导出的证书密码不能为空因为空密码会报错
			if (StringUtils.isEmpty(iosCert)
					|| StringUtils.isEmpty(certificatePassword)) {
				return;
			}
			String certificatePath = filePath + "WEB-INF/classes/iosCert/"
					+ iosCert;
			logger.info("iosCert : " + iosCert);
			pushManager.initializeConnection(host, port, certificatePath,
					certificatePassword,
					SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			try {
				//开始循环推送 
				for(int i=0;i<deviceTokenList.size();i++){
					pushManager.addDevice("iphone"+i,deviceTokenList.get(i));
					Device client=pushManager.getDevice("iphone"+i);
					pushManager.sendNotification(client,payLoad);
//				logger.info("Push Start deviceToken:" + deviceTokenList.get(i));
					logger.info("推送消息: " + client.getToken() + "\n"
							+ payLoad.toString() + " ");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 停止连接APNS
			pushManager.stopConnection();
			// 删除deviceToken
//			pushManager.removeDevice("iPhone");
			for(int i=0;i<deviceTokenList.size();i++){
				pushManager.removeDevice("iphone"+i);
			}
			logger.info("Push End");
		} catch (Exception ex) {
			logger.error("IOS Push error",ex);
			ex.printStackTrace();
		}
	}

}
