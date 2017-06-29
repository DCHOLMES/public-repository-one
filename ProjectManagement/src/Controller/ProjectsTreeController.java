/* Author: Declan C Holmes
 * Description: Projects Tree Controller class.
 * Creates a tree of projects and requirements that is displayed in the Projects Panel. 
 * 
 * Provides methods to populate the tree with either; All projects, form projects or document projects.
 * 
 * This class is an observer to the Project Manager model class, as such the project tree will be updated
 * upon a change to the backend projects contained within the project manager class. */ 
package Controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Model.Document;
import Model.Form;
import Model.ProjectInterface;
import Model.ProjectManager;
import Model.RequirementInterface;
import View.ProjectsPanel;

public class ProjectsTreeController implements Observer {

	// variables
	private ProjectManager projMan;
	private ProjectsPanel projPan;
	private JTree tree;
	private DefaultMutableTreeNode projNode = null;
	private DefaultMutableTreeNode top = new DefaultMutableTreeNode("Projects");
	private ProjTreeSelectionListener treeControl = new ProjTreeSelectionListener(this);
	
	/* constructor. passes registers project information observer to the tree selection listener. 
	 * Registers itself as an observer to the project manager object.
	 * Creates new JTree.*/
	public ProjectsTreeController(ProjectManager projMan, ProjectsPanel projPan) {
		
		this.projMan = projMan;
		this.projPan = projPan;
		tree = new JTree(top);
		projMan.registerObserver(this);

		tree.addTreeSelectionListener(treeControl);
	}
	
	/* update tree method. Begins by removing all children of the root node, then receives the set
	 * of all projects from the project manager object. These projects are then added to the tree
	 * as nodes.*/
	public void updateTree() {
		
		top.removeAllChildren();
		ProjectInterface projs[] = projMan.getAllProjects();
		
		for(int i = 0 ; i < projs.length ; i++) {
			
			RequirementInterface req[] = projs[i].getReqs();
			projNode = new DefaultMutableTreeNode(projs[i]);
						
			for(int y = 0 ; y < req.length ; y++) {
				
				DefaultMutableTreeNode reqNode = new DefaultMutableTreeNode(req[y]);
				
				projNode.add(reqNode);
			}
			
			top.add(projNode);
		}
	}
	
	// accessor
	public JTree getTree() {
		 
		return tree;
	}
	
	// updates the view.
	public void updateView() {
		
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		model.reload(top);
	}
	
	// updates the tree to contain all projects.
	public void showAll() {
	
		updateTree();
		updateView();
	}
	
	// updates the tree to show only document projects
	public void showDocs() {
		
		ProjectInterface projs[] = projMan.getAllProjects();
		top.removeAllChildren();
		
		for(int i = 0 ; i < projs.length ; i++) {
			
			if(projs[i] instanceof Document) {
				
				RequirementInterface req[] = projs[i].getReqs();
				projNode = new DefaultMutableTreeNode(projs[i]);
						
				for(int y = 0 ; y < req.length ; y++) {
				
					DefaultMutableTreeNode reqNode = new DefaultMutableTreeNode(req[i]);
				
					projNode.add(reqNode);
				}
			
				top.add(projNode);
			}
		}
		
		updateView();
	}
	
	// updates the tree to only show form objects
	public void showForms() {
		
		ProjectInterface projs[] = projMan.getAllProjects();
		top.removeAllChildren();
		
		for(int i = 0 ; i < projs.length ; i++) {
			
			if(projs[i] instanceof Form) {
				
				RequirementInterface req[] = projs[i].getReqs();
				projNode = new DefaultMutableTreeNode(projs[i]);
						
				for(int y = 0 ; y < req.length ; y++) {
				
					DefaultMutableTreeNode reqNode = new DefaultMutableTreeNode(req[i]);
				
					projNode.add(reqNode);
				}
			
				top.add(projNode);
			}
		}
		
		updateView();
	}

	// observer update method.
	@Override
	public void update(Observable arg0, Object arg1) {
		
		updateTree();
		updateView();
	}
	
	public ProjTreeSelectionListener getSelectionListener() {
		
		return treeControl;
	}
}
