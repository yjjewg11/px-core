package com.company.news.commons.util.upload;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.company.common.PxStringUtils;
import com.company.news.ProjectProperties;

public class OssIUploadFile implements IUploadFile {
	private final static String bucketName=ProjectProperties.getProperty("oss_bucketName", "wenjieimg");
	private final static String ACCESS_ID = ProjectProperties.getProperty("oss_ACCESS_ID", "Qfa0tFGxxK8kSvLu");
	private final static String ACCESS_KEY = ProjectProperties.getProperty("oss_ACCESS_KEY", "KSiSYA9VSmtOjcVFqha5RTf4jK4BaB");
	private final static String endpoint = ProjectProperties.getProperty("oos_endpoint", "");
	private static Logger logger = Logger.getLogger(OssIUploadFile.class);
	private final static OSSClient client;
	
	static{
		 client = new OSSClient(ACCESS_ID, ACCESS_KEY);
		 if(!PxStringUtils.isNullOrEmpty(endpoint))
	     client.setEndpoint("http://oss-cn-shenzhen.aliyuncs.com");
	}
	
	@Override
	public boolean uploadFile(InputStream input,String key,Integer type) {
		logger.info("uploadFile:"+key);
		// TODO Auto-generated method stub
        ObjectMetadata objectMeta = new ObjectMetadata();
        //objectMeta.setContentLength(input.);
        // 可以在metadata中标记文件类型
        objectMeta.setContentType("image/jpeg");		

		client.putObject(bucketName, key, input, objectMeta);

		return true;
       
	}

	@Override
	public void downloadFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFile(String key) {
		// TODO Auto-generated method stub
		logger.info("deleteFile:"+key);
		client.deleteObject(bucketName, key);
	}

	@Override
	public void getPath() {
		// TODO Auto-generated method stub
		
	}

}
