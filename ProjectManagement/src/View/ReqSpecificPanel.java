package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import Controller.ReqSaveButtonListener;
import Model.ProjectManager;

public class ReqSpecificPanel extends JPanel {

	// variables
	private String projId;
	private ProjectManager projMan;
	
	// constants
	private final Color GREEN = new Color(186, 245, 186);
	private final String SAVE = "Save";
	private final String CANCEL = "Cancel";
	private final String ACTIVE = "Active";
	private final String CANCELLED = "Cancelled";
	private final String DESCRIPTION = "Description";
	
	// components
	private ButtonGroup bGroup = new ButtonGroup();
	private JRadioButton activeR = new JRadioButton(ACTIVE);
	private JRadioButton cancelledR = new JRadioButton(CANCELLED);
	private FlowLayout flow = new FlowLayout();
	private JLabel idLab = new JLabel("12345");
	private Font idFont = idLab.getFont();
	private JLabel desc = new JLabel(DESCRIPTION);
	private JPanel buttonPan = new JPanel();
	private JTextArea descText = new JTextArea();
	private JButton saveButton = new JButton(SAVE);
	private JButton cancelButton = new JButton(CANCEL);
	
	// panels
	private JPanel idPan = new JPanel();
	private JPanel radioPan = new JPanel();
	private JPanel descPan = new JPanel();
	private JPanel parent;
	
	public ReqSpecificPanel(ProjectManager projMan, String projId, JPanel parent) {
		
		this.projMan = projMan;
		this.parent = parent;
		this.projId = projId;
		
		idLab.setText("");
		descText.setText("");
		
		setup();
	}
	
	public ReqSpecificPanel(ProjectManager projMan, JPanel parent, String projId, String id, String desc, String status) {
		
		this.projMan = projMan;
		this.parent = parent;
		this.projId = projId;

		idLab.setText(id);
		descText.setText(desc);
		
		setup();
	}
	
	public ReqSpecificPanel(ProjectManager projMan, JPanel parent) {
		// TODO Auto-generated constructor stub
		
		this.projMan = projMan;
		this.parent = parent;
		
		idLab.setText("");
		descText.setText("");
		
		setup();
	}

	private void setup() {
		
		this.setLayout(flow);

		//set sizes for panels
		this.setPreferredSize(new Dimension(parent.getPreferredSize().width, parent.getPreferredSize().height));
		idPan.setPreferredSize(new Dimension(this.getPreferredSize().width * 1 / 2 - 10, this.getPreferredSize().height * 2 / 10 - 10));
		radioPan.setPreferredSize(new Dimension(this.getPreferredSize().width * 1 / 2 - 10, this.getPreferredSize().height * 2 / 10 - 10));
		descPan.setPreferredSize(new Dimension(this.getPreferredSize().width - 15, this.getPreferredSize().height * 7 / 10 - 10));
		buttonPan.setPreferredSize(new Dimension(this.getPreferredSize().width - 15, this.getPreferredSize().height * 1 / 10 - 10));
		descText.setPreferredSize(new Dimension(descPan.getPreferredSize().width * 99 / 100, descPan.getPreferredSize().height * 75 /100));

		//set id font
		idLab.setFont(new Font(idFont.getName(), Font.PLAIN, 20));
		
		//set text area properties
		descText.setLineWrap(true);
		descText.setWrapStyleWord(true);
		
		//set color
		this.setBackground(GREEN);
		idPan.setBackground(GREEN);
		radioPan.setBackground(GREEN);
		descPan.setBackground(GREEN);
		buttonPan.setBackground(GREEN);
		activeR.setBackground(GREEN);
		cancelledR.setBackground(GREEN);
		
		//set borders
		idPan.setBorder(BorderFactory.createLoweredBevelBorder());
		radioPan.setBorder(BorderFactory.createLoweredBevelBorder());
		descText.setBorder(BorderFactory.createLoweredBevelBorder());
		
		idPan.setLayout(new FlowLayout(FlowLayout.LEADING));
		radioPan.setLayout(new FlowLayout(FlowLayout.TRAILING));
		descPan.setLayout(new FlowLayout(FlowLayout.LEADING));
		buttonPan.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		bGroup.add(activeR);
		bGroup.add(cancelledR);
		
		radioPan.add(activeR);
		radioPan.add(cancelledR);
		
		descPan.add(desc);
		descPan.add(descText);
		
		saveButton.addActionListener(new ReqSaveButtonListener(projMan, this));
		buttonPan.add(saveButton);
		buttonPan.add(cancelButton);
		
		idPan.add(idLab);
		
		// add sub-panels to main panel
		this.add(idPan);
		this.add(radioPan);
		this.add(descPan);
		this.add(buttonPan);
		
		this.setVisible(true);
	}

	public String getId() {
		
		return idLab.getText();
	}
	
	public String getDesc() {
		
		return descText.getText();
	}
	
	public String getProjId() {
		
		return projId;
	}
	
	public String getStatus() {
		
		if(activeR.isSelected()) {
			
			return ACTIVE;
		
		} else {
			
			return CANCELLED;
		}
	}
}
