package com.company.news.commons.util;

import java.util.Random;

public class RandomNumberGenerator {
	
	  //andorid 遗留bug,输入开始字符是0时,不能在输入后面数据bug..临时方案屏蔽.随机数0.
//	static String str = "0123456789";
	static String str = "123456789";
	
  /** * 获取count个随机数 * @param count 随机数个数 * @return */
  public static String getRandomInt(int count) {
    StringBuffer sb = new StringBuffer();
  
//    String str = "0123456789";
    
    Random r = new Random();
    for (int i = 0; i < count; i++) {
      int num = r.nextInt(str.length());
      sb.append(str.charAt(num));
      str = str.replace((str.charAt(num) + ""), "");
    }
    return sb.toString();
  }
}
