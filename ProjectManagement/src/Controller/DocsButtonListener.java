package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ProjectManager;

public class DocsButtonListener implements ActionListener {

	private ProjectManager projMan;
	private ProjectsTreeController tree;
	
	public DocsButtonListener(ProjectManager projMan, ProjectsTreeController tree) {
		
		this.projMan = projMan;
		this.tree = tree;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		tree.showDocs();
	}
}
