package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
	
	public static void processXml(InputStream inputStream, String currencyPair, Float targetVal){
		
			System.out.println("**************** Start of Processing Xml************");
			Float currentBid=0.0f;
			
			try{
				
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder builder =  builderFactory.newDocumentBuilder();
				
				Document xmlDocument = builder.parse(inputStream);
		
				XPath xPath =  XPathFactory.newInstance().newXPath();
		
			
				//String expression = "/Rates/Rate[@Symbol='USDJPY']/*";//hard-coded
				String expression = "/Rates/Rate[@Symbol='"+currencyPair+"']/Bid";
				//System.out.println(expression);//comment this later
				
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

				//for (int i = 0; i < nodeList.getLength(); i++) {//no need to loop through all elements
					
					if( nodeList!=null && nodeList.getLength() > 0 ){
								
							currentBid = Float.parseFloat(nodeList.item(0).getFirstChild().getNodeValue()) ;
							
							if(currentBid > targetVal){
								System.out.println("Hey, the target val is reached::"+ currentBid);
								
							}else{
								System.out.println("currentBid:"+currentBid);
							}
							
					}
					
					
				//}
		
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			
			System.out.println("**************** End of Processing Xml************\n\n");
			
	}
		
	


}
