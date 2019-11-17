package com.MekHQ.ScenarioEdit;


import javax.swing.*;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import java.awt.*;

public class ScenerioEditorGUI extends JFrame{
	static final long serialVersionUID = 9882272;
	private InfoPanel ip = null;
	private JPanel jp = null;
	private JTabbedPane jtpScenarios = null;
	private JTabbedPane jtpUnits = null;
	
	public ScenerioEditorGUI(String s) {
		super(s);
		this.setLayout(new BorderLayout());
		this.setSize(800, 600);
		ip = new InfoPanel();
		jtpScenarios = new JTabbedPane();
		jtpUnits = new JTabbedPane();
		
		//sp = new ScenarioPanel();
		// Build Panel for layout of multiple panels
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
		jp.add(ip);
		jp.add(jtpUnits);
		jp.add(jtpScenarios);
	
		
		this.getContentPane().add(jp,BorderLayout.CENTER);
		//this.getContentPane().add(ip,BorderLayout.CENTER);
		this.validate();
		this.setVisible(true);
	}
	
	public JTabbedPane getTabbedUnitPanel() {
		return jtpUnits;
	}
	
	public InfoPanel getInfoPanel() {
		return ip;
	}
	
	public JTabbedPane getTabbedScenarioPanel() {
		return jtpScenarios;
	}
}
