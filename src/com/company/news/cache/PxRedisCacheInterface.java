package com.company.news.cache;

import java.util.List;
import java.util.Map;

public interface PxRedisCacheInterface {
	/**
	 * 获取阅读计数,表px_count.
	 * @param ext_uuid
	 * @return
	 */
	public  Long getAddCountByExt_uuid(String ext_uuid);
	/**
	 * 设置阅读计数到缓存中.
	 * @param ext_uuid
	 * @param count
	 */
	public  void setCountByExt_uuid(String ext_uuid,Long count);
	
	/**
	 * 批量获取计数,不加1
	 * @param ext_uuids
	 * @return
	 */
	public   List<String> getCountByExt_uuids(String[] ext_uuids);
	/**
	 * 批量计数,自动加一
	 * @param ext_uuids
	 * @return
	 */
	public   List<Object> getIncrCountByExt_uuids(String[] ext_uuids);
	/**
	 * 批量设置阅读计数到缓存中.
	 * @param ext_uuid
	 * @param count
	 */
	public  void setCountByExt_uuids( Map<String,String> zMap);
	
	
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
