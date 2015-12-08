package com.company.news.cache.redis;

import java.util.List;

/**
 * redis同步数据到数据库接口
 * @author liumingquan
 *
 */
public interface SynPxRedisToDbInterface {
	/**
	 * 批量更新计数,提供效率.用于redis 同步到数据库
	 * 
	 * @param entityStr
	 * @param model
	 * @param request
	 * @return
	 */
	public boolean update_synCountRedisToDb(final String[] keys, final List<String> values) throws Exception;
}
