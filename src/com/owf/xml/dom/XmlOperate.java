package com.owf.xml.dom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlOperate {
	private Document document = null;
	
	public void createXml(String fileName) 
	{//创建xml文档
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
		
			this.document = db.newDocument();//创建xml与解析xml不同的地方
			document.normalize();
		}
		catch(ParserConfigurationException e)
		{
			System.out.println(e.getMessage());
		}
		
		Element root = this.document.createElement("root");//创建根节点
		this.document.appendChild(root);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		try
		{
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			pw.close();
		}
		catch(TransformerConfigurationException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(TransformerException e)
		{
			System.out.println(e.getMessage());
		}	
	}
	
	public void updateXml(String fileName, String nodeName, String text)
	{
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			this.document = db.parse(fileName); //解析xml与创建xml不同的地方
			this.document.normalize();
			document.getElementsByTagName(nodeName).item(0).setTextContent(text);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			pw.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public List<Data> readXmls(String fileName, String nodeName, String attributeName)
	{
		List<Data> result = new ArrayList<Data>();
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			this.document = db.parse(fileName); //解析xml与创建xml不同的地方
			this.document.normalize();
			NodeList list = document.getElementsByTagName(nodeName);
			for(int i=0; i<list.getLength(); i++)
			{
				String text = list.item(i).getTextContent();
				Data data = new Data();
				data.text = text;
				data.data = list.item(i).getAttributes().getNamedItem(attributeName).getTextContent();
				result.add(data);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public List<String> readXmls(String fileName, String nodeName)
	{
		List<String> result = new ArrayList<String>();
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			this.document = db.parse(fileName); //解析xml与创建xml不同的地方
			this.document.normalize();
			NodeList list = document.getElementsByTagName(nodeName);
			for(int i=0; i<list.getLength(); i++)
			{
				String text = list.item(i).getTextContent();
				result.add(text);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public String readXml(String fileName, String nodeName)
	{
		String result = "";
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			this.document = db.parse(fileName); //解析xml与创建xml不同的地方
			this.document.normalize();
			String text = document.getElementsByTagName(nodeName).item(0).getTextContent();
			result = text;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;

	}
	
	public void parserXML(String fileName)
	{//解析xml文档
		try
		{
			try
			{
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				this.document = db.parse(fileName); //解析xml与创建xml不同的地方
				this.document.normalize();
				//Document document = db.parse(fileName);
			}
			catch(ParserConfigurationException e)
			{
				System.out.println(e.getMessage());
			}
			
			NodeList employees = document.getChildNodes();
			for(int i = 0; i < employees.getLength(); i++)
			{
				Node employee = employees.item(i);
				NodeList employeeInfo  =employee.getChildNodes();
				for(int j = 0; j < employeeInfo.getLength(); j++)
				{
					Node node = employeeInfo.item(j);
					NodeList employeeMeta = node.getChildNodes();
					for(int k = 0;k < employeeMeta.getLength(); k++)
					{
						System.out.println(employeeMeta.item(k).getNodeName() + ":" + employeeMeta.item(k).getTextContent());
					}
				}
			}
			System.out.println("解析完毕");
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(SAXException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void addNode(String fileName, String nodeName, String nodeValue, String fatherNodeName) 
	{//在一指定的xml文档中，父节点下添加一个节点
		try
		{
			//得到DOM解析器的工厂实例
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//从DOM工厂里获取DOM解析器
			DocumentBuilder db = dbf.newDocumentBuilder();
			//解析XML文档，得到document, 即DOM树
			this.document = db.parse(fileName);
			//去掉XML文档中作为格式化内容的空白而映射在DOM树中的不必要的Text Node对象
			this.document.normalize();
			//创建一个标签
			Element myname =document.createElement(nodeName);//创建一个节点
 
			myname.setTextContent(nodeValue);
//			myname.setAttribute("name", "xxxx");//为该标签添加名字属性
			
//			Element mybook=document.createElement("mybook");
//			mybook.setAttribute("id", "Date Structure");
//			mybook.setAttribute("name", "423");
			
			//添加父子关系
//			myname.appendChild(mybook);
			
			//将标签添加到父标签里
			Element fatherElement=(Element)document.getElementsByTagName(fatherNodeName).item(0);
			fatherElement.appendChild(myname);
			
			//保存xml文档
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			//设置编码类型
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			StreamResult result = new StreamResult(new FileOutputStream(fileName));
			
			//把DOM树转换为xml文档
			transformer.transform(source, result);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
