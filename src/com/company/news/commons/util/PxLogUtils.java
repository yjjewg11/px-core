package com.company.news.commons.util;

import org.apache.log4j.Logger;

/**
 * 输出日志类工具类
 * @author liumingquan
 *
 */
public class PxLogUtils {

	
	/**
	 * 统一输出耗时的操作
	 * @param logger
	 * @param endTime
	 * @param msg
	 * 
	 * 
	 * Long startTime = System.currentTimeMillis();
					watermark_Image=ImageIO.read(new URL(watermark_url));
					Long endTime = System.currentTimeMillis() - startTime;
					PxLogUtils.log(logger, endTime, "ImageIO.read url="+watermark_url);
	 */
	public static void log(Logger logger,long endTime,String msg){
		
		String dd="time count "+ endTime + " time(ms).";
		msg=dd+msg;
		if(endTime>60000){
			logger.error(msg);
		}else if(endTime>2000){
			logger.warn(msg);
		}else{
			logger.info(msg);
		}
	}
}
