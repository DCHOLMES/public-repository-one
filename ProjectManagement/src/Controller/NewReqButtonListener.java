package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Model.ProjectManager;
import View.ProjectInformationPanel;

public class NewReqButtonListener implements ActionListener {
	
	private ProjectManager projMan;
	private RequirementsPanelController reqCon;
	private ProjectInformationPanel parent;
	
	public NewReqButtonListener(ProjectManager projMan, RequirementsPanelController reqCon, ProjectInformationPanel parent) {
		
		this.parent = parent;
		this.projMan = projMan;
		this.reqCon = reqCon;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		reqCon.setSpecific(parent.getId(), projMan);
	}
}
