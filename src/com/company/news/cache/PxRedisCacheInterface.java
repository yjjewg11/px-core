package com.company.news.cache;

public interface PxRedisCacheInterface {
	/**
	 * 获取阅读计数,表px_count
	 * @param ext_uuid
	 * @return
	 */
	public  Long getCountByExt_uuid(String ext_uuid);
	/**
	 * 设置阅读计数到缓存中.
	 * @param ext_uuid
	 * @param count
	 */
	public  void setCountByExt_uuid(String ext_uuid,Long count);
	
	/**
	 * 获取上传地址,每次调用缓存延长24小时
	 * @param uuid
	 * @return
	 */
	public  String getUploadFilePath(String uuid);
	/**
	 * 设置上传地址到缓存中.缓存延长24小时
	 * @param uuid
	 * @param path
	 */
	public  void setUploadFilePath(String uuid,String path);
}
