package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ContentPanel extends JInternalFrame {

	private JPanel mainPan = new JPanel();
	
	public ContentPanel(ProjectInformationPanel infoPan, RequirementsPanel reqPan) {
		
		this.setLocation(355, 0);
		mainPan.setBackground(new Color(149, 221, 245));
		mainPan.setPreferredSize(new Dimension(700, 1000));
		this.resizable = false;
		mainPan.setLayout(new GridBagLayout());
		
		addInfoPanel(infoPan);
		addReqPanel(reqPan);
		
		this.add(mainPan);
		
		this.setVisible(true);
	}
	
	public void addInfoPanel(ProjectInformationPanel infoPan) {
		
		GridBagConstraints con = new GridBagConstraints();
		
		con.gridx = 0;
		con.gridy = 0;
		con.weighty = 1.0;
		con.anchor = GridBagConstraints.FIRST_LINE_START;
		con.fill = GridBagConstraints.HORIZONTAL;
		
		mainPan.add(infoPan, con);
	}
	
	private void addReqPanel(RequirementsPanel reqPan) {
		
		GridBagConstraints con = new GridBagConstraints();
		
		con.gridx = 0;
		con.gridy = 1;
		con.weighty = 1.0;
		con.anchor = GridBagConstraints.LINE_START;
		con.fill = GridBagConstraints.HORIZONTAL;
		con.insets = new Insets(0,0,10,0);
		
		mainPan.add(reqPan, con);
	}
}
