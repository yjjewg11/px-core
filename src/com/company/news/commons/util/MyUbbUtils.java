package com.company.news.commons.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.company.common.SpringContextHolder;
import com.company.news.ProjectProperties;
import com.company.news.cache.CacheInterface;
import com.company.news.dao.NSimpleHibernateDao;
import com.company.news.entity.BaseDataList;

/**
 * 关于互动和回复的方案,我找了一个,ios和andorid那边评估下是否ok.
互动和回复的三要数:图片,文字,表情.
显示要求:图片单独列表显示. 文字和表情混显示.
返回数据按照ubb方式处理.用特殊表情:[表情key].服务端会返回一个表情库.表情库格式:表情key,表情的图片url地址.
举例说明:返回的数据: 好心情.[高兴][大笑].
转换到显示区域为好心情.
下面2个关于ios和andorid:
IOS 使用CoreText实现表情文本URL等混合显示控件 
http://www.cnblogs.com/CCSSPP/p/3339984.html 

http://blog.csdn.net/jie1991liu/article/details/46894969 
*/
@Component
public class MyUbbUtils implements  CacheInterface {
	static public String MyUbb_Prefix="[";
	static public String MyUbb_Suffix="]";
	
	{
		initMap();
	}
  
    public static void initMap(){
    	NSimpleHibernateDao nSimpleHibernateDao=SpringContextHolder.getBean("NSimpleHibernateDao");
    	String hql="from BaseDataList where typeuuid='emot'";
    	List<BaseDataList>  list=(List<BaseDataList>) nSimpleHibernateDao.getHibernateTemplate().find(hql);
    	map.clear();
    	for(BaseDataList o:list){
    		String url=ProjectProperties.getProperty("share_url_getEmot", "http://kd.wenjienet.com/px-rest/i/emoji/");
    		map.put(o.getDatavalue(), url+o.getDescription());
    	}
    	
    	
    }
    private static Map<String, Object> map=new ConcurrentHashMap();
    
    public static void main(String[] s){
    	map.put("大笑", "/px-rest/w/xheditor/xheditor_emot/default/laugh.gif");
    	String html=" <div>文字</div>1<p>文字</p>2<img alt=\"大笑\" src=\"/px-rest/w/xheditor/xheditor_emot/default/laugh.gif\"></img>456<img alt=\"大笑\" src=\"/px-rest/w/xheditor/xheditor_emot/default/laugh.gif\"/>123<img alt=\"大笑\" src=\"/px-rest/w/xheditor/xheditor_emot/default/laugh.gif\"></img>";
    	System.out.println(html);
    	String my=MyUbbUtils.htmlToMyUbb(html);
    	System.out.println(my);
    	
    	System.out.println(text_to_Html(my));
    	
    	System.out.println(MyUbbUtils.myUbbTohtml(my));
    }
 
