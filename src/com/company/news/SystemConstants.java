package com.company.news;



//系统常量�?
public class SystemConstants {
	static public final Integer Group_type_0 = Integer.valueOf(0);//管理后台
	static public final Integer Group_type_1 = Integer.valueOf(1);//幼儿园
	static public final Integer Group_type_2 = Integer.valueOf(2);//培训机构
	
	
	//yongh 
	// 20150610 去掉对用户表的TYPE定义，默认都为0
		//1:妈妈,2:爸爸,3:爷爷,4:奶奶,5:外公,6:外婆,7:其他
		public static final int USER_type_ma = 1;// 组织管理员
		public static final int USER_type_ba = 2;// 老师类型
		public static final int USER_type_ye = 3;// 组织管理员
		public static final int USER_type_nai = 4;// 老师类型
		public static final int USER_type_waigong = 5;// 组织管理员
		public static final int USER_type_waipo = 6;// 老师类型
		public static final int USER_type_other = 7;//其他
		public static final int USER_disable_default = 0;// 电话号码，验证。默认0，0:没验证。1:验证，2：提交验证
		public static final int USER_tel_verify_default = 0;// 是否被管理员封号。0：不封。1：封号，不允许登录。
		// 用户状态
		public static final int USER_disable_true = 1;// 禁用
		
		public static final int USER_isreg_0 = 0;// 用户未注册
		
		public static final int USER_isreg_1 = 1;// 用户已注册
		
    //------------------------上传模块
	static public final Integer UploadFile_type_head = Integer.valueOf(1);//我的头像
	static public final Integer UploadFile_type_cook = Integer.valueOf(2);//菜谱图片
	static public final Integer UploadFile_type_xheditorimg = Integer.valueOf(3);//xheditor编辑器菜单图片上�?

	/**
	 * 		ann_type_0:"全校公告",
		ann_type_1:"老师通知",
		ann_type_2:"班级通知",
		ann_type_3:"招生计划",
		ann_type_4:"文章分享",
	 */
	static public final Integer Ann_type_0 = Integer.valueOf(0);//
	static public final Integer Ann_type_1 = Integer.valueOf(1);//
	static public final Integer Ann_type_2 = Integer.valueOf(2);//
	static public final Integer Ann_type_3 = Integer.valueOf(3);//
	static public final Integer Ann_type_4 = Integer.valueOf(4);//

	static public final Integer Sms_type_1 = Integer.valueOf(1);//注册短信类型.
	static public final Integer Sms_type_2 = Integer.valueOf(2);//找回密码短信类型

	
	//	
//    static public final String UploadFile_type_myhead = "head";//我的头像
//    static public final String UploadFile_type_identity_card ="identity";//身份�?
//    static public final String UploadFile_type_marathon = "marathon";//马拉松认证照�?
//    
    static public final String UploadFile_imgtype = "jpg";//马拉松认证照�?
	 
    //认证。默�?�?:没验证�?1:验证�?：提交验证，3.验证失败
    static public final String User_Verify_NO = "0";//用户验证状�?-未验�?
    static public final String User_Verify_Pass = "1";//用户验证状�?-验证通过
    static public final String User_Verify_Apply = "2";//用户验证状�?-提交验证
    static public final String User_Verify_Fail= "3";//用户验证状�?-验证失败

    
    static public String Charset="UTF-8";
    
}


