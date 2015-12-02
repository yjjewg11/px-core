package com.company.common;

import org.apache.commons.lang.StringUtils;

/**
 * MSB2.0
 * Copyright (c) 2007 www.company.com. All rights reserved.
 * @author CL
 * String操作的实用类
 * */
@Deprecated
public class PxStringUtils {
    public PxStringUtils() {
    }

    // Unicode字符->GBK字符
    public static String unicodeToChinese(String str) throws Exception {
            if (str != null) {
                    return new String(str.getBytes("ISO-8859-1"), "GBK");
            } else {
                    return null;
            }
      }

    // 判断字符串是否为NULL或为空字符号串
    public static boolean isNullOrEmpty(String stringToTest) {
          return (stringToTest == null || stringToTest.trim().equals(""));
    }

    /*
     * 根据KEY得到相应的VALUE
     * @param String string
     *        需要分割的字符串
     * @param String separator
     *        分割符
     */
    public static String[] split(String string,String separator) {
        if (isNullOrEmpty(string)) {
            return null;
        }
        if(isNullOrEmpty(separator)){
            return  new String[]{string};
        }
        return string.split(separator);
    }
    
	/**
	 * desc: StringAddComma 在给定字符串前后加sep，默认','
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String StringAddComma(String str,String sep){
		if(PxStringUtils.isNullOrEmpty(sep)) sep=",";
		if(PxStringUtils.isNullOrEmpty(str)){
			return str;
		}	
		StringBuffer sb=new StringBuffer();
		if(!str.startsWith(sep)){
			sb.append(sep);
		}
		sb.append(str);
		if(!str.endsWith(sep)){
			sb.append(sep);
		}		
		return sb.toString();
	}
	/**
	 * desc: StringAddComma 在给定字符串前后加sep，默认','
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String StringAddComma(String str){
		return StringAddComma(str,null);
	}
	/**
	 * desc: StringDecComma 去掉给定字符串前后的sep，默认','
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String StringDecComma(String str,String sep){
		if(PxStringUtils.isNullOrEmpty(str)){
			return str;
		}
		if(str.length()<1){
			return str;
		}
		if(PxStringUtils.isNullOrEmpty(sep)) sep=",";
		if(str.startsWith(sep)){
			str = StringUtils.substring(str, 1);//new String(str.substring(1));
		}
		if(str.endsWith(sep)){
			str = StringUtils.substring(str, 0, str.length()-1);//new String(str.substring(0,str.length()-1));
		}		
		return str;
	}
	/**
	 * desc: StringDecComma 去掉给定字符串前后的sep，默认','
	 * @param str
	 * @return
	 * date&author: 2009-3-25 
	 */
	public static String StringDecComma(String str){
		return StringDecComma(str,null);
	}
}
