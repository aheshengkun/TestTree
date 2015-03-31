package com.owf.uuwise;

import java.io.IOException;

import com.owf.params.ConstantParams;
import com.owf.xml.dom.XmlOperate;

public class VerifyImp {
	private String xmlPath = ConstantParams.UUWISEXML;
	/**
	 * 获得图片验证码 成功返回验证字符串，失败返回空字符
	 * @param picPath 图片路径
	 * @param codeType 图片验证类型
	 * @return
	 */
	public String verify(String picPath, int codeType)
	{
		String result = "";
		XmlOperate xmpOp = new XmlOperate();
		
		UUAPI.SOFTID = Integer.parseInt(xmpOp.readXml(xmlPath, "SOFTID"));
		UUAPI.SOFTKEY = xmpOp.readXml(xmlPath, "SOFTKEY");	//KEY 获取方式：http://dll.uuwise.com/index.php?n=ApiDoc.GetSoftIDandKEY
		UUAPI.DLLVerifyKey = xmpOp.readXml(xmlPath, "DLLVerifyKey");//校验API文件是否被篡改，实际上此值不参与传输，关系软件安全，高手请实现复杂的方法来隐藏此值，防止反编译,获取方式也是在后台获取软件ID和KEY一个地方
		UUAPI.USERNAME = xmpOp.readXml(xmlPath, "USERNAME");		//用户帐号和密码(非开发者帐号)，在打码之前，需要先设置好，给用户留一个输入帐号和密码的地方
		UUAPI.PASSWORD = xmpOp.readXml(xmlPath, "PASSWORD");

		boolean status;
		try {
			//校验API，必须调用一次，校验失败，打码不成功
			status = UUAPI.checkAPI();
			if(!status){
				System.out.print("API文件校验失败，无法使用打码服务");
				return result;
			}
			//识别开始
			String data[] = UUAPI.uu_recognizeByCodeTypeAndBytesA(picPath, codeType);//picPath是图片路径,1004是codeType,http://www.uuwise.com/price.html
			System.out.println("this img codeID:"+data[0]);
			System.out.println("return recongize Result:"+data[1]);
			result = data[1];
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return result;
	}
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
}
