package com.hikvision;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.company.news.ProjectProperties;
import com.company.news.service.AbstractService;

import edu.emory.mathcs.backport.java.util.Arrays;

public class PublicControllerTest {
	
	 protected static Logger logger = LoggerFactory.getLogger(PublicControllerTest.class);
	static public String key= ProjectProperties.getProperty(
			"hikvision_key", "44b89d0bea824201ad557c48f73635d9");

static public String secret= ProjectProperties.getProperty(
		"hikvision_secret", "0e26ae75371eda0a2cce87d678c8687c");


    protected Map<String, Object> paramsInit(String method, Map<String, Object> paramsMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        long time = System.currentTimeMillis() / 1000;

        /** 测试用(开发者需换成自己的appkey和secret) */
//        String key = "c279ded87d3f4fdca7658f95fb5f1d9e";
//        // //
//        String secret = "b097e53bb9627e7e32b7a8001c701151";
        StringBuilder paramString = new StringBuilder();
        List<String> paramList = new ArrayList<String>();
        for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
            String key1 = it.next();
            String param = key1 + ":" + paramsMap.get(key1);
            paramList.add(param);
        }
        String[] params = paramList.toArray(new String[paramList.size()]);
        Arrays.sort(params);
        for (String param : params) {
            paramString.append(param).append(",");
        }
        paramString.append("method").append(":").append(method).append(",");
        paramString.append("time").append(":").append(time).append(",");
        paramString.append("secret").append(":").append(secret);
        System.out.println(paramString.toString().trim());

        String sign = null;
        try {
            sign = DigestUtils.md5Hex(paramString.toString().trim().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> systemMap = new HashMap<String, Object>();
        systemMap.put("ver", "1.0");
        systemMap.put("sign", sign);
        systemMap.put("key", key);
        systemMap.put("time", time);

        map.put("system", systemMap);
        map.put("method", method);
        map.put("params", paramsMap);
        map.put("id", "123456");
        return map;
    }

     public String doPost(Map<String, Object> map) {
        String json = JSON.toJSONString(map);
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient client = new HttpClient();
        // 使用POST方法
        PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
        String restult=null;
        try {
            RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
            method.setRequestEntity(entity);
            client.executeMethod(method);

            InputStream inputStream = method.getResponseBodyAsStream();
    
            restult =IOUtils.toString(inputStream, "UTF-8");
//             restult = IOUtils.toString(inputStream);
            
            logger.info("ys7:"+restult);
            return restult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            method.releaseConnection();
        }
        
        return restult;
    }

}
