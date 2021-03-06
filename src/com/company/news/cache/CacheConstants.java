package com.company.news.cache;

public class CacheConstants {
	static  public final String Px_count="px_count";
	static  public final String Hash_Count_pre="Hash_Count_";
	static  public final String SortedSet_Count_pre="SortedSet_Count_";
	//话题计数模块
	static  public final String Count_suffix_sns_topic="sns_topic";
	
	//话题计数模块
		static  public final String Count_suffix_FPFamilyCollection="fp_faimly";
	//用户hash,对应表 px_user,px_parent.用户基本信息
	static  public final String Hash_User_BaseInfo="Hash_User_BaseInfo";
	//用户sessionid 关联用户
	static  public final String Hash_Session_SessionId_User="Hash_Session_SessionId-User";
	public static String Redis_String_Path="P_";
		//用户uuid 关联sessionid
	//用于现在一个用户只保存一个sessionid关系.大于一则清除上一个
	static  public final String Hash_Session_Useruuid_SessionId="Hash_Session_Useruuid-SessionId";
	
	
	
	public static String Redis_String_PhotoPath="H_";
	public static int Redis_String_PhotoPath_Expire=60*60*24;
	
	
	
	static  public final String Key_YS7_accessToken="Key_YS7_accessToken";
}
