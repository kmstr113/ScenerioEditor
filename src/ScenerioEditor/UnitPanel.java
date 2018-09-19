package ScenerioEditor;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;

public class UnitPanel extends JPanel {
	private JTextField uID = null;
	private JTextField chassisType = null;
	private JTextField model = null;
	private JTextField mType = null;
	private JTextField unitCommander = null;
	private JTextField cnt = null;
	private JTextArea Weapons = null;
	
	public UnitPanel() {
		super();
		JButton j = new JButton("TEST");	
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//accessable textfields
		cnt = new JTextField(10);
		uID = new JTextField(10);
		chassisType = new JTextField(10);
		model = new JTextField(10);
		mType = new JTextField(10);
		unitCommander = new JTextField(10);
		Weapons = new JTextArea(4,40);
		JScrollPane jsp = new JScrollPane(Weapons);
		
		//Weapons Panel
		JPanel jpw = new JPanel();
		jpw.setLayout(new GridLayout(0,2));
		jpw.add(new JLabel("Weapons"));
		jpw.add(jsp);
		
		
		//Counter
		JPanel jp0 = new JPanel();
		jp0.setLayout(new GridLayout(0,2));
		jp0.add(new JLabel("Count"));
		jp0.add(cnt);
		
		// unit ID
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(0,2));
		jp.add(new JLabel("Unit ID"));
		jp.add(uID);
		
		// Chassis Type
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(0,2));
		jp1.add(new JLabel("Chassis Type"));
		jp1.add(chassisType);
		
		//Model
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(0,2));
		jp2.add(new JLabel("Model"));
		jp2.add(model);
		
		//Mech type
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(0,2));
		jp3.add(new JLabel("Type"));
		jp3.add(mType);
		
		//unitCommander
		JPanel jp4 = new JPanel();
		jp4.setLayout(new GridLayout(0,2));
		jp4.add(new JLabel("Unit Commander"));
		jp4.add(unitCommander);		
		
		
		// add the Jpanels
		this.add(jp0);
		this.add(jp);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jpw);
		this.add(j);
		
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED ));
		
		this.setVisible(true);
	}
	
	public void setUID(String s) {
		uID.setText(s);
	}
	
	public void setChassisType(String s) {
		chassisType.setText(s);
	}
	
	public void setModel(String s) {
		model.setText(s);
	}
	
	public void setMType(String s) {
		mType.setText(s);
	}
	
	public void setunitCommander(String s) {
		unitCommander.setText(s);
	}	
	
	public void setCounter(String s) {
		cnt.setText(s);
	}
	
	public void addWeapon(String s) {
		Weapons.append(s+"\r\n");
	}
	
	public void LoadUnit(Unit u) {
		cnt.setText(u.getCnt());
		uID.setText(u.getUnitID());
		chassisType.setText(u.getChassisType());
		model.setText(u.getModel());
		mType.setText(u.getMType());
		unitCommander.setText(u.getUnitCommander());
		Weapons.append(u.getWeapons());
	}
}
