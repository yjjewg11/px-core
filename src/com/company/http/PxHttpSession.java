package com.company.http;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.log4j.Logger;

import com.company.news.cache.SessionCache;
import com.company.web.listener.SessionListener;

/**
 * 自定义session
 * 释放需要自己判断
 * @author liumingquan
 *
 */
public class PxHttpSession implements HttpSession {
	  private static final Logger logger = Logger
	            .getLogger(PxHttpSession.class);
	 private static int count = 0;
	 
	 
	 private long lastAccessedTime=0l;
	 
	Map map=new HashMap();
	private HttpSession session=null;
	private String id;
	
	 public PxHttpSession(String id) {
		super();
		this.id = id;
		lastAccessedTime = System.currentTimeMillis();
//		System.out.println("PxHttpSession "+id);
//		SessionListener.putSessionByJSESSIONID(this);
//		 count++;
//	     logger.info("PxHttpSessionCreated,PxHttpSession count="+count);
	}
	

	public String setId(String id) {
		if(session!=null)return session.getId();
		
		return id;
	}
	
	@Override
	public Object getAttribute(String arg0) {
		if(session!=null){
			return session.getAttribute(arg0);
		}
		return map.get(arg0);
	}

	@Override
	public Enumeration getAttributeNames() {
		if(session!=null){
			return session.getAttributeNames();
		}
		return null;
	}

	@Override
	public long getCreationTime() {
		if(session!=null){
			return session.getCreationTime();
		}
		return 0;
	}

	@Override
	public String getId() {
		if(session!=null)return session.getId();
		return id;
	}

	@Override
	public long getLastAccessedTime() {
		if(session!=null){
			return session.getLastAccessedTime();
		}
		return 0;
	}

	@Override
	public int getMaxInactiveInterval() {
		if(session!=null){
			return session.getMaxInactiveInterval();
		}
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		if(session!=null){
			return session.getServletContext();
		}
		return null;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		if(session!=null){
			return session.getSessionContext();
		}
		return null;
	}

	@Override
	public Object getValue(String arg0) {
		if(session!=null){
			return session.getValue(arg0);
		}else{
			map.get(arg0);
		}
		return null;
	}

	@Override
	public String[] getValueNames() {
		if(session!=null){
			return session.getValueNames();
		}
		return null;
	}

	@Override
	public void invalidate() {
		if(session!=null){
			 session.invalidate();
			 return;
		}
		
		SessionCache.removePxHttpSession(id);
		
	}

	@Override
	public boolean isNew() {
		if(session!=null){
			return session.isNew();
		}
		return false;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		if(session!=null){
			 session.putValue(arg0, arg1);
		}else{
			map.put(arg0, arg1);
		}
	
	}

	@Override
	public void removeAttribute(String arg0) {
		if(session!=null){
			 session.removeAttribute(arg0);
		}else{
			map.remove(arg0);
		}
		
	}

	@Override
	public void removeValue(String arg0) {
		if(session!=null){
			 session.removeAttribute(arg0);
		}
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		if(session!=null){
			 session.setAttribute(arg0, arg1);
		}else{
			map.put(arg0, arg1);
		}
		
	}

	@Override
	public void setMaxInactiveInterval(int arg0) {
		if(session!=null){
			 session.setMaxInactiveInterval(arg0);
		}
		
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}


	public void setLastAccessedTime(long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	private int maxInactiveInterval=10*60;//单位秒.默认10分钟
	/**
	 * 判断是否有效
	 * @return
	 */
	public boolean isValid() {
	    if (maxInactiveInterval > 0) {
	        long timeNow = System.currentTimeMillis();
	        int timeIdle;
	        timeIdle = (int) ((timeNow - lastAccessedTime) / 1000L);
	        
	        if (timeIdle >= maxInactiveInterval) {
	          return false;
	        }
	    }

	    return true;
	}

}
