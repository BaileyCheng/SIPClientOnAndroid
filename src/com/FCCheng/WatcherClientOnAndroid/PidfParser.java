package com.FCCheng.WatcherClientOnAndroid;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class PidfParser
{
 String PIDF="";
 //DocumentBuilderFactory factory;
 //DocumentBuilder builder;
 public PidfParser(){
	 //this.factory = DocumentBuilderFactory.newInstance();
	 
 }
 
 public String parserTag(String pidf,String tagName){
	 String tagValue = "";
	 if(tagName.equals("basic")){
		 tagValue = pidf.substring(pidf.indexOf("<basic>")+7, pidf.indexOf("</basic>"));
		 //System.out.println("<basic>"+ tagValue+"</basic>");
	 }else if (tagName.equals("rpid:status-icon")){
		 tagValue = pidf.substring(pidf.indexOf("<rpid:status-icon>")+18, pidf.indexOf("</rpid:status-icon>"));
		 //System.out.println("<rpid:status-icon>"+ tagValue+"</rpid:status-icon>");
	 }else if (tagName.equals("note")){
		 tagValue = pidf.substring(pidf.indexOf("<note>")+6, pidf.indexOf("</note>"));
		 //System.out.println("<note>"+ tagValue+"</note>");
	 }
	 
	 return tagValue;
	 /*
	 try{
	   DocumentBuilder builder = factory.newDocumentBuilder();
	  
	   Document doc = builder.parse(new String(pidf));
	   
	   NodeList status1List = doc.getElementsByTagName("basic");
	   Node childNode = status1List.item(0);
	   System.out.println(""+childNode.getNodeName()+" value:"+ childNode.getNodeValue());
	   TagValue = childNode.getNodeValue();
	 }catch(SAXParseException e){

	 System.out.println(e);

	 }catch(Exception e){

	 System.out.println(e);

	 }
	 */
	
 	}
 	
}
