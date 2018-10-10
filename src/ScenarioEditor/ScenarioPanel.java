package ScenarioEditor;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class ScenarioPanel extends JPanel{
	private ScenarioEditorGUI SEG = null;
	private JTextField id = null;
//	private JTextField type = null;
	private JTextField name = null;
	private JTextField desc = null;
	private JTextField status = null;
	private JTextField date = null;
	private JTextField attacker = null;
	private JTextField lanceForceId = null;
	private JTextField lanceRole = null;
	private JTextField terrainType = null;
	private JTextField light = null;
	private JTextField weather = null;
	private JTextField wind = null;
	private JTextField fog = null;
	private JTextField atmosphere = null;
	private JTextField gravity = null;
	private JTextField start = null;
	private JTextField deploymentDelay = null;
	private JTextField mapSize = null;
	private JTextField map = null;
	private JTextField lanceCount = null;
	private JTextField rerollsRemaining = null;
	private JTextArea botForceStub = null;
	private JComboBox type = null;
	
	
	
	
	public ScenarioPanel(ScenarioEditorGUI se) {
		super();
		SEG = se;
		JButton n = new JButton("Next");
		JButton p = new JButton("Prev");
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		// build all the elements
		id = new JTextField(10);
		type = buildType();
		name = new JTextField(10);
		desc = new JTextField(10);
		status = new JTextField(10);
		date =  new JTextField(10);
		attacker = new JTextField(10);
		lanceForceId = new JTextField(10);
		lanceRole = new JTextField(10);
		terrainType = new JTextField(10);
		light = new JTextField(10);
		weather = new JTextField(10);
		wind = new JTextField(10);
		fog = new JTextField(10);
		atmosphere = new JTextField(10);
		gravity = new JTextField(10);
		start = new JTextField(10);
		deploymentDelay = new JTextField(10);
		mapSize = new JTextField(10);
		map = new JTextField(10);
		lanceCount = new JTextField(10);
		rerollsRemaining = new JTextField(10);
		botForceStub = new JTextArea(5,30);
		
		JScrollPane jsp = new JScrollPane(botForceStub);
		
		/*
		 * Build all the JPanels for the display of
		 * the Scenario
		 */
		
		//ID
		JPanel jp0 = new JPanel();
		jp0.setLayout(new GridLayout(0,2));
		jp0.add(new JLabel("ID"));
		id.setEnabled(false);
		id.setDisabledTextColor(Color.black);
		jp0.add(id);
		
		//type
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(0,2));
		jp1.add(new JLabel("Type"));
		//type.setEnabled(false);
		//type.setDisabledTextColor(Color.black);
		jp1.add(type);
		
		//name
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(0,2));
		jp2.add(new JLabel("Name"));
		name.setEnabled(false);
		name.setDisabledTextColor(Color.black);
		jp2.add(name);
		
		//desc
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(0,2));
		jp3.add(new JLabel("Desc"));
		desc.setEnabled(false);
		desc.setDisabledTextColor(Color.black);
		jp3.add(desc);
		
		//status
		JPanel jp4 = new JPanel();
		jp4.setLayout(new GridLayout(0,2));
		jp4.add(new JLabel("Status"));
		status.setEnabled(false);
		status.setDisabledTextColor(Color.black);
		jp4.add(status);
		
		//date
		JPanel jp5 = new JPanel();
		jp5.setLayout(new GridLayout(0,2));
		jp5.add(new JLabel("Date"));
		date.setEnabled(false);
		date.setDisabledTextColor(Color.black);
		jp5.add(date);
		
		//attacker
		JPanel jp6 = new JPanel();
		jp6.setLayout(new GridLayout(0,2));
		jp6.add(new JLabel("Attacker"));
		attacker.setEnabled(false);
		attacker.setDisabledTextColor(Color.black);
		jp6.add(attacker);
		
		//lanceForceId
		JPanel jp7 = new JPanel();
		jp7.setLayout(new GridLayout(0,2));
		jp7.add(new JLabel("lanceForceId"));
		lanceForceId.setEnabled(false);
		lanceForceId.setDisabledTextColor(Color.black);
		jp7.add(lanceForceId);
		
		//lanceRole
		JPanel jp8 = new JPanel();
		jp8.setLayout(new GridLayout(0,2));
		jp8.add(new JLabel("lanceRole"));
		lanceRole.setEnabled(false);
		lanceRole.setDisabledTextColor(Color.black);
		jp8.add(lanceRole);
		
		//terrainType
		JPanel jp9 = new JPanel();
		jp9.setLayout(new GridLayout(0,2));
		jp9.add(new JLabel("terrainType"));
		terrainType.setEnabled(false);
		terrainType.setDisabledTextColor(Color.black);
		jp9.add(terrainType);
		
		//light
		JPanel jp10 = new JPanel();
		jp10.setLayout(new GridLayout(0,2));
		jp10.add(new JLabel("light"));
		light.setEnabled(false);
		light.setDisabledTextColor(Color.black);
		jp10.add(light);
		
		//weather
		JPanel jp11 = new JPanel();
		jp11.setLayout(new GridLayout(0,2));
		jp11.add(new JLabel("Weather"));
		weather.setEnabled(false);
		weather.setDisabledTextColor(Color.black);
		jp11.add(weather);
		
		//wind	
		JPanel jp12 = new JPanel();
		jp12.setLayout(new GridLayout(0,2));
		jp12.add(new JLabel("Wind"));
		wind.setEnabled(false);
		wind.setDisabledTextColor(Color.black);
		jp12.add(wind);
		
		//fog	
		JPanel jp13 = new JPanel();
		jp13.setLayout(new GridLayout(0,2));
		jp13.add(new JLabel("Fog"));
		fog.setEnabled(false);
		fog.setDisabledTextColor(Color.black);
		jp13.add(fog);
		
		//atmosphere	
		JPanel jp14 = new JPanel();
		jp14.setLayout(new GridLayout(0,2));
		jp14.add(new JLabel("Atmosphere"));
		atmosphere.setEnabled(false);
		atmosphere.setDisabledTextColor(Color.black);
		jp14.add(atmosphere);

		//gravity	
		JPanel jp15 = new JPanel();
		jp15.setLayout(new GridLayout(0,2));
		jp15.add(new JLabel("Gravity"));
		gravity.setEnabled(false);
		gravity.setDisabledTextColor(Color.black);
		jp15.add(gravity);

		//start	
		JPanel jp16 = new JPanel();
		jp16.setLayout(new GridLayout(0,2));
		jp16.add(new JLabel("Start"));
		start.setEnabled(false);
		start.setDisabledTextColor(Color.black);
		jp16.add(start);
		
		//deploymentDelay	
		JPanel jp17 = new JPanel();
		jp17.setLayout(new GridLayout(0,2));
		jp17.add(new JLabel("deploymentDelay"));
		deploymentDelay.setEnabled(false);
		deploymentDelay.setDisabledTextColor(Color.black);
		jp17.add(deploymentDelay);
				

		//mapSize	
		JPanel jp18 = new JPanel();
		jp18.setLayout(new GridLayout(0,2));
		jp18.add(new JLabel("mapSize"));
		mapSize.setEnabled(false);
		mapSize.setDisabledTextColor(Color.black);
		jp18.add(mapSize);		

		//map	
		JPanel jp19 = new JPanel();
		jp19.setLayout(new GridLayout(0,2));
		jp19.add(new JLabel("map"));
		map.setEnabled(false);
		map.setDisabledTextColor(Color.black);
		jp19.add(map);	
		
		//lanceCount	
		JPanel jp20 = new JPanel();
		jp20.setLayout(new GridLayout(0,2));
		jp20.add(new JLabel("lanceCount"));
		lanceCount.setEnabled(false);
		lanceCount.setDisabledTextColor(Color.black);
		jp20.add(lanceCount);		

		//rerollsRemaining	
		JPanel jp21 = new JPanel();
		jp21.setLayout(new GridLayout(0,2));
		jp21.add(new JLabel("rerollsRemaining"));
		rerollsRemaining.setEnabled(false);
		rerollsRemaining.setDisabledTextColor(Color.black);
		jp21.add(rerollsRemaining);			

		//botForceStub JScrollPane	
		JPanel jp22 = new JPanel();
		jp22.setLayout(new GridLayout(0,2));
		jp22.add(new JLabel("BotForceStub"));
		botForceStub.setEnabled(false);
		botForceStub.setDisabledTextColor(Color.black);
		jp22.add(jsp);	
		
		//Button Panel
		JPanel jp23 = new JPanel();
		jp23.setLayout(new GridLayout(0,2));
		jp23.add(p);
		jp23.add(n);
		
		
		/*
		 * Add all the Panels to the Display
		 */
		this.add(jp0);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);
		this.add(jp9);
		this.add(jp10);
		this.add(jp11);
		this.add(jp12);
		this.add(jp13);
		this.add(jp14);
		this.add(jp15);
		this.add(jp16);
		this.add(jp17);
		this.add(jp18);
		this.add(jp19);
		this.add(jp20);
		this.add(jp21);
		this.add(jp22);
		this.add(jp23);
		
		this.setVisible(true);
		/*
		 *  Add the Action listeners for the buttons
		 */
		n.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = new Integer(id.getText()).intValue() + 1;
				if (x > SEG.getScenarioEditor().getMaximumScenarioID()) {
					x = 1;
				}
				SEG.getScenarioPanel().loadScenario(SEG.getScenarioEditor().getScenarioByID(Integer.toString(x)));
			}
		});
		
		p.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = new Integer(id.getText()).intValue() - 1;
				if (x < 1 ) {
					x =  SEG.getScenarioEditor().getMaximumScenarioID();
				}
				SEG.getScenarioPanel().loadScenario(SEG.getScenarioEditor().getScenarioByID(Integer.toString(x)));
			}
		});		
		
	}
	
	public void setId(String s) {
		id.setText(s);
	}
	
	public void setType(String s) {
		type.setSelectedItem(s);
	}
	
	public void setNName(String s) {
		name.setText(s);
	}
	
	public void setDesc(String s) {
		desc.setText(s);
	}
	
	public void setStatus(String s) {
		status.setText(s);
	}
	
	public void setDate(String s) {
		date.setText(s);
	}
	
	public void setAttacker(String s) {
		attacker.setText(s);
	}
	
	public void setLanceForceId(String s) {
		lanceForceId.setText(s);
	}
	
	public void setLanceRole(String s) {
		lanceRole.setText(s);
	}
	
	public void setTerrainType(String s) {
		terrainType.setText(s);
	}
	
	public  void setLight(String s) {
		light.setText(s);
	}
	
	public void setWeather(String s) {
		weather.setText(s);
	}
	
	public void setWind(String s) {
		wind.setText(s);
	}
	
	public void setFog(String s) {
		fog.setText(s);
	}
	
	public void setAtmosphere(String s) {
		atmosphere.setText(s);
	}
	
	public void setGravity(String s) {
		gravity.setText(s);
	}
	
	public void setStart(String s) {
		start.setText(s);
	}
	
	public void setDeploymentDelay(String s) {
		deploymentDelay.setText(s);
	}
	
	public void setMapSize(String s) {
		mapSize.setText(s);
	}
	
	public void setMap(String s) {
		map.setText(s);
	}
	
	public void setLanceCount(String s) {
		lanceCount.setText(s);
	}
	
	public void setRerollsRemaining(String s) {
		rerollsRemaining.setText(s);
	}
	
	public void setBotForceStub(String s) {
		botForceStub.append(s);
	}

	public void clearBotForceStub() {
		botForceStub.setText("");
	}
	
	private JComboBox buildType() {
		JComboBox jcb = new JComboBox();
		
		return jcb;
	}
	
	public void loadScenario(Scenario s) {
		id.setText(s.getID());
		type.setSelectedItem(s.getType());
		name.setText(s.getName());
		desc.setText(s.getDesc());
		status.setText(s.getStatus());
		date.setText(s.getDate());
		attacker.setText(s.getAttackers());
		lanceForceId.setText(s.getLanceForceId());
		lanceRole.setText(s.getLanceRole());
		terrainType.setText(s.getTerrainType());
		light.setText(s.getLight());
		weather.setText(s.getWeather());
		wind.setText(s.getWind());
		fog.setText(s.getFog());
		atmosphere.setText(s.getAtmosphere());
		gravity.setText(s.getGravity());
		start.setText(s.getStart());
		deploymentDelay.setText(s.getDeploymentDelay());
		mapSize.setText(s.getMapSize());
		map.setText(s.getMap());
		lanceCount.setText(s.getLanceCount());
		rerollsRemaining.setText(s.getRerollsRemaining());
	}
	
	
	
	public ScenarioEditorGUI getScenarioEditorGUI() {
		return SEG;
	}
	
	
}
