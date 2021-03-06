package com.hikvision;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 测试基础类
 * </p>
 * 
 * @author pengxiongwei 2014年7月8日 上午9:19:54
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2014年7月8日
 * @modify by reason:{方法名}:{原因}
 */
public class BaseTest {
    private static final Log log = LogFactory.getLog(BaseTest.class);

    private static final String TEST_HOST = "https://open.ys7.com:443/api/";

    // private static final String TEST_HOST = "https://pxw.shipin7.com/api/";

    // private static final String TEST_HOST = "https://10.97.4.38:443/api/";

    // private static final String TEST_HOST = "https://test.shipin7.com:65/api/";

    protected static JSONObject sendHttpRequest(List<NameValuePair> pairsList, String method) {
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(TEST_HOST + method);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        NameValuePair[] valuePairs = pairsList.toArray(new NameValuePair[0]);
        postMethod.setRequestBody(valuePairs);
        JSONObject object = null;

        try {
            httpClient.executeMethod(postMethod);
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            String returnReult = IOUtils.toString(inputStream);

            System.out.println(returnReult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return object;
    }


}
