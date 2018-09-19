package ScenerioEditor;

import java.util.Vector;

public class Scenario {
	private String cnt = "";
	private String type = "";
	private String name = "";
	private String desc = "";
	private String report = "";
	private String status = "";
	private String id = "";
	private String date = "";
	private String attackers = "";
	private String lanceForceId = "";
	private String lanceRole = "";
	private String terrainType = "";
	private String light = "";
	private String weather = "";
	private String wind = "";
	private String fog = "";
	private String atmosphere = "";
	private String gravity = "";
	private String start = "";
	private String deploymentDelay = "";
	private String mapSize = "";
	private String map = "";
	private String lanceCount = "";
	private String rerollsRemaining = "";
	private Vector<BotForce> botForce = new Vector<BotForce>(4,2);
	
	/*
	 * Constructor
	 */
	public Scenario() {
		
	}
	
	public void setCnt(String s) {
		cnt = s;
	}
	
	public String getCnt() {
		return cnt;
	}

	public void setType(String s) {
		type = s;
	}
	
	public String getType() {
		return type;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDesc(String s) {
		desc = s;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setReport(String s) {
		report = s;
	}
	
	public String getReport() {
		return report;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setID(String s) {
		id = s;
	}
	
	public String getID() {
		return id;
	}
	
	public void setDate(String s) {
		date = s;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setAttackers(String s) {
		attackers = s;
	}
	
	public String getAttackers() {
		return attackers;
	}
	
	public void setLanceForceId(String s) {
		lanceForceId = s;
	}
	
	public String getLanceForceId() {
		return lanceForceId;
	}
	
	public void setLanceRole(String s) {
		lanceRole = s;
	}
	
	public String getLanceRole() {
		return lanceRole;
	}
	
	public void setTerrainType(String s) {
		terrainType = s;
	}
	
	public String getTerrainType() {
		return terrainType;
	}
	
	public void setLight(String s) {
		light = s;
	}
	
	public String getLight() {
		return light;
	}
	
	public void setWeather(String s) {
		weather = s;
	}

	public String getWeather() {
		return weather;
	}
	
	public void setWind(String s) {
		wind = s;
	}
	
	public String getWind() {
		return wind;
	}

	public void setFog(String s) {
		fog = s;
	}
	
	public String getFog() {
		return fog;
	}
	
	public void setAtmosphere(String s) {
		atmosphere = s;
	}
	
	public String getAtmosphere() {
		return atmosphere;
	}
	
	public void setGravity(String s) {
		gravity = s;
	}
	
	public String getGravity() {
		return gravity;
	}
	
	public void setStart(String s) {
		start = s;
	}
	
	public String getStart() {
		return start;
	}
	
	public void setDeploymentDelay(String s) {
		deploymentDelay = s;
	}
	
	public String getDeploymentDelay() {
		return deploymentDelay;
	}
	
	public void setMapSize(String s) {
		mapSize = s;
	}
	
	public String getMapSize() {
		return mapSize;
	}
	
	public void setMap(String s) {
		map = s;
	}
	
	public String getMap() {
		return map;
	}
	
	public void setLanceCount(String s) {
		lanceCount = s;
	}
	
	public String getLanceCount() {
		return lanceCount;
	}
	
	public void setRerollsRemaining(String s) {
		rerollsRemaining = s;
	}
	
	public String getRerollsRemaining() {
		return rerollsRemaining;
	}
	
	public void addBotForce(BotForce b) {
		botForce.add(b);
	}
	
}
