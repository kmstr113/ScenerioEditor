package ScenarioEditor;

import java.util.Iterator;
import java.util.Vector;

public class Mission {

	private String cnt = "";
    private String missionID = "";
    
    private String missionName = "";
    private String missionType = "";
    private String missionPlanetID = "";
    private String missionStatus = "";
    private String missionDesc = "";
    private String missionTypeDesc = "";
    private Vector<Scenario> scenarios = null;
    
    public Mission() {
    	scenarios = new Vector<Scenario>(4,2);
    }
    
    public String toString() {
    	String s = "";
    	s =  "Cnt : " + cnt +"\r\n";
    	s += "Mission ID : " + missionID + "\r\n";
    	s += "Mission Name : " + missionName + "\r\n";
    	s += "Mission Type : " + missionType + "\r\n";
    	s += "Planet ID : " + missionPlanetID + "\r\n";
    	s += "Status : " + missionStatus + "\r\n";
    	s += "Description : " + missionDesc + "\r\n";
    	s += "Mission Type Desc: " + missionTypeDesc + "\r\n";
    	
     	return s;
    }
    public String getCnt() {
    	return cnt;
    }
    
    public void setCnt(String c) {
    	cnt = c;
    }
    
    public String getMissionID() {
    	return missionID;
    }
    
    public void setMissionID(String s) {
    	missionID = s;
    }
    
    public void setMissionName(String s) {
    	missionName = s;
    }
    
    public String getMissionName() {
    	return missionName;
    }
    public void setMissionType(String s) {
    	missionType = s;
    }
    
    public String getMissionType() {
    	return missionType;
    }
    
    public void setPlanetID(String s) {
    	missionPlanetID = s;
    }
    
    public String getPlanetID() {
    	return missionPlanetID;
    }
    
    public void setMissionStatus(String s) {
    	missionStatus = s;
    }
    
    public String getMissionStatus() {
    	return missionStatus;
    }
    
    public void setMissionDesc(String s) {
    	missionDesc = s;
    }
    
    public String getMissionDesc() {
    	return missionDesc;
    }
    
    public void setMissionTypeDesc(String s) {
    	missionTypeDesc = s;
    }
    
    public String getMissionTypeDesc() {
    	return missionTypeDesc;
    }
    
    public void addScenario(Scenario s) {
    	scenarios.add(s);
    }
    
    /*
     *  get Scenario by ID / cnt
     */
    public Scenario getScenarioByCnt(String s) {
		Scenario ul= null;
		Iterator<Scenario> itr = scenarios.iterator();
		do{
			ul = itr.next();
		}while(!s.equals(ul.getCnt()));
		return ul;
    }
    
	/*
	 * helper method to replace a Scenario object
	 * once it has been updated or modified in 
	 * some way
	 */
	public void replaceScenario(Scenario u) {
		Scenario ul= null;
		int cnt = 0;
		try {
			Iterator<Scenario> itr = scenarios.iterator();
			while(itr.hasNext()) {
				ul = itr.next();
				if(u.getCnt().equals(ul.getCnt())) {
					scenarios.set(cnt, u);	
				}
				cnt++;
			}
		}catch(Exception e) {
			System.err.println("Inside ReplaceScenario :" +e.toString());
		}
		
	}
    
}
