package ScenerioEditor;



public class Unit {
	private String unitID = "";
	private String chassisType = "";
	private String model = "";
	private String mType = "";
	private String unitCommander = "";
	private String cnt = "";
	private String Weapons = "";
	
	public Unit() {
		
	}
	
	public void setUnitID(String s) {
		unitID = s;
	}
	
	public String getUnitID() {
		return unitID;
	}
	
	public void setChassisType(String s) {
		chassisType = s;
	}

	public String getChassisType() {
		return chassisType;
	}
	
	public void setModel(String s) {
		model = s;
	}
	
	public String getModel() {
		return model;
	}

	public void setMType(String s) {
		mType = s;
	}
	
	public String getMType() {
		return mType;
	}
	
	public void setUnitCommander(String s) {
		unitCommander = s;
	}
	
	public String getUnitCommander() {
		return unitCommander;
	}
	
	public void setCnt(String s) {
		cnt = s;
	}
	
	public String getCnt() {
		return cnt;
	}
	
	public void setWeapons(String s) {
		Weapons = Weapons + s+ "\r\n";
	}
	
	public String getWeapons() {
		return Weapons;
	}
	
	
}
