package com.company.news.rest.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.company.news.ProjectProperties;
import com.company.news.vo.ResponseMessage;
public class DBUtil {
	public static String dbtype = ProjectProperties.getProperty("primary.dbtype", "mysql");
	private static Log log=LogFactory.getLog(DBUtil.class);

	/**
	 *防止sql注入
	 * @return
	 */
	public static boolean isSqlInjection(String strs, ResponseMessage responseMessage) {
		if(StringUtils.isBlank(strs))return false;
		if(strs.indexOf('\'')>=0){
			responseMessage.setMessage("包含非法字符:'");
			return true;
		}
		return false;
	}
	
	/**
	 *防止sql注入
	 * @return
	 */
	public static String safeToWhereString(String strs){
		if(StringUtils.isBlank(strs))return strs;
		strs=strs.replaceAll("'", "''");
		return strs;
		
	}
	/**
	 * 修复为空是,拼接的sql报错bug.
	 * 将逗号分割的多个数据组织成 where条件 in 需要的值
	 * uuid1,uuid2=>'uuid1','uuid2'
	 * @return
	 */
	public static String stringsToWhereInValue(String strs){
		String rstr="";
		if(StringUtils.isBlank(strs))return "''";
		strs=strs.replaceAll("'", "''");
		String[] strArr=strs.split(",");
		for(int i=0;i<strArr.length;i++){
			if(StringUtils.isNotBlank(strArr[i]))rstr+="'"+strArr[i]+"',";
		}
		return StringOperationUtil.trimSeparatorChars(rstr);
		
	}
	
	/**
	 * 时间字符串转成数据库时间字符串
	 *<p><code>stringToDateByDBType</code></p>
	 *Description:
	 * @param dateStr
	 * @return
	 */
	public static String stringToDateByDBType(String dateStr){
		
		String mysqlStringToDate="DATE_FORMAT('aaaa','%Y-%m-%d %H:%i:%s')";
		String currStringToDate=mysqlStringToDate;
//		if ("mysql".equals(dbtype)){
//			currStringToDate=mysqlStringToDate;
//		}else if("db2".equals(dbtype)){
//			currStringToDate=db2StringToDate;
//		}
		
		return StringUtils.replace(currStringToDate, "aaaa", dateStr);
	}
	
	/**
	 * 时间字符串转成数据库时间字符串
	 *<p><code>stringToDateByDBType</code></p>
	 *Description:
	 * @param dateStr
	 * @return
	 */
	public static String stringToDateYMDByDBType(String dateStr){
		
		String mysqlStringToDate="DATE_FORMAT('aaaa','%Y-%m-%d')";
		String currStringToDate=mysqlStringToDate;
//		if ("mysql".equals(dbtype)){
//			currStringToDate=mysqlStringToDate;
//		}else if("db2".equals(dbtype)){
//			currStringToDate=db2StringToDate;
//		}
		
		return StringUtils.replace(currStringToDate, "aaaa", dateStr);
	}
    
	
}
