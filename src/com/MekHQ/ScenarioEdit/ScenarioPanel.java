/**
 * 
 */
package com.MekHQ.ScenarioEdit;

import java.awt.GridLayout;
import java.lang.Integer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * @author jhahn
 *
 */
public class ScenarioPanel extends JPanel {
	static final long serialVersionUID = 67867700;

	private JTextField name = null;
	private JTextField Desc = null;
	private JTextField Report = null;
	private JTextField Status = null;
	private JTextField ID = null;
	private JTextField calDate = null;
	
	/**
	 * 
	 */
	public ScenarioPanel() {
		super();
		JButton j = new JButton("ScenarioPanel");	
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		name = new JTextField(10);
		Desc = new JTextField(10);
		Report = new JTextField(10);
		Status = new JTextField(10);
		ID  = new JTextField(10);
		calDate =  new JTextField(25);
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(0,2));
		jp.add(new JLabel("Sceanrio Name"));
		jp.add(name);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(0,2));
		jp1.add(new JLabel("Description"));
		jp1.add(Desc);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(0,2));
		jp2.add(new JLabel("Report"));
		jp2.add(Report);
		
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(0,2));
		jp3.add(new JLabel("Status"));
		jp3.add(Status);

		JPanel jp4 = new JPanel();
		jp4.setLayout(new GridLayout(0,2));
		jp4.add(new JLabel("ID"));
		jp4.add(ID);
		
		JPanel jp5 = new JPanel();
		jp5.setLayout(new GridLayout(0,2));
		jp5.add(new JLabel("Date"));
		jp5.add(calDate);
		
		this.add(jp4);
		this.add(jp5);
		this.add(jp);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(j);
		
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED ));
		
		this.setVisible(true);
	}
	
	public void setname(String s) {
		name.setText(s);
		name.setEditable(false);
	}
	
	public void setDesc(String s) {
		Desc.setText(s);
		Desc.setEditable(false);
	}
	
	public void setReport(String s) {
		Report.setText(s);
		Report.setEditable(false);
	}
	
	public void setStatus(String s) {
		if(new Integer(s).intValue() == 1) {
			Status.setText("Completed");
		}else if(new Integer(s).intValue() == 0) {
			Status.setText("Active");
		}else {
			Status.setText(s);
		}
		Status.setEditable(false);
	}

	public void setID(String s) {
		ID.setText(s);
		ID.setEditable(false);
	}
	
	public void setcalDate(String s) {
		calDate.setText(s);
		calDate.setEditable(false);
	}
	

}
