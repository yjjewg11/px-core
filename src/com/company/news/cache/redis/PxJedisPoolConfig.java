package com.company.news.cache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 配置基本参数
 * @author liumingquan
 *
 */
public class PxJedisPoolConfig extends GenericObjectPoolConfig {
	/*
jedipool连接池配置推荐的设置（适合v2.5+版本，咨询了用户团队的开发人员）：
// 设置最大连接数，（根据并发请求合理设置）。
config.setMaxTotal(100);
// 设置最大空闲连接数，（根据并发请求合理设置）
config.setMaxIdle(20);
// 多长空闲时间之后回收空闲连接
setMinEvictableIdleTimeMillis(60000);
// 设置最小空闲连接数或者说初始化连接数
config.setMinIdle(10);
// 设置最大等待时间
config.setMaxWaitMillis(500);
// 跟验证有关
config.setTestOnBorrow(true);
// 跟验证有关
config.setTestOnReturn(false);
// 启动空闲连接的测试
config.setTestWhileIdle(false);
	 */
  public PxJedisPoolConfig() {
    // defaults to make your life with connection pool easier :)
	// 设置最大连接数，（根据并发请求合理设置）。
	  PxJedisPoolConfig config=this;
	  config.setMaxTotal(100);
	  // 设置最大空闲连接数，（根据并发请求合理设置）
	  config.setMaxIdle(20);
	  // 多长空闲时间之后回收空闲连接
	  setMinEvictableIdleTimeMillis(60000);
	  // 设置最小空闲连接数或者说初始化连接数
	  config.setMinIdle(10);
	  // 设置最大等待时间
	  config.setMaxWaitMillis(500);
	  // 跟验证有关
	  config.setTestOnBorrow(true);
	  // 跟验证有关
	  config.setTestOnReturn(false);
	  // 启动空闲连接的测试
	  config.setTestWhileIdle(false);
//	  
//    setTestWhileIdle(true);
//    setMinEvictableIdleTimeMillis(60000);
//    setTimeBetweenEvictionRunsMillis(30000);
//    setNumTestsPerEvictionRun(-1);
  }
}
