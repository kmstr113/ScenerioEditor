package ScenerioEditor;
import javax.swing.*;
import java.awt.*;

public class ScenerioEditorGUI extends JFrame{
	private UnitPanel up = null;
	private ScenarioPanel sp = null;
	private ScenerioEditor scenerioeditor = null;
	private JTabbedPane JTP = null;
	
	public ScenerioEditorGUI(String s, ScenerioEditor se) {
		super(s);
		this.setLayout(new BorderLayout());
		this.setSize(640, 480);
		JTP = new JTabbedPane();
		scenerioeditor = se;
		
		//add tabbed Pane to Frame
		this.add(JTP,BorderLayout.CENTER);
		
		//Create Unit Tab add to Tabbed Pane
		up = new UnitPanel(this);
		JTP.addTab("Units", up);
		
		//Create the Scenario Panel add to tabbed pane
		sp = new ScenarioPanel(this);
		JScrollPane jsp = new JScrollPane(sp);
		JTP.addTab("Scenarios", jsp);
		
		
		this.setVisible(true);
		this.validate();
		up.setUID("TEST");
	}
	
	public UnitPanel getUnitPanel() {
		return up;
	}
	
	public ScenarioPanel getScenarioPanel() {
		return sp;
	}
	
	public ScenerioEditor getScenerioEditor() {
		return scenerioeditor;
	}
}
