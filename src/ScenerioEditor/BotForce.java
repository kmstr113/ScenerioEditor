package ScenerioEditor;

import java.util.Vector;

public class BotForce {
	private Vector<BotEntity> botEntities = new Vector<BotEntity>(4,2);
	private String id = "";
	private String name = "";
	
	
	/*
	 * Constructor
	 */
	public BotForce() {
		
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String s) {
		id = s;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	public void addBotEntity(BotEntity be) {
		botEntities.add(be);
	}

}
