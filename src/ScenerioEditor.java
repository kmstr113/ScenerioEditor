import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import mekhq.campaign.unit.*;
import mekhq.Version; 


//import mekhq.campaign.Campaign;
//import megamek.MegaMek;
//import megameklab.com.*;
import java.io.FileInputStream;
import java.util.UUID;
import java.io.*;
import javax.swing.*;


public class ScenerioEditor {
	ScenerioEditorGUI mFrame = null;
	Version version = null;
	
	public  ScenerioEditor() {
		try {			   
			mFrame = new ScenerioEditorGUI("Scenerio Editor");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(new File("C:\\Users\\jhahn\\eclipse-workspace\\MekHQ\\mekhq-master\\MekHQ\\campaigns\\Fist and Falcon\\test.cpnx"));
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

	                if (xn.equalsIgnoreCase("units")) { // This is needed so that the campaign name gets set in retVal
	                    processUnitNodes( wn);
	                }
	            }
	        }
/*			NodeList nl=doc.getDocumentElement().getChildNodes();
			for(int k=0;k<nl.getLength();k++){
				if(((Node)nl.item(k)).getNodeName().equals("units")) {
					printTags((Node)nl.item(k));
				}
			}
*/  

		} catch (Exception e) {
	    	  /*err handling*/
		}
		
	}
	
	private void processUnitNodes(Node wn) {
        NodeList wList = wn.getChildNodes();
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
            NamedNodeMap attrs = wn2.getAttributes();
            Node idNode = attrs.getNamedItem("id");
            id = UUID.fromString(idNode.getTextContent());
            mFrame.getUnitPanel().setUID(id.toString());
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
                    	mFrame.getUnitPanel().setChassisType(chassis);
                    }
 /*                 if (wn2.getNodeName().equalsIgnoreCase("site")) {
                        site = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("pilotId")) {
                        retVal.pilotId = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("daysToArrival")) {
                        retVal.daysToArrival = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("daysActivelyMaintained")) {
                        retVal.daysActivelyMaintained = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("daysSinceMaintenance")) {
                        retVal.daysSinceMaintenance = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("mothballTime")) {
                        retVal.mothballTime = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("astechDaysMaintained")) {
                        retVal.astechDaysMaintained = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("driverId")) {
                        if(version.getMajorVersion() == 0 && version.getMinorVersion() < 2 && version.getSnapshot() < 14) {
                            retVal.oldDrivers.add(Integer.parseInt(wn2.getTextContent()));
                        } else {
                            retVal.drivers.add(UUID.fromString(wn2.getTextContent()));
                        }
                    } else if (wn2.getNodeName().equalsIgnoreCase("gunnerId")) {
                        if(version.getMajorVersion() == 0 && version.getMinorVersion() < 2 && version.getSnapshot() < 14) {
                            retVal.oldGunners.add(Integer.parseInt(wn2.getTextContent()));
                        } else {
                            retVal.gunners.add(UUID.fromString(wn2.getTextContent()));
                        }
                    } else if (wn2.getNodeName().equalsIgnoreCase("vesselCrewId")) {
                        if(version.getMajorVersion() == 0 && version.getMinorVersion() < 2 && version.getSnapshot() < 14) {
                            retVal.oldVesselCrew.add(Integer.parseInt(wn2.getTextContent()));
                        } else {
                            retVal.vesselCrew.add(UUID.fromString(wn2.getTextContent()));
                        }
                    } else if (wn2.getNodeName().equalsIgnoreCase("navigatorId")) {
                        if(version.getMajorVersion() == 0 && version.getMinorVersion() < 2 && version.getSnapshot() < 14) {
                            retVal.oldNavigator = Integer.parseInt(wn2.getTextContent());
                        } else {
                            if(!wn2.getTextContent().equals("null")) {
                                retVal.navigator = UUID.fromString(wn2.getTextContent());
                            }
                        }
                    } else if (wn2.getNodeName().equalsIgnoreCase("techOfficerId")) {
                        if(!wn2.getTextContent().equals("null")) {
                            retVal.techOfficer = UUID.fromString(wn2.getTextContent());
                        }
                    }
                    else if (wn2.getNodeName().equalsIgnoreCase("techId")) {
                        if(!wn2.getTextContent().equals("null")) {
                            retVal.tech = UUID.fromString(wn2.getTextContent());
                        }
                    }
                    else if (wn2.getNodeName().equalsIgnoreCase("forceId")) {
                        retVal.forceId = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("scenarioId")) {
                        retVal.scenarioId = Integer.parseInt(wn2.getTextContent());
                    } else if (wn2.getNodeName().equalsIgnoreCase("salvaged")) {
                        if (wn2.getTextContent().equalsIgnoreCase("true"))
                            retVal.salvaged = true;
                        else
                            retVal.salvaged = false;
                    } else if (wn2.getNodeName().equalsIgnoreCase("mothballed")) {
                        if (wn2.getTextContent().equalsIgnoreCase("true"))
                            retVal.mothballed = true;
                        else
                            retVal.mothballed = false;
                    } else if (wn2.getNodeName().equalsIgnoreCase("entity")) {
                        retVal.entity = MekHqXmlUtil.getEntityFromXmlString(wn2);
                    } else if (wn2.getNodeName().equalsIgnoreCase("refit")) {
                        retVal.refit = Refit.generateInstanceFromXML(wn2, retVal, version);
                    } else if (wn2.getNodeName().equalsIgnoreCase("history")) {
                        retVal.history = wn2.getTextContent();
                    } else if (wn2.getNodeName().equalsIgnoreCase("fluffName")) {
                        retVal.fluffName = wn2.getTextContent();
                    } else if (wn2.getNodeName().equalsIgnoreCase("lastMaintenanceReport")) {
                        retVal.lastMaintenanceReport = wn2.getTextContent();
                    } else if (wn2.getNodeName().equalsIgnoreCase("mothballInfo")) {
                        retVal.mothballInfo = MothballInfo.generateInstanceFromXML(wn2, version);
                    }
 */
                }
            } catch (Exception ex) {
                // Doh!
                System.err.println( ex.toString() );
            }

   
          
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
		ScenerioEditor s = new ScenerioEditor();
	}   
		   

}
