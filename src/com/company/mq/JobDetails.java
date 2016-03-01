package com.company.mq;

import java.util.Map;

/**
 * job详细内容
 * @author liumingquan
 *
 */
public class JobDetails {
	public JobDetails(String beanName, String methodName,
			Map<String, String> args) {
		super();
		this.beanName = beanName;
		this.methodName = methodName;
		this.args = args;
	}
	private String beanName;
	private String methodName;
	public JobDetails() {
		super();
	}
	private Map<String,String> args;
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Map<String, String> getArgs() {
		return args;
	}
	public void setArgs(Map<String, String> args) {
		this.args = args;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
}
