/* Author: Declan C Holmes
 * Description: Requirements Panel. Houses either the Requirement Specific Panel, or the 
 * Requirement Overview Panel depending on the node type selected in the Projects tree. */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import Model.ProjectManager;

public class RequirementsPanel extends JPanel{

	private ReqSpecificPanel spec;
	private ReqOverviewPanel over;
	private ProjectManager projMan;
	
	public RequirementsPanel(ProjectManager projMan) {
		
		this.projMan = projMan;
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(1475, 700));
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new GridLayout());
		
		over = new ReqOverviewPanel(this);
		spec = new ReqSpecificPanel(projMan, this);
		
		this.setVisible(true);
	}
	
	public ReqSpecificPanel getSpecPanel(String projId, ProjectManager projMan) {
		
		spec = new ReqSpecificPanel(projMan, projId, this);
		
		return spec;
	}
	
	public ReqSpecificPanel getSpecPanel(String projId, String id, String desc, String status) {
		
		spec = new ReqSpecificPanel(projMan, this, projId, id, desc, status);
		
		return spec;
	}
	
	public ReqOverviewPanel getOverviewPan() {
		
		return over;
	}
	
	public void removeSpec() {
		
		this.remove(spec);
	}
}
