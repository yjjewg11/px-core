package com.company.news.commons.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DistanceUtil {
	/**
	 * 计算两经纬度点之间的距离（单位：米）
	 * 
	 * @param lng1
	 *            经度
	 * @param lat1
	 *            纬度
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2,
			double lat2) {
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double a = radLat1 - radLat2;
		double b = Math.toRadians(lng1) - Math.toRadians(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 
	 * @param point1
	 *            坐标点1 经纬度用逗号分隔
	 * @param point2
	 *            坐标点2 经纬度用逗号分隔
	 * @return 换算为KM
	 */
	public static String getDistance(String point1, String point2) {
		if(point1==null||point2==null)return null;
		//修复url编码问题."map_point",  "-1.0%2C-1.0"
		try {
			point1=URLDecoder.decode(point1,   "utf-8");
			point2=URLDecoder.decode(point1,   "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		double lng1, lat1, lng2, lat2 = 0;
		try {
			String[] point1s = point1.split(",");
			if (point1s.length == 2)// 验证长度
			{
				lng1 = Double.parseDouble(point1s[0]);
				lat1 = Double.parseDouble(point1s[1]);
			} else
				return null;

			String[] point2s = point2.split(",");
			if (point2s.length == 2) {
				lng2 = Double.parseDouble(point2s[0]);
				lat2 = Double.parseDouble(point2s[1]);
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//格式化为带1位小数的字符
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");
		return df.format(getDistance(lng1, lat1, lng2, lat2) / 1000)+"km";
	}
}
