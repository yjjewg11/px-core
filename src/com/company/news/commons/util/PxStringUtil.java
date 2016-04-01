package com.company.news.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.news.ProjectProperties;
import com.company.news.cache.CommonsCache;
import com.company.news.cache.PxConfigCache;
import com.company.news.entity.AbstractStudentContactRealation;
import com.company.news.interfaces.CreateUserInterface;
import com.company.news.interfaces.SessionUserInfoInterface;

public class PxStringUtil {
	 protected static Logger logger = LoggerFactory.getLogger("PxStringUtil");
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
				String tmp=imgSmallUrlByUuid_sub(url);
				if(StringUtils.isNotBlank(tmp))uuids+=","+tmp;
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
	public static List uuids_to_imgSmallUrlurlList(String uuids){
		List list=new ArrayList();
		if(StringUtils.isBlank(uuids))return list;
		
		for(String uuid:uuids.split(",")){
			String url=imgSmallUrlByUuid_sub(uuid);
				if(StringUtils.isNotBlank(url))list.add(url);
		}
		return list;
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
			String path=CommonsCache.getUploadFileOfFile_path(uuid);
			if(StringUtils.isBlank(path))return "";
			String s= ProjectProperties.getProperty("oss_Small_img_down_url", "http://img.wenjienet.com/{object}@60h").replace("{object}",path );
			s+="?uuid="+uuid;//必须添加用于保存时,转为uuid保存.
			return s;
		}
		
