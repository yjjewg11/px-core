package com.company.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtil {
	
	/**
	 * 
	 * @param o
	 * @return
	 * @throws IOException
	 */
	public static String ObjectToString(Object o) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);  
        objectOutputStream.writeObject(o);    
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");  
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");  
          
        objectOutputStream.close();  
        byteArrayOutputStream.close(); 
        
        return serStr;
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static Object StringToObject(String s) throws IOException, ClassNotFoundException{
		String redStr = java.net.URLDecoder.decode(s, "UTF-8");  
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));  
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);   
        Object o = objectInputStream.readObject();   
        objectInputStream.close();  
        byteArrayInputStream.close();  
        
        return o;
	}
	
	

}