	/**
	 * 去除html标记（类似DOM的element.innerText）
	 * @param htmlcontent
	 * @return content
	 */
	private static String getTextByRemoveHTMLTag(String htmlcontent) {
		if (StringUtils.isEmpty(htmlcontent)) {
			return "";
		}
		//不能替换导致多个空格被改成一个空格.
		Source source = new Source(htmlcontent);
		Renderer renderer=source.getRenderer();
		renderer.setMaxLineLength(99999);//设置一行最长个数，默认76字符
		String txt=renderer.toString();
		return txt;
	}
    /**
     * html =>myubb
     * 
     * <p>文字</p>	<img alt="大笑" src="/px-rest/w/xheditor/xheditor_emot/default/laugh.gif""/>
	=>
		文字[大笑]
     */
	public static String htmlToMyUbb(String html){
		if(StringUtils.isBlank(html))return html;
		 Pattern pattern1 = null; 
	      try { 
	        pattern1 = Pattern.compile("(<\\s*img\\s*([^>]*)\\s*/>)|(<img\\b[^<]*(?:(?!<\\/img>)<[^<]*)*<\\/img>)"); 
	     //   pattern1 = Pattern.compile("<img\\b[^<]*(?:(?!<\\/img>)<[^<]*)*<\\/img>"); 
	        
	        Matcher m = pattern1.matcher(html);

	        StringBuffer sb = new StringBuffer(); 
	        boolean result = m.find(); 
	        //使用循环将句子里所有的kelvin找出并替换再将内容加到sb里 
	        int i=0;//避免解析文稿内容出错导致死循环.
	        while(result&&i<99999) { 
	            i++; 
//	            System.out.println("匹配结果:"+m.group(0));
	            String d=img_to_myubb(m.group(0));
	            //解决特殊字符$bug。  m.appendReplacement(sb, d); 
	            d=d.replaceAll("\\$", "\\\\\\$");
	            m.appendReplacement(sb, d); 
	            //m.appendReplacement(sb, "Kevin"); 
//	            System.out.println("第"+i+"次匹配后sb的内容是："+sb); 
	            //继续查找下一个匹配对象 
	            result = m.find(); 
	        } 
	        //最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里； 
	        m.appendTail(sb); 
//	        System.out.println("调用m.appendTail(sb)后sb的最终内容是:"+ sb.toString()); 
	        String htmlNoImgStr=sb.toString();
	        return getTextByRemoveHTMLTag(htmlNoImgStr);
	    } catch (Exception pse) { 
	        pse.printStackTrace(); 
	    } 
	      return "";
	    }
	
	
	/**
	 * 
		<img alt="大笑" src="/px-rest/w/xheditor/xheditor_emot/default/laugh.gif""/>
	=>
		[大笑]
		将html 的img对象转成[大笑]
	 * 将img字符串转成myubb格式.
	 * @param img
	 * @return
	 */
	private static  String img_to_myubb(String imgstr){
		if (StringUtils.isEmpty(imgstr)) {
			return "";
		}
		for( String s:map.keySet()){
			if(imgstr.contains("\""+s+"\""))return MyUbbUtils.MyUbb_Prefix+s+MyUbbUtils.MyUbb_Suffix;
		}
		
		return "";
	}
	
	
	 /**
     * myubb=>html 
     * 文字[大笑]
     * =>
     * <p>文字</p>	<img alt="大笑" src="/px-rest/w/xheditor/xheditor_emot/default/laugh.gif""/>
     */
	public static String myUbbTohtml(String txt){
		 Pattern pattern1 = null; 
	      try { 
	        pattern1 = Pattern.compile("\\[[^\\[]*\\]"); 
	     //   pattern1 = Pattern.compile("<img\\b[^<]*(?:(?!<\\/img>)<[^<]*)*<\\/img>"); 
	        
	        
	        String html=text_to_Html(txt);
	        Matcher m = pattern1.matcher(html);

	        StringBuffer sb = new StringBuffer(); 
	        boolean result = m.find(); 
	        //使用循环将句子里所有的kelvin找出并替换再将内容加到sb里 
	        int i=0;//避免解析文稿内容出错导致死循环.
	        while(result&&i<99999) { 
	            i++; 
//	            System.out.println("匹配结果:"+m.group(0));
	            String d=myubb_to_img(m.group(0));
	            //解决特殊字符$bug。  m.appendReplacement(sb, d); 
	            d=d.replaceAll("\\$", "\\\\\\$");
	            m.appendReplacement(sb, d); 
	            //m.appendReplacement(sb, "Kevin"); 
//	            System.out.println("第"+i+"次匹配后sb的内容是："+sb); 
	            //继续查找下一个匹配对象 
	            result = m.find(); 
	        } 
	        //最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里； 
	        m.appendTail(sb); 
//	        System.out.println("调用m.appendTail(sb)后sb的最终内容是:"+ sb.toString()); 
	        return sb.toString();
	    } catch (Exception pse) { 
	        pse.printStackTrace(); 
	    } 
	      return "";
	    }
	
	
	/**
	 * [大笑]
	 * =>
		<img alt="大笑" src="/px-rest/w/xheditor/xheditor_emot/default/laugh.gif""/>
	 * 将myubb字符串img转成格式.
	 * @param img
	 * @return
	 */
	public static  String myubb_to_img(String myubb){
		if (StringUtils.isEmpty(myubb)) {
			return "";
		}
		String tmp_myubb=myubb;
		 tmp_myubb=PxStringUtil.StringDecComma(tmp_myubb, MyUbbUtils.MyUbb_Prefix);
		tmp_myubb=PxStringUtil.StringDecComma(tmp_myubb, MyUbbUtils.MyUbb_Suffix);
		for( String s:map.keySet()){
			if(tmp_myubb.equals(s)){
				String img="<img style=\"display: inline;\" alt=\""+s+"\" src=\""+map.get(s)+"\" />";
				return img;
			}
		}
		//没匹配,则直接返回
		return myubb;
	}

	   /**
		 * 一般文本转换成html格式
		 * @param content
		 * @return
		 */
		public static String text_to_Html(String content) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<div>");
			String temp3 = content;
			if(StringUtils.isNotEmpty(temp3)){
			    //不能用该方法,会导致中文被转义,打印有
			   // temp3=StringEscapeUtils.escapeHtml(temp3);
//			    temp3=DocControlHelper.cleanHtmlCorpsTag(temp3);
			    temp3 = temp3.replaceAll("&", "&amp;");
	            temp3 = temp3.replaceAll("<", "&lt;");
	            temp3 = temp3.replaceAll(">", "&gt;");
	            /*
	            temp3 = temp3.replaceAll(" ", "&nbsp;");
	            temp3 = temp3.replaceAll("&nbsp;(?=\\w)"," ");
	            */
	            //&nbsp; 导致不能自动换行.一个空格情况下显示没的问题,多个的情况下2个以上开始替换.
	            temp3 = temp3.replaceAll("  ", " &nbsp;");
	           
	            temp3 = temp3.replaceAll("\r\n", "<br/>");
	            temp3 = temp3.replaceAll("\n", "<br/>");
	            temp3 = temp3.replaceAll("\t", "&nbsp;");
			}else{
				temp3 = "";
			}
			buffer.append(temp3);
			buffer.append("</div>");
			
			
			return buffer.toString();
		}

	public void clearCache() {
		initMap();
		
	}
}
