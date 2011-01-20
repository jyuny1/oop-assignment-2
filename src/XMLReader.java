/*
 * A class to read an XML file  
 * Author: Liu, Chun-Yi
 * Version: 20.01.2011
 */


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {	
	private String filePath;
	
	/*
	 * the constructor of the class
	 * @param filePath the path pointed to XML file
	 */
	public XMLReader(String filePath){
		this.filePath=filePath;
	}

	/*
	public static void main(String[] args) {
		//XMLReader xmlReader = new XMLReader("movieGlossary.xml");
		XMLReader xmlReader = new XMLReader("tvSeriesGlossary.xml");
		NodeList result = xmlReader.read();
		
		System.out.println("the amount of move is:"+result.getLength());
		for(int i=0;i<result.getLength();i++){
			Node currentNode = result.item(i);
			NodeList currentNodeList = currentNode.getChildNodes();
			
			System.out.print(i+". ");
				
			for(int j=0;j<currentNodeList.getLength();j++){
				currentNode = currentNodeList.item(j);
				System.out.println(currentNode.getNodeName()+"->"+currentNode.getTextContent());
			}
		}
	}
	*/
	
	/*
	 * function to read XML file
	 * @return an DOM element returned if the target file exists
	 */
	private Element initDoc(){
		DocumentBuilderFactory factory;
		DocumentBuilder builder = null;
		Document doc = null;
		Element root = null;
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse(new File(this.filePath));
			root = doc.getDocumentElement();
		} catch (FactoryConfigurationError fce) {
			System.err.println("Could not create DocumentBuilderFactory");
		} catch (ParserConfigurationException pce) {
			System.err.println("Could not locate a JAXP parser");
		} catch (SAXException se) {
			System.err.println("XML file is not well-formed.");
		} catch (IOException ioe) {
			System.err.println("Due to an IOException, the parser could not read the XML file:"+this.filePath);
		}
		
		return root;
	}
	
	/*
	 * the private function to read a specific tag from XML file
	 * @param document the DOM extracted from XML file
	 * @param tagetXMLTag the tag specified by user
	 * @return a NodeList which match with the XPath expression
	 */
	private NodeList selectNodes(String express, Object source){
		NodeList result = null;
	     XPathFactory xpathFactory=XPathFactory.newInstance();
	     XPath xpath=xpathFactory.newXPath();
	        
	     try {
	    	 result=(NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
	     } catch (XPathExpressionException e) {
	            e.printStackTrace();
	     }        
	     
	     return result;
	}
	
	/*
	 * function to read XML file
	 * @return a NodeList which contains a group of term matched given XPath expression
	 */
	public NodeList read() {
		String express = "/glossary/term";
		Element root = initDoc();
		boolean isExist = new File(this.filePath).exists();
		NodeList result = null;
		if(isExist){
			result = selectNodes(express, root);
		}
		return result;
	}
	
	/*
	 * the getter of private variable "filePath"
	 * @return where is the XML file
	 */
	public String getFilePath() {
		return filePath;
	}
}
