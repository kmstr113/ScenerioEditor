package ScenerioEditor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import mekhq.Version; 
import java.lang.Integer;
import java.util.Iterator;

//import mekhq.campaign.Campaign;
//import megamek.MegaMek;
//import megameklab.com.*;
import java.io.FileInputStream;
import java.util.UUID;
import java.io.*;
import javax.swing.*;
import java.math.*;
import java.util.Vector;
import java.util.List;

public class ScenerioEditor {
	ScenerioEditorGUI mFrame = null;
	Version version = null;
	private Vector<Unit> units = new Vector<Unit>(10,2);
	
	public  ScenerioEditor() {
		try {			   
					
			mFrame = new ScenerioEditorGUI("Scenerio Editor");
			mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(new File("C:\\MekHQ\\campaigns\\Fist and Falcon\\test.cpnx"));
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

	                if (xn.equalsIgnoreCase("units")) { 
	                    processUnitNodes( wn);
	                }
	                if (xn.equalsIgnoreCase("parts")) { 
	                    processPartNodes(wn);
	                }
	            }
	        } 		

		} catch (Exception e) {
	    	  /*err handling*/
		}
		
		mFrame.getUnitPanel().LoadUnit(getUnitByCnt("1"));
		
		
		
	}
	
	public Unit getUnitByCnt(String s) {
		Unit ul= null;
		Iterator<Unit> itr = units.iterator();
		do{
			ul = itr.next();
		}while(!s.equals(ul.getCnt()));
		return ul;
	}
	
	public Unit getUnitByUnitID(String s) {
		Unit ul= null;
		Iterator<Unit> itr = units.iterator();
		do{
			ul = itr.next();
		}while(!s.equals(ul.getUnitID()));
		return ul;
	}
	
	public void replaceUnit(Unit u) {
		Unit ul= null;
		int cnt = 0;
		Iterator<Unit> itr = units.iterator();
		while(itr.hasNext()) {
			cnt++;
			ul = itr.next();
			if(u.getCnt().equals(ul.getCnt())) {
				units.set(cnt, u);	
			}
		}
		
	}
	
	private void processUnitNodes(Node wn) {
        NodeList wList = wn.getChildNodes();
        int cnt = 0;
        UUID id;
        int oldId;
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
            cnt += 1;
            Unit u = new Unit();
            
            NamedNodeMap attrs = wn2.getAttributes();
            Node idNode = attrs.getNamedItem("id");
            id = UUID.fromString(idNode.getTextContent());
            u.setCnt(Integer.toString(cnt));
            u.setUnitID(id.toString());
            
            // Okay, now load Part-specific fields!
            NodeList nl = wn2.getChildNodes();

            try {
                for (int y=0; y<nl.getLength(); y++) {
                    Node wn3 = nl.item(y);
                    
                    if (wn3.getNodeName().equalsIgnoreCase("entity")) {
                    	NamedNodeMap eattrs = wn3.getAttributes();
                    	System.out.println(eattrs.getNamedItem("chassis").getTextContent());
                    	u.setChassisType(eattrs.getNamedItem("chassis").getTextContent());
                    	u.setModel(eattrs.getNamedItem("model").getTextContent());
                    	u.setMType(eattrs.getNamedItem("type").getTextContent());
                    	u.setUnitCommander(eattrs.getNamedItem("commander").getTextContent());
                    	units.add(u);
                    	
                  	}
                }                
                System.out.println("Units in Vector: "+units.size());
            } catch (Exception ex) {
                // Doh!
                System.err.println( ex.toString() );
            }         
        }
    }	
	private void processPartNodes(Node wn) {
        NodeList wList = wn.getChildNodes();
        int cnt = 0;
        String wpn = "";
        String unitId = ""; 

        // Okay, lets iterate through the children, eh?
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);

            // If it's not an element node, we ignore it.
            if (wn2.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (!wn2.getNodeName().equalsIgnoreCase("part")) {
                // Error condition of sorts!
                // Errr, what should we do here?
                continue;
            }
            
            cnt += 1;
            NamedNodeMap attrs = wn2.getAttributes();
            Node idNode = attrs.getNamedItem("id");
        	//System.out.println("PARTS ID " +  idNode.getTextContent());                      
        	idNode = attrs.getNamedItem("type");
        	
        	if(idNode.getTextContent().equals("mekhq.campaign.parts.equipment.EquipmentPart")) {
        		//System.out.println("PART TYPE " + idNode.getTextContent());
	        	// Okay, now load Part-specific fields!
	            NodeList nl = wn2.getChildNodes();
	            try {
	                for (int y=0; y<nl.getLength(); y++) {
	                    Node wn3 = nl.item(y);
	                    
	                    if (wn3.getNodeName().equalsIgnoreCase("name")) {
	                    	wpn = wn3.getTextContent();
	                  	}else if (wn3.getNodeName().equalsIgnoreCase("unitId")) {
	                  		unitId = wn3.getTextContent();
	                  	}
	                }
	                Unit unl = getUnitByUnitID(unitId);
	                unl.setWeapons(wpn);
	                replaceUnit(unl);
	                
	            } catch (Exception ex) {
	                // Doh!
	                System.err.println( ex.toString() );
	            } 
        	}
        }
	}
	
	

	public static void main(String[] args){
		ScenerioEditor s = new ScenerioEditor();
	}   
		   

}
