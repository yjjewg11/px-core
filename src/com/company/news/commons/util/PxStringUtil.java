package com.company.news.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.company.news.ProjectProperties;

public class PxStringUtil {
	/**
	 * desc: StringDecComma 去掉给定字符串前后的sep，默认','
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String StringDecComma(String str,String sep){
		if(StringUtils.isEmpty(str)){
			return str;
		}
		if(str.length()<1){
			return str;
		}
		if(StringUtils.isEmpty(sep)) sep=",";
		if(str.startsWith(sep)){
			str = StringUtils.substring(str, 1);//new String(str.substring(1));
		}
		if(str.endsWith(sep)){
			str =StringUtils.substring(str, 0, str.length()-1);//new String(str.substring(0,str.length()-1));
		}		
		return str;
	}
	/**
	 * desc: StringDecComma 去掉给定字符串前后的sep，默认','
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String StringDecComma(String str){
		return StringDecComma(str,null);
	}
	
	/**
	 * 将图片uuid替换成可以下载的http地址.
	 * @param uuid
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String imgUrlByUuid(String uuid){
		if(uuid==null)return null;
		if(uuid.startsWith("http://")){
			return uuid;
		}
		return ProjectProperties.getProperty("img_down_url_pre", "http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
	}
	/**
	 * 将图片http地址替换成uuid,用于保存.
	 * @param uuid
	 * http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}
	 * =>{uuid}
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String imgUrlToUuid(String url){
		if(url==null)return null;
		if(url.startsWith("http://")){
			String[] st=url.split("uuid=");
			if(st.length>0){
				return st[st.length-1];
			}
		}
		return url;
	}
	
	/**
	 * 将图片uuid替换成可以下载的http地址.
	 * 
	 * @param uuids
	 * @return List
	 *   a,b,c=>http://img/a.jpg
	 * date&author: 2009-3-25 
	 */
	public static List uuids_to_imgurlList(String uuids){
		List list=new ArrayList();
		if(StringUtils.isBlank(uuids))return list;
		
		for(String uuid:uuids.split(",")){
			if(uuid.startsWith("http://")){
				list.add(uuid);
			}else{
				list.add(ProjectProperties.getProperty("img_down_url_pre", "http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid));
			}
		}
		return list;
	}
	/**
	 * 分享公告地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getAnnByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getAnn", "http://localhost:8080/px-rest/rest/share/getAnn.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	
	/**
	 * 分享精品地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getArticleByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getArticle", "http://localhost:8080/px-rest/rest/share/getArticle.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	/**
	 * 分享班级互动
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getClassNewsByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getClassNews", "http://localhost:8080/px-rest/rest/share/getClassNews.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	

	/**
	 * 分享班级互动
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getCookbookPlanByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getCookbookPlan", "http://localhost:8080/px-rest/rest/share/getClassNews.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	
}
