import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

//import mekhq.campaign.Campaign;
//import megamek.MegaMek;
//import megameklab.com.*;
import java.io.FileInputStream;
import java.io.*;


public class ScenerioEditor {
	
	public  ScenerioEditor() {
		try {
		
			
			File fXmlFile = new File("C:\\Users\\jhahn\\eclipse-workspace\\MekHQ\\mekhq-master\\MekHQ\\campaigns\\Fist and Falcon\\test.cpnx");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		}catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		
	}
	
	   public static void printTags(Node nodes){
		   //System.out.println("NODE_TYPE : " + nodes.getNodeType());
		   if (nodes.hasAttributes()) {
	           NamedNodeMap nnm = nodes.getAttributes();
	           if (nnm.getLength() >0 ) {
	        	   System.out.print("<"+nodes.getNodeName());
	        	   for( int i =0 ; i < nnm.getLength() ; i++) {
		        	   Node nk =  nnm.item(i);
		        	   System.out.print (" " +nk.getNodeName() + "=\"" + nk.getNodeValue()+"\"");
		           }
	        	   System.out.println(">");
	           }
	           	//System.out.println(nodes.getAttributes());
	           
		   }
	       if(nodes.hasChildNodes()  || nodes.getNodeType()!=3){
	           NodeList nl=nodes.getChildNodes();
	           System.out.println("<"+nodes.getNodeName()+">");

	           for(int j=0;j<nl.getLength();j++)printTags(nl.item(j));
	         
	       }
	   }

	   public static void main(String[] args){
		      try {
		         DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		         Document doc = dBuilder.parse(new File("C:\\Users\\jhahn\\eclipse-workspace\\MekHQ\\mekhq-master\\MekHQ\\campaigns\\Fist and Falcon\\test.cpnx"));
		         NodeList nl=doc.getDocumentElement().getChildNodes();

		         for(int k=0;k<nl.getLength();k++){
		        	 if(((Node)nl.item(k)).getNodeName().equals("forces")) {
		        		 printTags((Node)nl.item(k));
		        	 }
		         }
		      } catch (Exception e) {/*err handling*/}
	   	}   
	   

}
