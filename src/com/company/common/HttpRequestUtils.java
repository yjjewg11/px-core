package com.company.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import com.company.news.SystemConstants;
import com.company.news.commons.util.PxStringUtil;
import com.company.news.json.JSONUtils;
 
public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);    //日志记录
 
    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }
 
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        //post请求返回结果
    	CloseableHttpClient  httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }
 
 
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
        	CloseableHttpClient  client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
           
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            	
            	
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }
    
    /** 
     * 正则获取字符编码 
     * @param content 
     * @return 
     */  
    private static String getCharSet(	 Header[] headers){  
    	if(headers.length==0)return SystemConstants.Charset_GBK;
    	//"text/html;charset=UTF-8"
    	for(Header o:headers){
    		
    		if("Content-Type".equalsIgnoreCase(o.getName())){
    			logger.info(o.getName()+"<=>"+o.getValue());
    			if(o.getValue()!=null){
    				if(o.getValue().contains("GBK")||o.getValue().contains("gbk")){
        				return SystemConstants.Charset_GBK;
    				}else if(o.getValue().contains("GB2312")||o.getValue().contains("gb2312")){
        				return SystemConstants.Charset_GBK;
    				}
    			}
    		}
    	}
    	return SystemConstants.Charset;
    }  
    

    /**
     *获取网页地址的标题.
     * @param url    路径
     * @return
     */
    public static String httpGetHtmlTitle(String url){
    	url=StringUtils.trim(url);
    	if(!PxStringUtil.isUrl(url)){
    		return null;
    	}
    	
    	
    	 try {
			Source source=new Source(new URL(url));
			 Renderer renderer=source.getFirstElement(HTMLElementName.TITLE).getRenderer();
			 
			 String title=renderer.toString();
			  
		        if(isMessyCode(title)){  
		            try {  
		            	title= new String(title.getBytes("ISO8859-1"), "UTF-8");  
		            } catch (Exception e) {  
		            	e.printStackTrace();
		            }  
		        }  
		        
			 return title;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			logger.error("httpGetHtmlTitle,url="+url);
		}
         return null;
//        //get请求返回结果
//        String strResult = null;
//        try {
//        	CloseableHttpClient  client = HttpClients.createDefault();
//            //发送get请求
//            HttpGet request = new HttpGet(url);
//            CloseableHttpResponse  response = client.execute(request);
// 
//            /**请求发送成功，并得到响应**/
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
////            	response.get
//            	
//            	 Header[] headers=response.getAllHeaders();
//             	
//                 /**读取服务器返回过来的json字符串数据**/
//            	 String charset=getCharSet(headers);
//            	// System.out.println("charset="+charset);
//                  strResult = EntityUtils.toString(response.getEntity(),charset);
////                 
////                /**读取服务器返回过来的json字符串数据**/
////                 strResult = EntityUtils.toString(response.getEntity(),SystemConstants.Charset);
////                 String charset = getCharSet(strResult);  
////                 System.out.println(charset);
////                 if(!SystemConstants.Charset.equalsIgnoreCase(charset)&&StringUtils.isNotBlank(charset)){
////                	
////                	 strResult= new String(strResult.getBytes(SystemConstants.Charset),charset);
////                	 
////                 }
//                if(StringUtils.isNotBlank(strResult)) return  getTitle(strResult);
//                /**把json字符串转换成json对象**/
//            } else {
//                logger.error("get请求提交失败:" + url);
//            }
//        } catch (IOException e) {
//            logger.error("get请求提交失败:" + url, e);
//        }
//      return null;
    }
    
    
    /** 
     * 从html源码(字符串)中去掉标题 
     * @param htmlSource 
     * @return 
     */  
    public static String getTitle(String htmlSource){  
        List<String> list = new ArrayList<String>();  
        String title = "";  
          
        //Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以   
        Pattern pa = Pattern.compile("<title>.*?</title>");//源码中标题正则表达式   
        Matcher ma = pa.matcher(htmlSource);  
        while (ma.find())//寻找符合el的字串   
        {  
            list.add(ma.group());//将符合el的字串加入到list中   
        }  
        for (int i = 0; i < list.size(); i++)  
        {  
            title = title + list.get(i);  
        }  
        title=outTag(title);
        
        
        if(isMessyCode(title)){  
            try {  
            	title= new String(title.getBytes("ISO8859-1"), "UTF-8");  
            } catch (Exception e) {  
            	e.printStackTrace();
            }  
        }  

        
        title=PxStringUtil.getSubString(title, 128);
        
        return title;  
    }  
    
    
    
    /** 
     * 去掉html源码中的标签 
     * @param s 
     * @return 
     */  
    public static String outTag(String s)  
    {  
        return s.replaceAll("<.*?>", "");  
    }  

    /**
     * 
     * @param model
     * @param response
     * @param jsObjName 
     * @throws IOException
     */
    public static void responseJSONP(ModelMap model,HttpServletResponse response,String jsObjName) throws IOException  
    {  
    	  
 	   response.setContentType("text/plain");  
     response.setHeader("Pragma", "No-cache");  
     response.setHeader("Cache-Control", "no-cache");  
     response.setDateHeader("Expires", 0);  
      
     PrintWriter out = response.getWriter();       
     
//     net.sf.json.JSONObject resultJSON = net.sf.json.JSONObject.fromObject(model); //根据需要拼装json  
    // if(StringUtils.isBlank(jsonpCallback))jsonpCallback="jsonpCallback";
     
     out.println("var "+jsObjName+"="+JSONUtils.getJsonString(model));//返回jsonp格式数据  
//     out.println(JSONUtils.getJsonString(model));//返回jsonp格式数据  
     out.flush();  
     out.close();  
    }   
    private static boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }  
      
 
    /**
     * 是否是乱码
     * @param strName
     * @return
     */
    public static boolean isMessyCode(String strName) {  
    	if(strName==null)return false;
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");  
        Matcher m = p.matcher(strName);  
        String after = m.replaceAll("");  
        String temp = after.replaceAll("\\p{P}", "");  
        char[] ch = temp.trim().toCharArray();  
        float chLength = 0 ;  
        float count = 0;  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (!Character.isLetterOrDigit(c)) {  
                if (!isChinese(c)) {  
                    count = count + 1;  
                }  
                chLength++;   
            }  
        }  
        float result = count / chLength ;  
        if (result > 0.4) {  
            return true;  
        } else {  
            return false;  
        }  
    }  

    public static void main(String[] args) throws MalformedURLException, IOException {  
        String htmlUrl = "https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN";  
       
    //    htmlUrl="http://www.duanwenxue.com/article/665726.html#0-tqq-1-57024-89b7933b38b0586d98eb9d131fa70b46";
//        htmlUrl="http://www.wenjienet.com";
        htmlUrl="http://kd.wenjienet.com/px-rest/rest/share/getArticle.html?uuid=11786304-a438-473b-9dab-7e79a28c4e35";
        htmlUrl="http://mp.weixin.qq.com/s?__biz=MjM5MTkxNDM3Mw==&mid=402669189&idx=1&sn=e0711716a1474ba21fafef54eb1cf0df#rd";
//        htmlUrl= "http://www.wenjienet.com/px-cc/FPMovie/index.html?movie_uuid=1";
        String dd= HttpRequestUtils.httpGetHtmlTitle(htmlUrl);  
        System.out.println(dd);
        
        Source source=new Source(new URL(htmlUrl));
        Renderer renderer=source.getFirstElement(HTMLElementName.TITLE).getRenderer();
//        List<Element> elList=source.getAllElements(HTMLElementName.META);
//        for(Element e:elList  ){
//        	String tt=e.getAttributeValue("charset");
//        	 System.out.println("charset="+tt);
//        }
       // Renderer renderer=source.getRenderer();
		renderer.setMaxLineLength(99999);//设置一行最长个数，默认76字符
		 dd=renderer.toString();
//		 dd="sdfdfasdf随风倒 sdfsdf ";
//		 dd= new String(dd.getBytes("GBK"), "UTF-8");  
		 String aaa=null;
		 if(isMessyCode(dd)){  
	            try {  
	            	aaa= new String(dd.getBytes("ISO8859-1"), "UTF-8");  
	            } catch (Exception e) {  
	            	e.printStackTrace();
	            }  
	        }  

//		 String aaa=new String(dd.getBytes("ISO-8859-1"),"UTF-8");
         System.out.println(dd);
         System.out.println(aaa);
         
    }  


}