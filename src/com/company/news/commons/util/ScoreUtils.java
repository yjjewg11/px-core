package com.company.news.commons.util;


/**
 * 评价打分计算
 * @author liumingquan
 *
 */
public class ScoreUtils {
		public static int calculateAverageScore(Integer ct_stars,double ct_stars_count,Integer new_ct_start){
			
			//记录5星评价的总数.ct_stars=(((ct_stars*ct_stars_count)+new_ct_start)/(ct_stars_count+1)+0.5)*10
			
			double x = (ct_stars*ct_stars_count+new_ct_start)/(ct_stars_count+1);
			 System.out.println(ct_stars+"*"+ct_stars_count+"+"+new_ct_start+"/="+x);
			return Double.valueOf(x).intValue();
		}
		
		public static void main(String[] sss){
			int d=ScoreUtils.calculateAverageScore(45, 100L, 49);
			System.out.println(d);
			
		}
		
}
