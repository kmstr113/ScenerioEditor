package com.MekHQ.ScenarioEdit;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;

public class UnitPanel extends JPanel {
	private JTextField uID = null;
	private JTextField chassisType = null;
	static final long serialVersionUID = 127898812;
	
	public UnitPanel() {
		super();
		JButton j = new JButton("UnitPanel");	
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		uID = new JTextField(10);
		chassisType = new JTextField(10);
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(0,2));
		jp.add(new JLabel("Unit ID"));
		jp.add(uID);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(0,2));
		jp1.add(new JLabel("Chassis Type"));
		jp1.add(chassisType);
		
		
		this.add(jp);
		this.add(jp1);
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
	
}