		return ProjectProperties.getProperty("img_down_url_pre", "http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	
	private static String imgSmallUrlByRelativePath_sub(String relativePath){
		if(StringUtils.isBlank(relativePath))return "";
		if(relativePath.startsWith("http://")){
			return relativePath;
		}
		if (uploadfiletype.equals("oss")) {
			String s= ProjectProperties.getProperty("oss_Small_img_down_url", "http://img.wenjienet.com/{object}@60h").replace("{object}",relativePath );
			return s;
		}
		return relativePath;
//		return ProjectProperties.getProperty("img_down_url_pre", "http://kd.wenjienet.com/px-moblie/rest/uploadFile/getImgFile.json?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	/**
	 * 根据相对路径返回地址.照片专用
	 * @param relativePath
	 * @return
	 */
	public static String imgFPPhotoUrlByRelativePath_sub(String relativePath){
		if(StringUtils.isBlank(relativePath))return "";
		if(relativePath.startsWith("http://")){
			return relativePath;
		}
		if (uploadfiletype.equals("oss")) {
			String s= ProjectProperties.getProperty("oss_Small_img_down_url", "http://img.wenjienet.com/{object}@240h").replace("{object}",relativePath );
			return s;
		}
		return relativePath;
	}
	/**
	 * 根据相对路径返回地址.
	 * @param relativePath
	 * @return
	 */
	public static String imgMiddleUrlByRelativePath_sub(String relativePath){
		if(StringUtils.isBlank(relativePath))return "";
		if(relativePath.startsWith("http://")){
			return relativePath;
		}
		if (uploadfiletype.equals("oss")) {
			String s= ProjectProperties.getProperty("oss_Small_img_down_url", "http://img.wenjienet.com/{object}@108h").replace("{object}",relativePath );
			return s;
		}
		return relativePath;
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
	/**
	 * 将图片uuid替换成可以下载的http地址.中等图片
	 * @param uuid
	 * @return
	 * uuid1,uuid2
	 * =>
	 * http://ddd/uuid1,http://ddd/uuid1,
	 * date&author: 2009-3-25 
	 */
	private static String imgMiddleUrlByUuid_sub(String uuid){
		if(uuid==null)return "";
		if(uuid.startsWith("http://")){
			return uuid;
		}
		if (uploadfiletype.equals("oss")) {
			String path=CommonsCache.getUploadFileOfFile_path(uuid);
			if(StringUtils.isBlank(path))return "";
			String s= ProjectProperties.getProperty("oss_middle_img_down_url", "http://img.wenjienet.com/{object}@108h").replace("{object}",path);
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
			String path=CommonsCache.getUploadFileOfFile_path(uuid);
			if(StringUtils.isBlank(path))return "";
			String s= ProjectProperties.getProperty("oss_img_down_url", "http://img.wenjienet.com/{object}@60h").replace("{object}",path );
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
				}else{
					logger.error("imgUrlToUuid="+urls);
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
				String tmp=imgMiddleUrlByUuid_sub(uuid);
				if(StringUtils.isNotBlank(tmp)){
					list.add(tmp);
					
				}
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
				String url=imgUrlByUuid_sub(uuid);
				if(StringUtils.isNotBlank(url))list.add(url);
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
	 * 分享话题地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getSnsTopicByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getSnsTopic", "http://kd.wenjienet.com/px-rest/rest/share/getSnsTopic.html?uuid={uuid}").replace("{uuid}", uuid);
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
	 * 分享精品地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getPxCourseContentByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("content_url_getPxCourse", "http://kd.wenjienet.com/px-rest/rest/share/getPxCourse.html?uuid={uuid}&v=1").replace("{uuid}", uuid);
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
	 * 分享精品地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getGroupShareURLByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_group", "http://kd.wenjienet.com/px-rest/rest/share/getKDInfo.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	
	
	/**
	 * 幼儿园招生地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getGroupRecruitURLByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("recruit_url_group", "http://kd.wenjienet.com/px-rest/rest/share/getRecruitBygroupuuid.html?uuid={uuid}").replace("{uuid}", uuid);
	}
	/**
	 * 分享精品地址
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getGroupContentURLByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("content_url_group", "http://kd.wenjienet.com/px-rest/rest/share/getKDInfo.html?uuid={uuid}").replace("{uuid}", uuid);
	} 
	
	
	/**
	 * 分享动态相册
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String getFPMovieByUuid(String uuid){
		if(uuid==null)return null;
		return ProjectProperties.getProperty("share_url_getFPMovie", "http://www.wenjienet.com/px-cc/FPMovie/index.html?movie_uuid={uuid}").replace("{uuid}", uuid);
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
	  
	  /**
	   * 判断是否是url
	   * @param str
	   * @return
	   */
	  public static boolean isUrl(String str){
		  if (StringUtils.isBlank(str))return false;
		  if(str.startsWith("http://")||str.startsWith("https://")){
				return true;
			}
		  return false;
	  }
	  
	  
	  /**
		 * 获取Map 列表中的对应key 的值,连接成字符串,逗号分割
		 * 获取话题模块打开的url.
		 * date&author: 2009-3-25 
		 */
		public static String getMapVaules(List<Map> list,String key){
			if(list==null||list.isEmpty())return null;
			StringBuffer sb=new StringBuffer();
			for(Map o:list){
				sb.append(o.get(key)).append(',');
			}
			return StringDecComma(sb.toString());
		}
		
		/**
		 * 获取Map 列表中的对应key 的值,连接成字符串,逗号分割
		 * 获取话题模块打开的url.
		 * date&author: 2009-3-25 
		 */
		public static Map<String,Map> listMapToMapMap(List<Map> list,String key){
			Map<String,Map> relMap=new HashMap();
			for(Map o:list){
				String mapkey=o.get(key)+"";
				o.remove(key);
				relMap.put(mapkey, o);
			}
			return relMap;
		}

		/**
		 * 将图片uuid替换成可以下载的http地址.最小图片
		 * @param uuid
		 * @return
		 * uuid1,uuid2
		 * =>
		 * 获取话题模块打开的url.
		 * date&author: 2009-3-25 
		 */
		public static String getSnsTopicWebViewURL(String uuid){
			return PxConfigCache.getConfig_sns_url()+"&topic_uuid="+uuid;
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
