package com.company.pushmsg;

public interface PushMsgAndoridInterface {

	 /**
	   * 广播所有android消息_给家长
	   * @param msg
	   * @return
	   */
	  public void androidPushMsgToSingleDevice_to_parentByChannelId(String title,String msg,String channelId)throws Exception;

	  /**
	   * 广播所有android消息_给老师
	   * @param msg
	   * @return
	   */
	  public void androidPushMsgToSingleDevice_to_TeacherByChannelId(String title,String msg,String channelId)throws Exception;
	  
}
