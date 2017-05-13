package com.company.news.commons.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.company.news.json.JSONUtils;

public class HttpClientUtils {

	
	public class SSLClient extends DefaultHttpClient{  
	    public SSLClient() throws Exception{  
	        super();  
	        SSLContext ctx = SSLContext.getInstance("TLS");  
	        X509TrustManager tm = new X509TrustManager() {  
	                @Override  
	                public void checkClientTrusted(X509Certificate[] chain,  
	                        String authType) throws CertificateException {  
	                }  
	                @Override  
	                public void checkServerTrusted(X509Certificate[] chain,  
	                        String authType) throws CertificateException {  
	                }  
	                @Override  
	                public X509Certificate[] getAcceptedIssuers() {  
	                    return null;  
	                }  
	        };  
	        ctx.init(null, new TrustManager[]{tm}, null);  
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
	        ClientConnectionManager ccm = this.getConnectionManager();  
	        SchemeRegistry sr = ccm.getSchemeRegistry();  
	        sr.register(new Scheme("https", 443, ssf));  
	    }  
	}  
	
	
	public static String get(String url){
		String result=null;
		
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		    HttpGet httpGet = new HttpGet(url);
		    CloseableHttpResponse response1 = null;
		    // The underlying HTTP connection is still held by the response object
		    // to allow the response content to be streamed directly from the network socket.
		    // In order to ensure correct deallocation of system resources
		    // the user MUST either fully consume the response content  or abort request
		    // execution by calling CloseableHttpResponse#close().
		    //建立的http连接，仍旧被response1保持着，允许我们从网络socket中获取返回的数据
		    //为了释放资源，我们必须手动消耗掉response1或者取消连接（使用CloseableHttpResponse类的close方法）

		    try {
		    	response1 = httpclient.execute(httpGet);
		        System.out.println(response1.getStatusLine());
		        HttpEntity entity1 = response1.getEntity();
		      //获取响应实体信息
				if (entity1 != null) {
					result = EntityUtils.toString(entity1, "UTF-8");
				}
				// 确保HTTP响应内容全部被读出或者内容流被关闭
				EntityUtils.consume(entity1);
				   System.out.println(result);
		    }catch (Exception e) {
				// TODO: handle exception
			} finally {
		        if(response1!=null)
					try {
						response1.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    }
		    
		    return result;

	}
	
	public void post(){
		
//		 HttpPost httpPost = new HttpPost("http://targethost/login");
//		    //拼接参数
//		    List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//		    nvps.add(new BasicNameValuePair("username", "vip"));
//		    nvps.add(new BasicNameValuePair("password", "secret"));
//		    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//		    CloseableHttpResponse response2 = httpclient.execute(httpPost);
//
//		    try {
//		        System.out.println(response2.getStatusLine());
//		        HttpEntity entity2 = response2.getEntity();
//		        // do something useful with the response body
//		        // and ensure it is fully consumed
//		        //消耗掉response
//		        EntityUtils.consume(entity2);
//		    } finally {
//		        response2.close();
//		    }

	}
	
	public static  void main(String[] s){
		
		String url="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
		String responseJson=HttpClientUtils.get(url);
		
		/**
		 * //正常返回的JSON数据包
{
      "openid": "OPENID",
      "session_key": "SESSIONKEY"
}
//错误时返回JSON数据包(示例为Code无效)
{
    "errcode": 40029,
    "errmsg": "invalid code"
}
		 */
		Map map=(Map)JSONUtils.jsonToObject(responseJson, Map.class);
		
		
		if(map.get("errmsg")!=null){
			String errmsg=map.get("errmsg")+"";
			System.out.print(errmsg);
		}
		String openid=map.get("openid")+"";
		String session_key=map.get("session_key")+"";
		
		
		
	}
	
}
