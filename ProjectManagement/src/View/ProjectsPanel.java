package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JInternalFrame;

import Controller.ProjectInformationObserver;
import Controller.ProjectsTreeController;
import Controller.RequirementsPanelController;
import Model.ProjectManager;

public class ProjectsPanel extends JInternalFrame {

	private ProjectsTreeController projTree;
	private ProjectManager projMan;
	private ProjPanToolBar tool;
	int taskbarSize;
	
	public ProjectsPanel(ProjectManager projMan, ProjectsTreeController projTree) {
		
		this.projMan = projMan;
		this.projTree = projTree;
		
		tool = new ProjPanToolBar(this.projMan, projTree);
		projTree.updateTree();
		
		this.setLayout(new BorderLayout());
		this.setTitle("Project Navigator");
		this.setResizable(true);
		this.setBackground(Color.white);
		this.setSize(350, 100);
		
		this.add(tool, BorderLayout.NORTH);
		this.add(projTree.getTree());
		
		this.setVisible(true);
	}
	
	public void updateView() {
		
		this.add(projTree.getTree());
		
		this.repaint();
	}
}
