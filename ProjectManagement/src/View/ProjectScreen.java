/* Author: Declan C. Holmes
 * Project Screen Class. The entry screen for the ProjectManagment program.
 */

package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.ProjTreeSelectionListener;
import Controller.ProjectInformationObserver;
import Controller.ProjectsTreeController;
import Controller.RequirementsPanelController;
import Model.ProjectManager;

public class ProjectScreen extends JFrame{

	private ProjectManager projMan;
	private Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = scrSize.width;
	private int height = scrSize.height;
	
	// Sub-screens
	private ContentPanel contentPan;
	private ProjectsPanel projectPan;
	
	// Content panel sub-screens
	private ProjectInformationPanel infoPan;
	private RequirementsPanel reqPan;
	
	// Components
	private MainToolBar menu = new MainToolBar();
	
	// Controllers/Observers
	private ProjectsTreeController treeCon;
	private ProjectInformationObserver projInfoObs;
	private ProjTreeSelectionListener treeLis;
	private RequirementsPanelController reqCon;
	
	
	public ProjectScreen(ProjectManager projMan) {
		
		this.projMan = projMan;
		reqPan = new RequirementsPanel(projMan);
		
		// initialize main panels and tree controls
		createProjHierarchyPanel();
		createContentPanel();
		
		menu = new MainToolBar();
		
		//Set window properties
		this.setSize(width / 2, height / 2);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		// add components
		this.setJMenuBar(menu);
		this.add(projectPan);
		this.add(contentPan);
		this.add(new JPanel());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		// set sub-frame properties
		projectPan.setSize(350, height - 70);
		contentPan.setSize(1560, 1010);
	}
	
	private void createProjHierarchyPanel() {
		
		// initialize tree controller, project hierarchy panel
		treeCon = new ProjectsTreeController(projMan, projectPan);
		projectPan = new ProjectsPanel(projMan, treeCon);
	}
	
	private void createContentPanel() {
		
		// initialize the project information observer and requirements panel controller
		reqCon = new RequirementsPanelController(reqPan);
		infoPan = new ProjectInformationPanel(projMan, reqCon);
		projInfoObs = new ProjectInformationObserver(infoPan);
		
		// initialize content panel
		contentPan = new ContentPanel(infoPan, reqPan);
		
		infoPan.setPreferredSize(new Dimension(contentPan.getPreferredSize().width, contentPan.getPreferredSize().height / 4));
		
		// register project information panel as an observer of the tree selection listener
		treeCon.getSelectionListener().registerProjObserver(projInfoObs);
		treeCon.getSelectionListener().registerReqObserver(reqCon);
	}
}
