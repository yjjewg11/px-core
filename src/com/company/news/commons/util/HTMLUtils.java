package com.company.news.commons.util;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Source;

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
     * 根据html获取摘要文本和img图片uuid
     * @param htmlcontent
     * @return
     */
	private static Object[] getSummaryAndImgByHTML(String htmlcontent) {
		if (StringUtils.isEmpty(htmlcontent)) {
			return new String[]{null,null};
		}
		//不能替换导致多个空格被改成一个空格.
		Source source = new Source(htmlcontent);
		Renderer renderer=source.getRenderer();
		renderer.setMaxLineLength(99999);//设置一行最长个数，默认76字符
		String text=renderer.toString();
		
		 List<Element> imglist=source.getAllElements(HTMLElementName.IMG);
		 String url=ProjectProperties.getProperty("share_url_getEmot", "http://kd.wenjienet.com/px-rest/i/emoji/");
		 List listimguuids=new ArrayList();
		 int summaryCount=100;
		 int maxCount=3;
		 for(Element img: imglist){
			 String srcV=img.getAttributeValue("src");
			 if(StringUtils.contains(srcV, url)){//过滤表情
				 continue;
			 }
			 String imguuid=PxStringUtil.imgUrlToUuid(srcV);
			if(StringUtils.isNotBlank(imguuid)){
				listimguuids.add(imguuid);
			}
			 if(listimguuids.size()>=3)break;
		 }
		 
		String summary=PxStringUtil.getSubString(text, summaryCount);
		String imguuids=StringUtils.join(listimguuids, ',');
		return new String[]{summary,imguuids};
	}

}
