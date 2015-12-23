package com.company.news.commons.util;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.company.news.ProjectProperties;


@Component
public class HTMLUtils  {
	
    public static void main(String[] s){
    	String html=" <div>文字</div>1<p>文字</p>2<img alt=\"大笑\" src=\"/px-rest/w/xheditor/xheditor_emot/default/laugh.gif\"></img>456<img alt=\"大笑\" src=\"/px-rest/w/xheditor/xheditor_emot/default/laugh.gif\"/>123<img alt=\"大笑\" src=\"/px-rest/w/xheditor/xheditor_emot/default/laugh.gif\"></img>";
    	System.out.println(html);
    	
    }
    /**
     * 根据html获取摘要文本(取100字以内),和img图片uuid(图片取3张)
     * @param htmlcontent
     * @return
     */
    public static String[] getSummaryAndImgByHTML(String htmlcontent) {
		if (StringUtils.isEmpty(htmlcontent)) {
			return new String[]{null,null};
		}
		
		
		 int summaryCount=100;
		 int maxCount=3;
		 
		 

		 

         
		//不能替换导致多个空格被改成一个空格.
		Source source = new Source(htmlcontent);
//		Renderer renderer=source.getRenderer();
		
		//修改解析不彻底bug.<http://bbs.mobby.cn/wechat-login.html> <http://bbs.mobby.cn/wechat-login.html> 有的宝宝不会自己小便，经常尿湿裤子，每次幼儿园的老师都会告状“今天又尿裤子了”，听到这样的话家长也会比较尴尬。遇到这样的情况，家长该怎么办呢？
//
//		renderer.setMaxLineLength(99999);//设置一行最长个数，默认76字符
//		String text=renderer.toString();
		
		
		TextExtractor textExtractor=new TextExtractor(source) ;
		String text=textExtractor.toString();
		
		 List<Element> imglist=source.getAllElements(HTMLElementName.IMG);
		 String url=ProjectProperties.getProperty("share_url_getEmot", "http://kd.wenjienet.com/px-rest/i/emoji/");
		 List listimguuids=new ArrayList();
	
		 for(Element img: imglist){
			 String srcV=img.getAttributeValue("src");
			 if(StringUtils.contains(srcV, url)){//过滤表情
				 continue;
			 }
			 String imguuid=PxStringUtil.imgUrlToUuid(srcV);
			if(StringUtils.isNotBlank(imguuid)){
				listimguuids.add(imguuid);
			}
			 if(listimguuids.size()>=maxCount)break;
		 }
		 
		String summary=PxStringUtil.getSubString(text, summaryCount);
		String imguuids=null;
		if(listimguuids.size()>0)imguuids=StringUtils.join(listimguuids, ',');
		
		return new String[]{summary,imguuids};
	}

}
