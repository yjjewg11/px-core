package com.company.news.commons.util;

import com.company.news.ProjectProperties;
import com.company.news.vo.ResponseMessage;

/**
 * 上传文件工具类
 * @author liumingquan
 *
 */
public class UploadFileUtils {
	static Long  maxfileSize = Long.valueOf(ProjectProperties.getProperty(
			"UploadFilePath_maxSize_M", "2"));
	/**
	 * 判断上传文件是否合法
	 * 
	 * @param responseMessage
	 * @param fileExt
	 * @return
	 */
	public static boolean fileFilter(ResponseMessage responseMessage,
			long fileSize) {
	
		if (fileSize > maxfileSize * 1024 * 1024) {
			responseMessage.setMessage("上载文件太大，不能超过" + maxfileSize + "M");
			return false;
		}

		return true;
	}
    
}
