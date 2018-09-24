package ScenarioEditor;

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
import javax.swing.JFileChooser;
import java.util.NoSuchElementException;

public class ScenarioEditor {
	ScenarioEditorGUI mFrame = null;
	Version version = null;
	private Vector<Unit> units = new Vector<Unit>(10,2);
	private Vector<Mission> missions = new Vector<Mission>(4,2);
	private Vector<Scenario> scenarios = new Vector<Scenario>(4,2);
	
	/*
	 *  Scenario Editor Constructor
	 */
	public  ScenarioEditor() {
		try {			   
					
			mFrame = new ScenarioEditorGUI("Scenario Editor", this);
			mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			JFileChooser jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(mFrame);
			String sfn = jfc.getCurrentDirectory() + "\\" + jfc.getSelectedFile().getName();
			Document doc = dBuilder.parse(new File(sfn));			
//			Document doc = dBuilder.parse(new File("C:\\MekHQ\\campaigns\\Solo\\Solo30270514.cpnx"));
			Element campaignEle = doc.getDocumentElement();
	        NodeList nl = campaignEle.getChildNodes();
	        campaignEle.normalize();
	        version = new Version(campaignEle.getAttribute("version"));
			System.out.println(campaignEle.getAttribute("version"));
			
			
	        // Step through the file to get 
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
	                if (xn.equalsIgnoreCase("missions")) {
	                	processMissionNodes(wn);
	                }
	            }
	            System.out.println(".");
	        } 		
		} catch (Exception e) {
	    	  /*err handling*/
		}
		mFrame.getUnitPanel().LoadUnit(getUnitByCnt("1"));
		mFrame.getScenarioPanel().loadScenario(getScenarioByID(Integer.toString(getMinimumScenarioID())));
		
	}
	
	/*
	 * Process the Mission nodes in the campaign file
	 */
	private void processMissionNodes(Node wn){
        NodeList wList = wn.getChildNodes();
        int cnt = 0;

        // iterate through the children,
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);

            // If it's not an element node, we ignore it.
            if (wn2.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (!wn2.getNodeName().equalsIgnoreCase("mission")) {
                continue;
            }
            cnt += 1;
            Mission m = new Mission();
            
            NamedNodeMap attrs = wn2.getAttributes();
            // Mission ID
            Node idNode = attrs.getNamedItem("id");
            m.setCnt(idNode.getTextContent());
            // Mission Type
            idNode = attrs.getNamedItem("type");
            m.setMissionType(idNode.getTextContent());
            
            // Okay, now load scenario specific fields
            NodeList nl = wn2.getChildNodes();

            try {
                for (int y=0; y<nl.getLength(); y++) {
                    Node wn3 = nl.item(y);
                    // If it's not an element node, we ignore it.
                    if (wn3.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (wn3.getNodeName().equalsIgnoreCase("name")) {
                    	m.setMissionName(wn3.getTextContent());                   	
                  	}else if (wn3.getNodeName().equalsIgnoreCase("desc")) {
                  		m.setMissionDesc(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("type")) {
                  		m.setMissionTypeDesc(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("planetId")) {
                  		m.setPlanetID(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("status")) {
                  		m.setMissionStatus(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("id")) {
                  		m.setMissionID(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("scenarios")) {
                  		processScenarioNodes(wn3);
                  	}
                    System.out.println("+");
                }                
                System.out.println("Mission: \r\n" + m.toString());
            } catch (Exception ex) {
                // Doh!
                System.err.println( ex.toString() );
            }  
            
        }
		
	}
	
	/*
	 * Process the scenarios in the mission in campaign file
	 */
	private void processScenarioNodes(Node wn) {
        NodeList wList = wn.getChildNodes();
        int cnt = 0;

        // iterate through the children,
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);

            // If it's not an element node, we ignore it.
            if (wn2.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (!wn2.getNodeName().equalsIgnoreCase("scenario")) {
                continue;
            }
            cnt += 1;
            Scenario m = new Scenario();
            
            NamedNodeMap attrs = wn2.getAttributes();
            // Scenario ID
            Node idNode = attrs.getNamedItem("id");
            m.setCnt(idNode.getTextContent());
            // Mission Type
            idNode = attrs.getNamedItem("type");
            m.setType(idNode.getTextContent());
            
            // Okay, now load scenario specific fields
            NodeList nl = wn2.getChildNodes();

            try {
                for (int y=0; y<nl.getLength(); y++) {
                    Node wn3 = nl.item(y);
                    
                    if (wn3.getNodeName().equalsIgnoreCase("name")) {
                    	m.setName(wn3.getTextContent());                   	
                  	}else if (wn3.getNodeName().equalsIgnoreCase("desc")) {
                  		m.setDesc(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("report")) {
                  		m.setReport(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("status")) {
                  		m.setStatus(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("id")) {
                  		m.setID(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("date")) {
                  		m.setDate(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("attacker")) {
                  		m.setAttackers(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("lanceforceid")) {
                  		m.setLanceForceId(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("lanceRole")) {
                  		m.setLanceRole(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("terraintype")) {
                  		m.setTerrainType(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("light")) {
                  		m.setLight(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("weather")) {
                  		m.setWeather(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("wind")) {
                  		m.setWind(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("fog")) {
                  		m.setFog(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("atmosphere")) {
                  		m.setAtmosphere(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("gravity")) {
                  		m.setGravity(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("start")) {
                  		m.setStart(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("deploymentDelay")) {
                  		m.setDeploymentDelay(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("mapsize")) {
                  		m.setMapSize(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("map")) {
                  		m.setMap(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("lanceCount")) {
                  		m.setLanceCount(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("rerollsremaining")) {
                  		m.setRerollsRemaining(wn3.getTextContent());
                  	}else if (wn3.getNodeName().equalsIgnoreCase("botForceStub")) {
                  		BotForce bf = new BotForce();
                  		
                  		NamedNodeMap nnm = wn3.getAttributes();
                  		Node nnms = nnm.getNamedItem("name");
                  		bf.setName(nnms.getTextContent());
                  		processBotForceEntities(wn3, bf);
                  		m.addBotForce(bf);
                  	}
                }
                scenarios.add(m);
                //System.out.println("Mission: \r\n" + m.toString());
            } catch (Exception ex) {
                // Doh!
                System.err.println( ex.toString() );
            }  
            
        }		
	}
	
	/*
	 * Process Bot Force Entities in campaign file 
	 * add entities to the botforce passed to it
	 */
	public void processBotForceEntities(Node wn,BotForce b) {
        NodeList wList = wn.getChildNodes();
        int cnt = 0;

        // iterate through the children,
        for (int x = 0; x < wList.getLength(); x++) {
            Node wn2 = wList.item(x);

            try {
                    if (wn2.getNodeName().equalsIgnoreCase("entityStub")) {
                    	cnt += 1;                    
                    	BotEntity be = new BotEntity();
                    	be.setID(Integer.toString(cnt));
                    	be.setName(wn2.getTextContent());  
                    	b.addBotEntity(be);
                  	}                
                //System.out.println("Mission: \r\n" + m.toString());
            } catch (Exception ex) {
                // Doh!
                System.err.println( ex.toString() );
            }  
            		
        }
	}
	
	/* 
	 * Process the Units in the Campaign File
	 */
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
	
	/*
	 * Process the parts for the Units
	 */
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
	                    	System.out.println("In ProcessPartNodes : " + wpn);
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
	
	/*
	 * get unit objectfrom units vector by Cnt number 
	 */
	public Unit getUnitByCnt(String s) {
		Unit ul= null;
		try {
			Iterator<Unit> itr = units.iterator();
			do{
				ul = itr.next();
			}while(!s.equals(ul.getCnt()));
		}catch(NoSuchElementException nsee) {
			return new Unit();
		}catch(Exception e) {
			
		}
		return ul;
	}
	
	/*
	 * get unit object from vector by unique ID
	 */
	public Unit getUnitByUnitID(String s) {
		Unit ul= null;
		Iterator<Unit> itr = units.iterator();
		do{
			ul = itr.next();
		}while(!s.equals(ul.getUnitID()));
		return ul;
	}
	
	/*
	 * helper method to replace a unit object
	 * once it has been updated or modified in 
	 * some way
	 */
	public void replaceUnit(Unit u) {
		Unit ul= null;
		int cnt = 0;
		try {
			Iterator<Unit> itr = units.iterator();
			while(itr.hasNext()) {
				ul = itr.next();
				if(u.getCnt().equals(ul.getCnt())) {
					units.set(cnt, u);	
				}
				cnt++;
			}
		}catch(Exception e) {
			System.err.println("Inside ReplaceUnit :" +e.toString());
		}
		
	}
	
	/*
	 * get size of Units
	 */
	public int getUnitsSize() {
		return units.size();
	}

	/*
	 * get scenario object by  id
	 */
	public Scenario getScenarioByID(String s) {
		Scenario ss = null;
		try {
			Iterator<Scenario> itr = scenarios.iterator();
			do {
				  ss = itr.next();
			}while(!s.equals(ss.getCnt()));
		}catch(NoSuchElementException nsee) {
			return new Scenario();
		}catch(Exception e) {
			
		}
		return ss;
	}
	
	/*
	 * get scenario object by  id
	 */
	public int getScenarioSize() {
		return scenarios.size();
	}
	
	/*
	 * get minimum ID of scenerio in scenerios
	 */
	public int getMinimumScenarioID() {
		int i = 1000;
		int v = 0;
		try {
		Scenario ss = null;
		Iterator<Scenario> itr = scenarios.iterator();
		do {
			  ss = itr.next();
			  v = new Integer(ss.getCnt()).intValue();
			  if(i > v) {
				  i = v;
			  }
		}while(itr.hasNext());
		}catch(NoSuchElementException nsee) {
			return i;
		}catch (Exception e) {
			
		}
		return i;
	}
	
	/*
	 * get maximum ID of scenerio in scenerios
	 */
	public int getMaximumScenarioID() {
		int i = 0;
		int v = 0;
		Scenario ss = null;
		Iterator<Scenario> itr = scenarios.iterator();
		do {
			  ss = itr.next();
			  v = new Integer(ss.getCnt()).intValue();
			  if(i < v) {
				  i = v;
			  }
		}while(itr.hasNext());
		return i;
	}
		
	
	
	public static void main(String[] args){
		ScenarioEditor s = new ScenarioEditor();
	}   
		   

}
