package com.company.news.rest;

public class RestConstants {
  //sessionid 标示
  static public String Return_JSESSIONID="JSESSIONID";
  static public String LOGIN_TYPE="S_type";
  static public String CENSOR_DEVICE="censordevice";
  static public String Return_access_token="access_token";
  static public String Session_UserInfo="S_UserInfo";
  static public String Session_UserInfo_rights="S_User_rights";//数据量大不存缓存
  static public String Session_isAdmin="S_isAdmin";
  static public String Session_MygroupUuids="S_Mygroups";
  static public String Session_StudentslistOfParent="S_Stus";
  static public String Popedom_RESULT_RETURN="isPower";
  static public String Popedom_RESULT_MENULIST="menulist";
  static public String Return_UserInfo="userinfo";
  static public String Return_CAPTCHA="captcha";
  static public String Return_CAPTCHA_NO="captcha_no";
  //请求返回状态
  static public String Return_ResponseMessage="ResMsg";
  //请求返回状态-成功
  static public String Return_ResponseMessage_success="success";
  //总数
  static public String Return_ResponseMessage_count="count";
  //总数
  static public String Return_ResponseMessage_isFavorites="isFavor";
  //请求list
  static public String Return_ResponseMessage_list="list";
  //请求md5
  static public String Return_ResponseMessage_md5="md5";
  //分享地址.
  static public String Return_ResponseMessage_share_url="share_url";
  //请求返回状态-失败
  static public String Return_ResponseMessage_failed="failed";
  //请求返回状态-无权限
  static public String Return_ResponseMessage_nopower="nopower";
  //请求返回状态-session超时.
  static public String Return_ResponseMessage_sessionTimeout="sessionTimeout";
  
  static public String Return_DeleteMessage_successId="successId";
  static public String Return_DeleteMessage_failedMsg="failedMsg";
  static public String Return_ResponseDiffercontent="differcontent";
  //移动审片是否启用附件功能
  static public String Return_attachment="attachment";
  
  //项目代码
  static public String Return_projectcode="projectcode";
//  //请求返回消息
//  static public String Return_G_message="G_message";
  
//查询返回消息集合
  static public String Return_PageQueryResult="pageQueryResult";
  static public String Return_PAGENO="pageNo";
  static public String Return_PAGESIZE="pageSize";
  static public String Return_TotalCount="totalCount";
  
  //请求返回结果中业务对象
  static public String Return_G_entity="data";
  //请求返回图片地址
  static public String Return_G_imgUrl="imgUrl";
  
//请求返回结果中业务对象,产生的主键id
  static public String Return_G_entity_id="data_id";
  
  //默认日期格式
  static public String SimpleDateFormat="yyyy-MM-dd";
  
  //默认时间格式
  static public String SimpleTimestampFormat="yyyy-MM-dd HH:mm:ss";

//编辑业务返回信息
  static public String Return_MenuLIST="UIMenuKeylist";
  static public String Return_ISREADONLY="readOnly";
  
  static public String Return_MD5CODE="md5";
  static public String Return_ConditionShow="conditionShow";
  

}
