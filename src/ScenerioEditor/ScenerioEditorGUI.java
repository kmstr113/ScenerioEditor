package ScenerioEditor;
import javax.swing.*;
import java.awt.*;

public class ScenerioEditorGUI extends JFrame{
	private UnitPanel up = null;
	
	public ScenerioEditorGUI(String s) {
		super(s);
		this.setLayout(new BorderLayout());
		this.setSize(640, 480);
		this.setVisible(true);
		up = new UnitPanel();
		this.getContentPane().add(up,BorderLayout.CENTER);
		this.validate();
		up.setUID("TEST");
	}
	
	public UnitPanel getUnitPanel() {
		return up;
	}
}
