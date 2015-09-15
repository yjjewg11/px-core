package com.company.news.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.company.news.ProjectProperties;
import com.company.news.cache.CommonsCache;
import com.company.news.entity.StudentContactRealation;
import com.company.news.entity.UploadFile4Q;
import com.company.news.interfaces.CreateUserInterface;
import com.company.news.interfaces.SessionUserInfoInterface;
import com.company.news.service.UploadFileService;

public class PxStringUtil {
	//html5 响应式布局模版,替换key定义
	public static String Html5_Responsive_body_context="{body_context}";
	//html5 响应式布局模版
	public static String Html5_Responsive="<!doctype html>" +
			"<html class=\"no-js\" ><head>"+
			"<meta charset=\"utf-8\">"+
			"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">"+
			Html5_Responsive_body_context+
			"</body></html>";

	/**
	 * 传入格式:<div>内容</div>
	 * @param str
	 * @return
	 */
	public static String warpHtml5Responsive(String str){
		if(str==null)return "";
		return StringUtils.replace(Html5_Responsive, Html5_Responsive_body_context, str);
	}
	
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
	 * desc: 给当前数据设置创建人信息.
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static void addCreateUser(SessionUserInfoInterface user,CreateUserInterface createuser){
		if(user==null||createuser==null)return;
		createuser.setCreate_user(user.getName());
		createuser.setCreate_useruuid(user.getUuid());
		createuser.setCreate_img(user.getImg());
		
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
	public static String imgSmallUrlByUuid(String urls){
		if(urls==null)return null;
		urls=PxStringUtil.StringDecComma(urls);
		String uuids="";
		for(String url:urls.split(",")){
				String tmp=imgSMiddleUrlByUuid_sub(url);
				if(StringUtils.isNotBlank(tmp))uuids+=","+tmp;
		}
		return PxStringUtil.StringDecComma(uuids);
	}
	
	/**
	 * 将图片uuid替换成可以下载的http地址.最小图片
	 * @param uuid
	 * @return
	 * uuid1,uuid2
	 * =>
	 * http://ddd/uuid1,http://ddd/uuid1,
	 * date&author: 2009-3-25 
	 */
	private static String imgSmallUrlByUuid_sub(String uuid){
		if(uuid==null)return "";
		if(uuid.startsWith("http://")){
			return uuid;
		}
		if (UploadFileService.uploadfiletype.equals("oss")) {
			UploadFile4Q q=(UploadFile4Q)CommonsCache.get(uuid, UploadFile4Q.class);
			if(q==null)return "";
			String s= ProjectProperties.getProperty("oss_Small_img_down_url", "http://img.wenjienet.com/{object}@60h").replace("{object}",q.getFile_path() );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
	}
	/**
	 * 将图片uuid替换成可以下载的http地址.中等图片
	 * @param uuid
	 * @return
	 * uuid1,uuid2
	 * =>
	 * http://ddd/uuid1,http://ddd/uuid1,
	 * date&author: 2009-3-25 
	 */
	private static String imgSMiddleUrlByUuid_sub(String uuid){
		if(uuid==null)return "";
		if(uuid.startsWith("http://")){
			return uuid;
		}
		if (UploadFileService.uploadfiletype.equals("oss")) {
			UploadFile4Q q=(UploadFile4Q)CommonsCache.get(uuid, UploadFile4Q.class);
			if(q==null)return "";
			String s= ProjectProperties.getProperty("oss_middle_img_down_url", "http://img.wenjienet.com/{object}@108h").replace("{object}",q.getFile_path() );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	/**
	 * 将图片uuid替换成可以下载的http地址.最大图片
	 * @param uuid
	 * @return
	 * uuid1,uuid2
	 * =>
	 * http://ddd/uuid1,http://ddd/uuid1,
	 * date&author: 2009-3-25 
	 */
	private static String imgUrlByUuid_sub(String uuid){
		if(uuid==null)return "";
		if(uuid.startsWith("http://")){
			return uuid;
		}
		if (UploadFileService.uploadfiletype.equals("oss")) {
			UploadFile4Q q=(UploadFile4Q)CommonsCache.get(uuid, UploadFile4Q.class);
			if(q==null)return "";
			String s= ProjectProperties.getProperty("oss_img_down_url", "http://img.wenjienet.com/{object}@60h").replace("{object}",q.getFile_path() );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
	}
	/**
	 * 将图片uuid替换成可以下载的http地址.
	 * @param uuid
	 * @return
	 * uuid1,uuid2
	 * =>
	 * http://ddd/uuid1,http://ddd/uuid1,
	 * date&author: 2009-3-25 
	 */
	public static String imgUrlByUuid(String urls){
		if(urls==null)return null;
		urls=PxStringUtil.StringDecComma(urls);
		String uuids="";
		for(String url:urls.split(",")){
				String tmp=imgUrlByUuid_sub(url);
				if(StringUtils.isNotBlank(tmp))uuids+=","+tmp;
		}
		return PxStringUtil.StringDecComma(uuids);
	}
	/**
	 * 将图片http地址替换成uuid,用于保存.
	 * @param uuid
	 * http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid},http://localhost:8080/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid},
	 * =>{uuid}
	 * 
	 * or
	 * 	 * uuid1,uuid2
	 * =>uuid1,uuid2
	 * 
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String imgUrlToUuid(String urls){
		if(urls==null)return null;
		urls=PxStringUtil.StringDecComma(urls);
		String uuids="";
		for(String url:urls.split(",")){
			if(url.startsWith("http://")){
				String[] st=url.split("uuid=");
				if(st.length>1){
					uuids+=","+ st[st.length-1];
				}
			}else{
				uuids+=","+url;
			}
		}
		
		return PxStringUtil.StringDecComma(uuids);
	}
	

	/**
	 * 将图片uuid替换成可以下载的http地址.
	 * 
	 * @param uuids
	 * @return List
	 *   a,b,c=>http://img/a.jpg
	 * date&author: 2009-3-25 
	 */
	public static List uuids_to_imgMiddleurlList(String uuids){
		List list=new ArrayList();
		if(StringUtils.isBlank(uuids))return list;
		
		for(String uuid:uuids.split(",")){
				list.add(imgSMiddleUrlByUuid_sub(uuid));
		}
		return list;
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
				list.add(imgUrlByUuid(uuid));
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
	
	/**
	 * 根据孩子关联信息,获取父母称呼
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getParentNameByStudentContactRealation(StudentContactRealation sr){
		if(sr==null)return "";
		return sr.getStudent_name()+sr.getTypename();
		
	}
	
}
