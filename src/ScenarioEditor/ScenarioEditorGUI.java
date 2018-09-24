package ScenarioEditor;
import javax.swing.*;
import java.awt.*;

public class ScenarioEditorGUI extends JFrame{
	private UnitPanel up = null;
	private ScenarioPanel sp = null;
	private ScenarioEditor scenerioeditor = null;
	private JTabbedPane JTP = null;
	
	public ScenarioEditorGUI(String s, ScenarioEditor se) {
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
	
	public ScenarioEditor getScenarioEditor() {
		return scenerioeditor;
	}
}
