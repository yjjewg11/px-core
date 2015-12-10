package com.company.news.commons.util;

import org.apache.commons.lang.StringUtils;


public class DbUtils {
  public static String stringToDateByDBType(String dateStr) {
    String mysqlStringToDate = "DATE_FORMAT('aaaa','%Y-%m-%d %H:%i:%s')";
//    String oracleStringToDate = "to_date('aaaa','yyyy-mm-dd hh24:mi:ss')";
//    String db2StringToDate = "TIMESTAMP('aaaa')";
//    String currStringToDate = oracleStringToDate;
	dateStr=dateStr.replaceAll("'", "''");
    return mysqlStringToDate.replace("aaaa", dateStr);
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
		dateStr=dateStr.replaceAll("'", "''");
		return StringUtils.replace(currStringToDate, "aaaa", dateStr);
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
}
