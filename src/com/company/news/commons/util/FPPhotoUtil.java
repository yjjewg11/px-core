package com.company.news.commons.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.news.ProjectProperties;
import com.company.news.cache.redis.FPPhotoRedisCache;

public class FPPhotoUtil {
	 protected static Logger logger = LoggerFactory.getLogger("PhotoUtil");
	public static final String uploadfiletype = ProjectProperties.getProperty(
			"uploadfiletype", "oss");
	//html5 响应式布局模版,替换key定义
	public static String Html5_Responsive_body_context="{body_context}";
	//html5 响应式布局模版
	public static String Html5_Responsive="<!doctype html>" +
			"<html class=\"no-js\" ><head>"+
			"<meta charset=\"utf-8\">"+
			"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">"+
			"</head><body>"+
			
			Html5_Responsive_body_context+
			"</body></html>";

	/**
	 * 将图片uuid替换成可以下载的http地址.最小图片
	 * @param uuid
	 * @return
	 * uuid1,uuid2
	 * =>
	 * http://ddd/uuid1,http://ddd/uuid1,
	 * date&author: 2009-3-25 
	 */
	public static String imgUrlByUuid_sub(String uuid){
		if(uuid==null)return "";
		if(uuid.startsWith("http://")){
			return uuid;
		}
		if (uploadfiletype.equals("oss")) {
			String path=FPPhotoRedisCache.getUploadFilePath(uuid);
			if(StringUtils.isBlank(path))return "";
			String s= ProjectProperties.getProperty("oss_photo_midd_img_url", "http://img.wenjienet.com/{object}@108h").replace("{object}",path );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	
	/**
	 * 根据相对路径返回地址.
	 * o.put("path", PxStringUtil.imgUrlByRelativePath_sub((String)o.get("path"),"@320w"));
	 * @param relativePath
	 * scale:@108h,@320w
	 * @return
	 */
	public static String imgUrlByRelativePath_sub(String relativePath,String scale){
		if(StringUtils.isBlank(relativePath))return "";
		if(relativePath.startsWith("http://")){
			return relativePath;
		}
//		if (uploadfiletype.equals("oss")) {
			String s= ProjectProperties.getProperty("oss_original_img_down_url", "http://img.wenjienet.com/{object}").replace("{object}",relativePath )+scale;
			//@108h
			return s;
//		}
//		return relativePath;
	}
}
