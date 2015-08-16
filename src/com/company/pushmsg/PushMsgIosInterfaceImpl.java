package com.company.pushmsg;

import java.util.List;

import com.company.news.ProjectProperties;
import com.company.news.commons.util.IOSPushUtils;

public class PushMsgIosInterfaceImpl implements PushMsgIosInterface {

	@Override
	public void iosPushMsgToSingleDevice_to_parentByChannelId(String title,
			String msg, List deviceTokenList) throws Exception {
		 String p12FileName = ProjectProperties.getProperty("iosCert_parent", "wenjie_jiazhangtong_push_aps_dev.p12");
		  String p12Pass = ProjectProperties.getProperty("iosCert_pwd_parent", "wenjie_123456");
		 IOSPushUtils.pushIosMsgByToken(p12FileName, p12Pass, msg, deviceTokenList);

	}

	@Override
	public void iosPushMsgToSingleDevice_to_TeacherByChannelId(String title,
			String msg, List deviceTokenList) throws Exception {
		// TODO Auto-generated method stub

	}

}
