package util;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import exception.InvalidInputException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import enumeration.PageType;

import java.io.File;
import java.util.ArrayList;
 
public class XMLFileReader 
{
	private  int count;
	private  Document doc;

	
  public XMLFileReader(File file) throws InvalidInputException 
  {
	  readXML(file);
  }
  
  private void readXML(File file) throws InvalidInputException 
  {
	 if(!file.getName().endsWith(".xml"))
	 {
		 System.out.println("Invalid TestCase File Format (File format must be '.xml')");
	 }
	 else
	 {
		 try
		 {
			 count = 0;
			 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			 doc = dBuilder.parse(file);
			 doc.getDocumentElement().normalize();
		 }
		 catch(Exception e)
		 {
			throw new InvalidInputException(e.getMessage());
		 }
	 }
  }
  
  public String getStartPage()
  {
	  try
	  {
		  String result = "";	  
		  String path ="/testcase/setup/start";
		  result = getTextByXpath(path);  
		  
		  return result;
	  }
	  catch(Exception e)
	  {
		  return "";
	  }
  }  

  public  String getInput(int testStepIndex, String tagPath)
  {
	  try
	  {
		  String result = "";	  
		  String path ="/testcase/execution/step" + testStepIndex +"/" + tagPath;
		  result = getTextByXpath(path);  
		  
		  return result;
	  }
	  catch(Exception e)
	  {
		  return "";
	  }	  
  }
  
//  public  String getInputAttr(int testStepIndex, String tagPath)
//  {
//	  try
//	  {
//		  String result = "";	  
//		  String path ="/testcase/execution/step" + testStepIndex +"/" + tagPath;
//		  NodeList nList = doc.getElementsByTagName(path);
//		  Node nNode = nList.item(0);
//		  if (nNode.getNodeType() == Node.ELEMENT_NODE) 
//		  {
//	          Element eElement = (Element) nNode;
//              result = eElement.getAttribute("attr");
//          }
//		  return result;
//	  }
//	  catch(Exception e)
//	  {
//		  return "";
//	  }  
//  }

  
  public String getAction(int testStepIndex)
  {
	  try
	  {
		  String result = "";	  
		  
		  NodeList nList = doc.getElementsByTagName("step" + testStepIndex);
		  Node nNode = nList.item(0);
		  if (nNode.getNodeType() == Node.ELEMENT_NODE) 
		  {
	          Element eElement = (Element) nNode;
              result = eElement.getAttribute("action");
          }
		  return result;
	  }
	  catch(Exception e)
	  {
		  return "";
	  }  	  
  }
  
  public boolean isTagPathPresent(int testStepIndex, String tagPath)
  {
	  try
	  {
		  String result = "";		  
		  String path ="/testcase/execution/step" + testStepIndex +"/" + tagPath;

		  result = getTextByXpath(path);  
		  
		  if(!result.isEmpty())
		  {
			 return true; 
		  }
		  return false;
	  }
	  catch(Exception e)
	  {
		  return false;
	  }	  
  }
   
  
  public String getPage(int testStepIndex)
  {
	  try
	  {
		  String result = "";	  
		  
		  NodeList nList = doc.getElementsByTagName("step" + testStepIndex);
		  Node nNode = nList.item(0);
		  if (nNode.getNodeType() == Node.ELEMENT_NODE) 
		  {
	          Element eElement = (Element) nNode;
              result = eElement.getAttribute("page");
          }
		  return result;
	  }
	  catch(Exception e)
	  {
		  return "";
	  }  
  }  
  
  
  public  int getNumberOfTestSteps()
  {
	  try
	  {
		  int num = 0;	  
		  
		  NodeList nList = doc.getElementsByTagName("execution");

		  Node nNode = nList.item(0);
		  NodeList nodeList = nNode.getChildNodes();
		  
		    for (int temp = 0; temp < nodeList.getLength(); temp++)
		      {
		         Node node = nodeList.item(temp);
		         if (node.getNodeType() == Node.ELEMENT_NODE)
		         {
		            num++;
		         }
		      }		  
		  return num;
	  }
	  catch(Exception e)
	  {
		  return 0;
	  }
  }
  
  
  public  ArrayList<String> ExpectedErrorsList()
  {
	  try
	  {
		  ArrayList<String> ExpectedErrorsList = new ArrayList();
		  
		  GetAllTextContent(ExpectedErrorsList, doc.getDocumentElement(), "expectederror");
		  return ExpectedErrorsList;
	  }
	  catch(Exception e)
	  {
		  return null;
	  }
  }
  
  private  ArrayList<String> GetAllTextContent(ArrayList<String> allList, Node node, String tagName) 
  {
	  try
	  {
		    if(node.getNodeName().equals(tagName))
		    {
		        if (node.getNodeType() == Node.ELEMENT_NODE) 
		        {
					Element e = (Element) node;
					allList.add(e.getTextContent().trim());
		        }
		    }
		    
		    NodeList nodeList = node.getChildNodes();
		    for (int i = 0; i < nodeList.getLength(); i++) 
		    {
		        Node currentNode = nodeList.item(i);
		        if (currentNode.getNodeType() == Node.ELEMENT_NODE) 
		        {
		            //calls this method for all the children which is Element
		        	GetAllTextContent(allList, currentNode, tagName);
		        }
		    }
		    
		    return allList;
	  }
	  catch(Exception e)
	  {
		  return null;
	  }
	  
  }
  
  
  protected  String getTextByXpath(String path)
  {
	  try
	  {
		  XPathFactory xPathfactory = XPathFactory.newInstance();
		  XPath xpath = xPathfactory.newXPath();
//		  String path = "//action[@pageType=\"donation\"]";
//		  String path = "//subaction[@subpageType=\"eventsettings\"]/firstname" ;
		  XPathExpression expr = xpath.compile(path);
//		  NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		  Node nl = (Node) expr.evaluate(doc, XPathConstants.NODE);

		  String result = nl.getTextContent().trim();
		  if(result == null)
		  {
			  return "";
		  }
		  else
		  {
			  return result;
		  }
	  }
	  catch(Exception e)
	  {
		  return "";
	  }

  }
 
}