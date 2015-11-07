package com.company.news;



//系统常量
public class SystemConstants {
	static public final Integer Group_type_0 = Integer.valueOf(0);//管理后台
	static public final Integer Group_type_1 = Integer.valueOf(1);//幼儿园
	static public final Integer Group_type_2 = Integer.valueOf(2);//培训机构
	
	static public final String Sex_male = "0";//男
	static public final String Sex_female = "1";//女
	
	
	
	static public final String Group_uuid_wjd = "group_wjd";//云代理幼儿园,所有注册的老师都默认关联它.
	
	static public final String Group_uuid_wjkj = "wjkj";//管理后台uuid

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
	static public final Integer UploadFile_type_nocut = Integer.valueOf(4);//放在html里的图片,不裁剪只压缩上传

	/**
	 * 		回复，浏览量，点赞，通知通用类型
	 */

	public static final int common_type_gonggao = 0;// 公告
	public static final int common_type_neibutongzhi = 1;// 内部通知（教师可见）
	public static final int common_type_banjitongzhi = 2;// 班级通知
	public static final int common_type_jingpinwenzhang = 3;// 精品文章
	public static final int common_type_zhaoshengjihua = 4;// 招生计划
	public static final int common_type_jingpinkecheng = 5;// 培训机构精品课程
	public static final int common_type_shipu = 6;// 食谱
	public static final int common_type_jiaoxuejihua = 7;//课程表
	public static final int common_type_Kindergarten_introduction = 8;// 幼儿园介绍
	public static final int common_type_html = 10;// 10:html类型,直接去url地址,调用浏览器显示.
	public static final int common_type_messageTeaher = 11;//老师和家长交流信件
	public static final int common_type_messageKD = 12;//老师和园长交流信件
	public static final int common_type_signrecord = 13;//孩子签到记录
	public static final int common_type_pxgroup = 81;//培训机构
	public static final int common_type_pxcourse = 82;//培训课程	
	public static final int common_type_teacher = 83;//教师	
	public static final int common_type_pxteachingPlan = 84;//培训机构-教学计划
	public static final int common_type_pxbenefit  = 85;//优惠活动
	
	public static final int common_type_KDHelp = 91;//幼儿园帮助文档。
	public static final int common_type_PDHelp = 92;//培训机构帮助文档。增加2种文档通知类型。参考老师公告做。
	public static final int common_type_reply = 98;//评论
	public static final int common_type_hudong = 99;// 互动类
	

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
    
    //及时消息模块
    static public final Integer Message_type_0 = Integer.valueOf(0);//推送消息.
	static public final Integer Message_type_1 = Integer.valueOf(1);//给老师写信.
	static public final Integer Message_type_2 = Integer.valueOf(2);//给园长写信.
	static public final String Message_title_xinxiang = "老师来信";//给老师写信.
	static public final String Message_title_xinxiangjiazhang = "家长来信";//
	
	//设备注册的类型
	static public final Integer PushMsgDevice_type_0 = Integer.valueOf(0);//家长
	static public final Integer PushMsgDevice_type_1 = Integer.valueOf(1);//老师
	
	//设备注册的类型
	static public final String PushMsgDevice_device_type_android = "android";//
	static public final String PushMsgDevice_device_type_ios = "ios";//

	//老师通信录类型
	static public final Integer TeacherPhone_type_0 = Integer.valueOf(0);//园长
	static public final Integer TeacherPhone_type_1 = Integer.valueOf(1);//老师	
		
	//type;//0:老师签到,1:家长打卡,2:家长签到.
		static public final Integer StudentBind_type_0 = Integer.valueOf(0);//老师签到
		static public final Integer StudentBind_type_1 = Integer.valueOf(1);//家长打卡
	
	//type;//0:老师签到,1:家长打卡,2:家长签到.
	static public final Integer StudentSignRecord_type_0 = Integer.valueOf(0);//老师签到
	static public final Integer StudentSignRecord_type_1 = Integer.valueOf(1);//家长打卡
	static public final Integer StudentSignRecord_type_2 = Integer.valueOf(2);//家长签到	
	
	//男女类型
	static public final Integer User_sex_male = Integer.valueOf(0);//男
	static public final Integer User_sex_female = Integer.valueOf(1);//女
	
	
	//教师每日任务类型
	static public final Integer TeacherDailyTask_status_0 = Integer.valueOf(0);//完成
	static public final Integer TeacherDailyTask_status_1 = Integer.valueOf(1);//待完成
	
	//审核举报模块
	static public final Integer Check_status_disable = Integer.valueOf(2);//禁止发布
	static public final Integer Check_status_weifabu = Integer.valueOf(1);//未发布
	static public final Integer Check_status_fabu = Integer.valueOf(0);//发布]
	
	//培训机构课程发布
	static public final Integer PxCourse_status_weifabu = Integer.valueOf(1);//1:未发布.0:发布.2:屏蔽
	static public final Integer PxCourse_status_fabu = Integer.valueOf(0);//发布]
	
	//班级模块
	public static final int class_usertype_head = 1;// 班主任(幼儿园),培训机构(管理员)
	public static final int class_usertype_teacher = 0;// 其他老师(幼儿园),培训机构(授课老师)
	

	//结业
	static public final Integer Class_isdisable_0 = Integer.valueOf(0);//未结业
	static public final Integer Class_isdisable_1 = Integer.valueOf(1);//已结业
	
	
	
	//设备注册的类型
		static public final Integer StatMonthAttendance_type_0 = Integer.valueOf(0);//家长
		static public final Integer StatMonthAttendance_type_1 = Integer.valueOf(1);//老师
		
	//学生状态
		static public final Integer Student_status_0 = Integer.valueOf(0);//在校
		static public final Integer Student_status_1 = Integer.valueOf(1);//结业
		static public final Integer Student_status_2 = Integer.valueOf(2);//离校   中途离校
		
		
		
		static public final String GroupHabits_key_WorkTime = "WorkTime";//上班时间.格式: 08:00-17:00
		
		
		static public final Integer Ct_stars_init=45;//5星,取值范围.0-50.
}


