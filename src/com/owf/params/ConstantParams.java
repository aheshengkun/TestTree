package com.owf.params;

public class ConstantParams {
	public static final String CHENG_LINE = "\r\n";
	public static final String BLANK = "  ";
	public static final String SINGLE_BLANK = " ";
	public static final String TABLE = "\t";
	public static final String CRAWL_PROPERTIES = "config.properties";
	
	//task xml
	public static final String TASK_DBNAME = "task";
	public static final String TASK_FILENAME = "source/task";
	public static final String TASK_BASEPATH = "basePath";
	public static final String TASK_URL = "url";
	public static final String TASK_URLCOLLECT = "urlCollect";
	public static final String TASK_URLTRAVERSAL = "urlTraversal";
	
	//special task xml
	public static final String SPECIALTASK_DBNAME = "specialtask";
	public static final String SPECIALTASK_FILENAME = "source/specialtask";
	public static final String SPECIALTASK_BASEPATH = "basePath"; 
	
	//url rule xml
	public static final String URLRULE_DBNAME = "urlrule";
	public static final String URLRULE_FILENAME = "source/urlrule";
	public static final String URLRULE_REGEX = "regex";
	public static final String URLRULE_XPATH = "xpath";
	public static final String URLRULE_TYPE = "ruleType";
	
	//type
	public static final String REGEX_TYPE = "使用正则表达式获取网页内连接";
	public static final String XPATH_TYPE = "使用Xpath表达式获取网页内连接";
	public static final String XPATHREGEX_TYPE = "使用Xpath和正则表达式获取网页内连接";
	
	//content rule
	public static final String CONTENTRULE_DBNAME = "contentrule";
	public static final String CONTENTRULE_FILENME = "source/contentrule";
	
	//gxsp tmpl
	public static final String TMPLPATH = "source/tmpl/gxsp.html"; //模板
	public static final String URLPRE = "http://120.24.64.202/aguiowf/gxsp/";
	public static final String TMPL_FIELPAHT = "E:/source/html/file/"; //通过模板生成好的文件的存放位置
	public static final String SPCTITYXMLPAHT = "source/specialtask/cityxml/";
	
	//uuwise xml
	public static final String UUWISEXML = "source/uuwise/base.xml"; //优优云配置参数位置
}
