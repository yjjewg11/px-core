package com.company.news.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhonePaseUtils {
	/**
     * 匹配最可能是电话号码的数据返回.
     * 
     *  * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
   * 联通号码段:130、131、132、136、185、186、145 电信号码段:133、153、180、189
   * 17号段.中国联通开通4G并使用176子号段进行4G放号。自此，三大运营商的4G号码已全部亮相，均属于17号段
   
     */
	public static String getPhoneNumber(String html){
		if(html==null)return html;
		
		//只要数字,替换相像的数据
		html=html.replaceAll("O", "0");
		html=html.replaceAll("o", "0");
		html=html.replaceAll("i", "1");
		html=html.replaceAll("l", "1");
		html=html.replaceAll("B", "8");
		html=html.replaceAll("z", "2");
		html=html.replaceAll("Z", "2");
		
		 Pattern pattern1 = null; 
	      try { 
	    	  
//	    	  String regex = "(1)\\d{10}";
	    	  String regex ="((13)|(14)|(15)|(17)|(18))\\d{9}";
	        pattern1 = Pattern.compile(regex); 
	     //   pattern1 = Pattern.compile("<img\\b[^<]*(?:(?!<\\/img>)<[^<]*)*<\\/img>"); 
	        
	        Matcher m = pattern1.matcher(html);

	        StringBuffer sb = new StringBuffer(); 
	        boolean result = m.find(); 
	        if(result){
	        	  System.out.println("匹配结果:"+m.group(0));
	  	        return m.group(0);
	        }
//	      
//	        //使用循环将句子里所有的kelvin找出并替换再将内容加到sb里 
//	        int i=0;//避免解析文稿内容出错导致死循环.
//	        while(result&&i<99999) { 
//	            i++; 
//	            System.out.println(i+"匹配结果:"+m.group(0));
////	            return m.group(0);
////	            String relult=m.group(0);
//	            //m.appendReplacement(sb, "Kevin"); 
////	            System.out.println("第"+i+"次匹配后sb的内容是："+sb); 
//	            //继续查找下一个匹配对象 
//	            result = m.find(); 
//	        } 
	 
	    } catch (Exception pse) { 
	        pse.printStackTrace(); 
	    } 
	      return "";
	    }
	
	
	
	   public static void main(String[] s){
		   String html="    12345678901     ";
		   String str=null;
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="    iuehegr03idfgh173899389834     ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="    斯蒂芬斯蒂芬1234斯蒂芬5678901斯蒂芬是     ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="    斯蒂芬斯蒂芬14345678901     ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="    15345678901斯蒂芬斯蒂芬     ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="    11623456789013斯蒂芬斯蒂芬     ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="    211723456789013斯蒂芬斯蒂芬     ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="    2111834561231231292837123988 7892771637123117 312313211627831321319823719231231233132    11111111789013斯蒂芬斯蒂芬     ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
		   html="918oOZzilB12344444 ";
		   str =PhonePaseUtils.getPhoneNumber(html);
		   System.out.println("匹配结果:"+str);
	   }
}
