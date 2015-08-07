package com.company.news.rest.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.company.news.ProjectProperties;

public class DBUtil {
	public static String dbtype = ProjectProperties.getProperty("primary.dbtype", "mysql");
	private static Log log=LogFactory.getLog(DBUtil.class);
	
	
	/**
	 * 将逗号分割的多个数据组织成 where条件 in 需要的值
	 * uuid1,uuid2=>'uuid1','uuid2'
	 * @return
	 */
	public static String stringsToWhereInValue(String strs){
		String rstr="";
		if(strs==null)return rstr;
		String[] strArr=strs.split(",");
		for(int i=0;i<strArr.length;i++){
			rstr+="'"+strArr[i]+"',";
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
		String oracleStringToDate="to_date('aaaa','yyyy-mm-dd hh24:mi:ss')";
		String db2StringToDate="TIMESTAMP('aaaa')";
		String currStringToDate=oracleStringToDate;
		if ("mysql".equals(dbtype)){
			currStringToDate=mysqlStringToDate;
		}else if("db2".equals(dbtype)){
			currStringToDate=db2StringToDate;
		}
		
		return StringUtils.replace(currStringToDate, "aaaa", dateStr);
	}
    
	
}
