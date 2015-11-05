package com.company.news.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.company.news.ProjectProperties;
import com.company.news.cache.CommonsCache;
import com.company.news.entity.AbstractStudentContactRealation;
import com.company.news.entity.UploadFile4Q;
import com.company.news.interfaces.CreateUserInterface;
import com.company.news.interfaces.SessionUserInfoInterface;

public class PxStringUtil {
	
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
		if (uploadfiletype.equals("oss")) {
			UploadFile4Q q=(UploadFile4Q)CommonsCache.get(uuid, UploadFile4Q.class);
			if(q==null)return "";
			String s= ProjectProperties.getProperty("oss_Small_img_down_url", "http://img.wenjienet.com/{object}@60h").replace("{object}",q.getFile_path() );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
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
		if (uploadfiletype.equals("oss")) {
			UploadFile4Q q=(UploadFile4Q)CommonsCache.get(uuid, UploadFile4Q.class);
			if(q==null)return "";
			String s= ProjectProperties.getProperty("oss_middle_img_down_url", "http://img.wenjienet.com/{object}@108h").replace("{object}",q.getFile_path() );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
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
		if(StringUtils.isBlank(uuid))return "";
		
		if(uuid.startsWith("http://")){
			return uuid;
		}
		if (ProjectProperties.getProperty(
				"uploadfiletype", "oss").equals("oss")) {
			UploadFile4Q q=(UploadFile4Q)CommonsCache.get(uuid, UploadFile4Q.class);
			if(q==null)return "";
			String s= ProjectProperties.getProperty("oss_img_down_url", "http://img.wenjienet.com/{object}@60h").replace("{object}",q.getFile_path() );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
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
	 * http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid},http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid},
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
		return ProjectProperties.getProperty("share_url_getAnn", "http://kd.wenjienet.com/px-rest/rest/share/getAnn.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	
	/**
	 * 分享精品地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getArticleByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getArticle", "http://kd.wenjienet.com/px-rest/rest/share/getArticle.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	/**
	 * 分享班级互动
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getClassNewsByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getClassNews", "http://kd.wenjienet.com/px-rest/rest/share/getClassNews.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	/**
	 * 分享精品地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getPxCourseByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getPxCourse", "http://kd.wenjienet.com/px-rest/rest/share/getPxCourse.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	

	/**
	 * 分享班级互动
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getCookbookPlanByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getCookbookPlan", "http://kd.wenjienet.com/px-rest/rest/share/getClassNews.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	/**
	 * 根据孩子关联信息,获取父母称呼
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getParentNameByStudentContactRealation(AbstractStudentContactRealation sr){
		if(sr==null)return "";
		return sr.getStudent_name()+sr.getTypename();
		
	}
	
	
	 /**
	   * 修复手机中复制粘贴导致的特殊号码
	     String s1="180 123 123123";
		  repairCellphone(s1);
		  s1="180-123-123123";
	   * @param cellphone
	   * @return
	   */
	  public static String repairCellphone(String cellphone) {
		  if(StringUtils.isBlank(cellphone))return cellphone;
		 String tmp= cellphone.replaceAll("[\\s|\\-\\(\\)]", "");
		 if(!cellphone.equals(tmp)) {
			 System.out.println("repairCellphone="+cellphone+"==>"+tmp);
		 }
		 return tmp;
	  }
	
	  /**
	   * 获取电话保密格式.
	   * 136111178901-> 136****78901
	   * @param cellphone
	   * @return
	   */
	  public static String getSecretCellphone(String cellphone){
			if (StringUtils.isBlank(cellphone)||cellphone.length()<8) {
				return cellphone;
			}
		// 电话保密
				String  s=StringUtils.substring(cellphone, 0, 3)+ "****" + StringUtils.substring(cellphone, 7);
			return s;
	  }
	  
	  /**
	   * 最大字符截取
	   * 136111178901-> 136...
	   * @param cellphone
	   * @return
	   */
	  public static String getSubString(String str,Integer index){
			if (StringUtils.isBlank(str)||str.length()<=index||index<4) {
				return str;
			}
			str=str.substring(0, index-3)+"...";
			return str;
	  }
	  
	  public static void main(String[] s){
		  String s1="180 123 123123";
		  repairCellphone(s1);
		  s1="180-123-123123";
		  repairCellphone(s1);
		  s1="(180) 123 123123";
		  s1=repairCellphone(s1);
		  s1=repairCellphone(s1);
	  }
}
