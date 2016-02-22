package com.company.mq;

import com.github.xetorthio.jedisque.Job;

/**
 * 执行job接口
 * @author liumingquan
 *
 */
public interface DoJob {

	public String doJob(String job);
}
