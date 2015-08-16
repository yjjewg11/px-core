package com.company.pushmsg;

import java.util.List;

public interface PushMsgIosInterface {
	  /**
	   * 广播所有ios消息_给所有家长
	   * @param msg
	   * @return
	   */
	public void iosPushMsgToSingleDevice_to_parentByChannelId(String title,String msg,List deviceTokenList)throws Exception;
	  /**
	   * 广播所有ios消息_给所有家长
	   * @param msg
	   * @return
	   */
	public void iosPushMsgToSingleDevice_to_TeacherByChannelId(String title,String msg,List deviceTokenList)throws Exception;
		  
}
