package com.company.common;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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

import com.company.news.commons.util.PxStringUtil;
import com.company.news.rest.util.DBUtil;
 
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
     *获取网页地址的标题.
     * @param url    路径
     * @return
     */
    public static String httpGetHtmlTitle(String url){
    	if(!PxStringUtil.isUrl(url)){
    		return null;
    	}
        //get请求返回结果
        String strResult = null;
        try {
        	CloseableHttpClient  client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
 
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                 strResult = EntityUtils.toString(response.getEntity());
                if(StringUtils.isNotBlank(strResult)) return  getTitle(strResult);
                /**把json字符串转换成json对象**/
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
      return null;
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
      
    public static void main(String[] args) {  
        String htmlUrl = "http://mp.weixin.qq.com/s?__biz=MzA3NTg4MDQzMw==&mid=400325494&idx=7&sn=a9412f129d8f11b3e52522131c50d5ec&scene=2&srcid=1115nofTRhdDvk5O3XSxmhwX&from=timeline&isappinstalled=0#wechat_redirect";  
        String dd= HttpRequestUtils.httpGetHtmlTitle(htmlUrl);  
         System.out.println(dd);
    }  


}