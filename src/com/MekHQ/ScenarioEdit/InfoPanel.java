package com.MekHQ.ScenarioEdit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class InfoPanel extends JPanel {
	static final long serialVersionUID =  67867699;
	private JTextField name = null;
	private JTextField faction = null;
	private JTextField pctFemale = null;
	private JTextField astechPool = null;
	private JTextField medicPool = null;
	private JTextField calDate = null;
 
	public InfoPanel() {
		super();
		JButton j = new JButton("InfoPanel");	
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		name = new JTextField(10);
		faction = new JTextField(10);
		pctFemale = new JTextField(10);
		astechPool = new JTextField(10);
		medicPool  = new JTextField(10);
		calDate =  new JTextField(25);
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(0,2));
		jp.add(new JLabel("Unit Name"));
		jp.add(name);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(0,2));
		jp1.add(new JLabel("Faction"));
		jp1.add(faction);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(0,2));
		jp2.add(new JLabel("% Female"));
		jp2.add(pctFemale);
		
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(0,2));
		jp3.add(new JLabel("AstroTech Pool"));
		jp3.add(astechPool);

		JPanel jp4 = new JPanel();
		jp4.setLayout(new GridLayout(0,2));
		jp4.add(new JLabel("Medic Pool"));
		jp4.add(medicPool);
		
		JPanel jp5 = new JPanel();
		jp5.setLayout(new GridLayout(0,2));
		jp5.add(new JLabel("Date"));
		jp5.add(calDate);
		
		this.add(jp5);
		this.add(jp);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(j);
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED ));
		
		this.setVisible(true);
	}
	
	public void setname(String s) {
		name.setText(s);
		name.setEditable(false);
	}
	
	public void setfaction(String s) {
		faction.setText(s);
		faction.setEditable(false);
	}
	
	public void setPctFemale(String s) {
		pctFemale.setText(s);
		pctFemale.setEditable(false);
	}
	
	public void setastechPool(String s) {
		astechPool.setText(s);
		astechPool.setEditable(false);
	}

	public void setmedicPool(String s) {
		medicPool.setText(s);
		medicPool.setEditable(false);
	}
	
	public void setcalDate(String s) {
		calDate.setText(s);
		calDate.setEditable(false);
	}
}
