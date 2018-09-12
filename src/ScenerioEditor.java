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

/*			NodeList nList = doc.getElementsByTagName("*");

			System.out.println("----------------------------");

			Node n=null;
			Element eElement=null;
			Element ieElement=null;

			for (int i = 0; i < nList.getLength(); i++) {           
			  System.out.println(nList.getLength());     
			  n= nList.item(i);                            
			  System.out.println("\nCurrent Element :" + n.getNodeName());
			  System.out.println("Value             :" + n.getTextContent());

			  if (n.getNodeType() == Node.ELEMENT_NODE) {
			    eElement = (Element) n.getChildNodes();
			    System.out.println("\nCurrent Element :" + eElement.getNodeName());
			    System.out.println("Value             :" + eElement.getTextContent());
			    if(eElement.getNodeType() == Node.ELEMENT_NODE) {
				    ieElement = (Element) n.getChildNodes();
				    System.out.println("\nCurrent Element :" + ieElement.getNodeName());
				    System.out.println("Value             :" + ieElement.getTextContent());
				    eElement.getNextSibling();
			    }
			   // name = eElement.getElementsByTagName("name").item(i).getTextContent(); //here throws null pointer exception after printing staff1 tag
			   // phone = eElement.getElementsByTagName("phone").item(i).getTextContent();
			   // email = eElement.getElementsByTagName("email").item(i).getTextContent();
			   // area = eElement.getElementsByTagName("area").item(i).getTextContent();
			   // city = eElement.getElementsByTagName("city").item(i).getTextContent();
			    
			  }
			  n.getNextSibling();
			}			
			
			
			
			
*/			
			
			
			
			
			
			
			
/*			System.out.println("Loading file ");
			FileInputStream fis = new FileInputStream("C:\\Users\\jhahn\\eclipse-workspace\\MekHQ\\mekhq-master\\MekHQ\\campaigns\\Fist and Falcon\\test.cpnx");
			DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder DB  = DBF.newDocumentBuilder();
			DBF.setValidating(false);
			Document doc = DB.parse(fis);
			System.out.println("File Loaded");
	        Element campaignEle = doc.getDocumentElement();
	        NamedNodeMap a = doc.getAttributes();
	        NodeList nl = campaignEle.getChildNodes();
	        if (a != null) {
		        for(int u = 1; u < a.getLength(); u++) {
		        	System.out.println(a.item(u).getTextContent());
		        }
	        }
	        System.out.println("-------------------------------------------");
	        // Get rid of empty text nodes and adjacent text nodes...
	        // Stupid weird parsing of XML. At least this cleans it up.
	        campaignEle.normalize();
	        
	        for (int t = 0; t < nl.getLength() ;t++) {
	        	System.out.println(nl.item(t).toString());
	        	NodeList nnl = nl.item(t).getChildNodes();
	        	for(int g = 0;g < nnl.getLength(); g++) {
	        		System.out.println("Local Name:"+nnl.item(g).getNodeName());
		        	System.out.println(nnl.item(t).getTextContent());
		        	System.out.println("G Value "+g);
	        	}
	        	System.out.println(nl.item(t).getTextContent());
	        	System.out.println("T Value "+t);
	        	
	        }
	        
			//for (Node start0=doc.getFirstChild(); start0 != null; start0=start0.getNextSibling()) {
			//	    System.out.println(start0.toString());
			//}
			fis.close();
*/			
		}catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		
	}
	
	   public static void printTags(Node nodes){
	       if(nodes.hasChildNodes()  || nodes.getNodeType()!=3){
	           System.out.println(nodes.getNodeName()+" : "+nodes.getTextContent());
	           NodeList nl=nodes.getChildNodes();
	           for(int j=0;j<nl.getLength();j++)printTags(nl.item(j));
	       }
	   }

	   public static void main(String[] args){
		      try {
		         DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		         Document doc = dBuilder.parse(new File("C:\\Users\\jhahn\\eclipse-workspace\\MekHQ\\mekhq-master\\MekHQ\\campaigns\\Fist and Falcon\\test.cpnx"));
		         NodeList nl=doc.getDocumentElement().getChildNodes();

		         for(int k=0;k<nl.getLength();k++){
		             printTags((Node)nl.item(k));
		         }
		      } catch (Exception e) {/*err handling*/}
	   	}   
	   
/*	public static void main(String[] args  ) {
		ScenerioEditor app = new ScenerioEditor();
	}
*/	
}
