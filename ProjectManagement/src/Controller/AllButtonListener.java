package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ProjectManager;

public class AllButtonListener implements ActionListener {

	private ProjectManager projMan;
	private ProjectsTreeController tree;
	
	public AllButtonListener(ProjectManager projMan, ProjectsTreeController tree) {
		
		this.projMan = projMan;
		this.tree = tree;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		tree.showAll();
	}
}
