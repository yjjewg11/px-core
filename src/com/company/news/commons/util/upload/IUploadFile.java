package com.company.news.commons.util.upload;

import java.io.InputStream;

public interface IUploadFile {
	
	public boolean uploadFile(InputStream input,String key,Integer type);
	
	public void downloadFile();
	
	public void getPath();
	
	public void deleteFile(String key);
}
