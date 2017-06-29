package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ProjectManager;
import View.ReqSpecificPanel;

public class ReqSaveButtonListener implements ActionListener {

	private ProjectManager projMan;
	private ReqSpecificPanel parent;
	
	public ReqSaveButtonListener(ProjectManager projMan, ReqSpecificPanel parent) {
		
		this.projMan = projMan;
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String id = parent.getId();
		String desc = parent.getDesc();
		
		if(id.equals("")) {
			
			projMan.addRequirement(parent.getProjId(), desc, parent.getStatus());
			
		} else {
			
			projMan.getProject(parent.getProjId()).getReqById(id).setDesc(desc);
		}
	}
}
