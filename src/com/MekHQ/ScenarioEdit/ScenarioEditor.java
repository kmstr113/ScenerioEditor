package com.MekHQ.ScenarioEdit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import mekhq.Version; 
import java.io.File;
import java.util.UUID;
import javax.swing.WindowConstants;
import javax.swing.JFileChooser;

/**
 * @author jhahn
 *
 */
public class ScenarioEditor {
	ScenerioEditorGUI mFrame = null;
	JFileChooser fc = null;
	Version version = null;
	File fle = null;
	
	public  ScenarioEditor() {
		try {			   
			mFrame = new ScenerioEditorGUI("Scenerio Editor", this);
			mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			fc = new JFileChooser();
			fc.showOpenDialog(mFrame);
			fle = fc.getSelectedFile();
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(fle);
//			Document doc = dBuilder.parse(new File("C:\\temp\\MekHQ-0.46\\mekhq-windows-0.46.0\\campaigns\\Sedgwicks Scouts\\Sedgwicks Scouts30250709.cpnx"));
			Element campaignEle = doc.getDocumentElement();
	        NodeList nl = campaignEle.getChildNodes();
	        campaignEle.normalize();
	        version = new Version(campaignEle.getAttribute("version"));
			System.out.println(campaignEle.getAttribute("version"));			
			
	        // we need to iterate through three times, the first time to collect
	        // any custom units that might not be written yet
	        for (int x = 0; x < nl.getLength(); x++) {
	            Node wn = nl.item(x);

	            if (wn.getParentNode() != campaignEle) {
	                continue;
	            }
	            int xc = wn.getNodeType();

	            if (xc == Node.ELEMENT_NODE) {
	                // This is what we really care about.
	                // All the meat of our document is in this node type, at this
	                // level.
	                // Okay, so what element is it?
	                String xn = wn.getNodeName();
	                
	                if (xn.equalsIgnoreCase("info")) {
	                	processInfoNodes(wn);
	                }
	                if (xn.equalsIgnoreCase("units")) { // This is needed so that the campaign name gets set in retVal
	                    processUnitNodes(wn);
	                }
	                if (xn.equalsIgnoreCase("missions")) { // This is needed so that the campaign name gets set in retVal
	                    processMissionsNodes(wn);
	                }
	            }
	        }

		} catch (Exception e) {
	    	  /*err handling*/
			System.err.println(e.toString());
		}
		
	}
	
	private void processUnitNodes(Node wn) {
        
		NodeList wList = wn.getChildNodes();
        UUID id;
        String chassis;
        

        // Okay, lets iterate through the children, eh?
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);

            // If it's not an element node, we ignore it.
            if (wn2.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (!wn2.getNodeName().equalsIgnoreCase("unit")) {
                // Error condition of sorts!
                // Errr, what should we do here?
                continue;
            }
            UnitPanel up = new UnitPanel();
            NamedNodeMap attrs = wn2.getAttributes();
            Node idNode = attrs.getNamedItem("id");
            id = UUID.fromString(idNode.getTextContent());
            up.setUID(id.toString());
            //mFrame.getUnitPanel().setUID(id.toString());
            // Okay, now load Part-specific fields!
            NodeList nl = wn2.getChildNodes();
 
            try {
                for (int y=0; y<nl.getLength(); y++) {
                    Node wn3 = nl.item(y);
                    
                    if (wn3.getNodeName().equalsIgnoreCase("entity")) {
                    	NamedNodeMap eattrs = wn3.getAttributes();
                    	Node chassisNode = eattrs.getNamedItem("chassis");
                    	chassis = chassisNode.getTextContent();
                    	System.out.println(chassis);
                    	up.setChassisType(chassis);
                    	mFrame.getTabbedUnitPanel().add(up);
                    	//mFrame.getUnitPanel().setChassisType(chassis);
                    }
                }
                
            } catch (Exception ex) {
                // Doh!
                System.err.println( ex.toString() );
            }
        }
        
    }	

	private void processInfoNodes(Node wn) {
        NodeList wList = wn.getChildNodes();
        String s = "";

        // Okay, lets iterate through the children, eh?
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);

            if (wn2.getNodeName().equalsIgnoreCase("name")) {
                s = wn2.getTextContent();
                //wn2.setTextContent("");
                mFrame.getInfoPanel().setname(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("faction")) {
            	s = wn2.getTextContent();
            	mFrame.getInfoPanel().setfaction(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("percentFemale")) {
            	s = wn2.getTextContent();
            	mFrame.getInfoPanel().setPctFemale(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("astechPool")) {
            	s = wn2.getTextContent();
            	mFrame.getInfoPanel().setastechPool(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("medicPool")) {
            	s = wn2.getTextContent();
            	mFrame.getInfoPanel().setmedicPool(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("calendar")) {
            	s = wn2.getTextContent();
            	mFrame.getInfoPanel().setcalDate(s);
            }
            
            s = "";
        }
    }	

	private void processMissionsNodes(Node wn) {
		NodeList wList = wn.getChildNodes();
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);
            if (wn2.getNodeName().equalsIgnoreCase("mission")) {
        	     NodeList wList2 = wn2.getChildNodes();
        	     for(int y = 0; y < wList2.getLength(); y++) {
        	    	 Node wn3 = wList2.item(y);
        	    	 if(wn3.getNodeName().equalsIgnoreCase("scenarios")) {
        	    		NodeList wList3 = wn3.getChildNodes();
        	    		for(int z = 0; z < wList3.getLength(); z++) {
        	    		    Node wn4 = wList3.item(z);
        	    			if(wn4.getNodeName().equalsIgnoreCase("scenario")) {
        	    		       processScenariosNodes(wn4);
        	    			}
        	    		}
        	    	 
        	    	 }
        	     }
            }
        }
	}
	
	private void processScenariosNodes(Node wn) {
		ScenarioPanel sp = new ScenarioPanel();
		
        NodeList wList = wn.getChildNodes();
        String s = "";

        // Okay, lets iterate through the children, eh?
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);

            if (wn2.getNodeName().equalsIgnoreCase("name")) {
                s = wn2.getTextContent();
                sp.setname(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("desc")) {
            	s = wn2.getTextContent();
            	sp.setDesc(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("report")) {
            	s = wn2.getTextContent();
            	sp.setReport(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("status")) {
            	s = wn2.getTextContent();
            	sp.setStatus(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("ID")) {
            	s = wn2.getTextContent();
            	sp.setID(s);
            }
            if (wn2.getNodeName().equalsIgnoreCase("date")) {
            	s = wn2.getTextContent();
            	sp.setcalDate(s);
            }
            
            s = "";
        }
        mFrame.getTabbedScenarioPanel().add(sp);
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
		 new ScenarioEditor();
	}   
		   

}
