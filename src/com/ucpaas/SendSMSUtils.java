package com.ucpaas;

import org.apache.log4j.Logger;

import com.company.news.rest.RestConstants;
import com.company.news.vo.ResponseMessage;
import com.ucpaas.restDemo.SysConfig;
import com.ucpaas.restDemo.client.JsonReqClient;

public class SendSMSUtils {
	
	protected static Logger logger = Logger.getLogger(SendSMSUtils.class);
	static  String  accountSid = SysConfig.getInstance().getProperty("accountSid");
	static String token = SysConfig.getInstance().getProperty("token");
	static String appId = SysConfig.getInstance().getProperty("appId");
	static String sendVerificationCode_templateId = SysConfig.getInstance().getProperty("templateId");
	
	static public boolean sendSMS(String tel,String templateId,String parm,ResponseMessage responseMessage){
		// 亲，你的短信验证码为：{1}，请于{2}分钟内正确输入验证码
				try {
					String result = new JsonReqClient().templateSMS(accountSid, token,
							appId, templateId,tel, parm);

					/**
					 * result返回2中情况：
					 * 
					 * 失败：{"resp":{"respCode":"105110"}}"
					 * 
					 * 
					 * { "resp" : { "respCode" : "000000", "failure" : 1, "templateSMS"
					 * : { "createDate" : 20140623185016, "smsId" :
					 * "f96f79240e372587e9284cd580d8f953" } } }
					 */
					logger.info("templateSMS Response content is: " + result);
					if (result == null || result.indexOf("\"000000\"") < 0) {
						// if(!"000000".equals(result)){
						responseMessage
								.setStatus(RestConstants.Return_ResponseMessage_failed);
						responseMessage.setMessage("短信发送失败！code：" + result);
						return false;

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				return true;
	}
	/**
	 * 发送注册通知
	获得问界互动家园授权,帐号手机号码,初始密码:{1}
	 * @param tel
	 * @param templateId
	 * @param parm
	 * @param responseMessage
	 * @return
	 */
	static public boolean sendRegistrationNotification(String tel,String parm,ResponseMessage responseMessage){
		
		return sendSMS( tel, "25128", parm, responseMessage);
		
	}
	/**
	 * 发送验证码

	 * @param tel
	 * @param templateId
	 * @param parm
	 * @param responseMessage
	 * @return
	 */
	static public boolean sendVerificationCode(String tel,String parm,ResponseMessage responseMessage){
		
		return sendSMS( tel, sendVerificationCode_templateId, parm, responseMessage);
		
	}
	
}
