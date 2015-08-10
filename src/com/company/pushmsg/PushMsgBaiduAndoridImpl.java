package com.company.pushmsg;

import net.sf.json.JSONObject;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.company.news.ProjectProperties;

public class PushMsgBaiduAndoridImpl implements PushMsgAndoridInterface {

	 /**
	   * 广播所有android消息_给所有家长
	   * @param msg
	   * @return
	   */
	@Override
	  public void androidPushMsgToSingleDevice_to_parentByChannelId(String title,String msg,String channelId)throws Exception{
		  
		  String apiKey = ProjectProperties.getProperty("baidu_apiKey_parent", "p9DUFwCzoUaKenaB5ovHch0G");
		  String secretKey = ProjectProperties.getProperty("baidu_secretKey_parent", "GUHR0mniN15LvML8OWnm3GzMdXsVEGbD");
		  this.androidPushMsgToSingleDevice(title,msg, channelId, apiKey, secretKey);
	  }
	  

	  /**
	   * 广播所有android消息_给老师
	   * @param msg
	   * @return
	   */
	@Override
	  public void androidPushMsgToSingleDevice_to_TeacherByChannelId(String title,String msg,String channelId)throws Exception{
		  
		  String apiKey = ProjectProperties.getProperty("baidu_apiKey_teacher", "El4au0Glwr7Xt8sPgZFg2UP7");
		  String secretKey = ProjectProperties.getProperty("baidu_secretKey_teacher", "4rtqyA96S6GDNVcgB8D1Cqh0Wm4Vohq8");
		  this.androidPushMsgToSingleDevice(title,msg, channelId, apiKey, secretKey);
	  }
	  /**
	   * 广播所有android消息
	   * @param msg
	   * @return
	   */
	  public void androidPushMsgToSingleDevice(String title,String msg,String channelId,String apiKey,String secretKey)throws Exception{
		// 1. get apiKey and secretKey from developer console
//			String apiKey = "xxxxxxxxxxxxxxxxxxxx";
//			String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
			PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

			// 2. build a BaidupushClient object to access released interfaces
			BaiduPushClient pushClient = new BaiduPushClient(pair,
					BaiduPushConstants.CHANNEL_REST_URL);

			// 3. register a YunLogHandler to get detail interacting information
			// in this request.
			pushClient.setChannelLogHandler(new YunLogHandler() {
				@Override
				public void onHandle(YunLogEvent event) {
					System.out.println(event.getMessage());
				}
			});

			try {
				// 4. specify request arguments

				JSONObject notification = new JSONObject();
				notification.put("title", title);
				notification.put("description",msg);
				PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(channelId)
						.addMsgExpires(new Integer(3600)).addMessageType(1)//1：通知,0:透传消息. 默认为0 注：IOS只有通知.
						.addMessage(notification.toString()) //添加透传消息
						.addDeviceType(3);
				// 5. http request
				PushMsgToSingleDeviceResponse response = pushClient
						.pushMsgToSingleDevice(request);
				// Http请求结果解析打印
				System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
						+ response.getSendTime());
			} catch (PushClientException e) {
				if (BaiduPushConstants.ERROROPTTYPE) {
					throw e;
				} else {
					e.printStackTrace();
				}
			} catch (PushServerException e) {
				if (BaiduPushConstants.ERROROPTTYPE) {
					throw e;
				} else {
					System.out.println(String.format(
							"requestId: %d, errorCode: %d, errorMessage: %s",
							e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
				}
			}
			
			
	  }

}
