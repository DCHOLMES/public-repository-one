/* Author: Declan C Holmes
 * Description: Proj Tree Selection Listener class. Notifies observers on value change of tree. */

package Controller;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import Model.Document;
import Model.Form;
import Model.Project;
import Model.Requirement;

public class ProjTreeSelectionListener extends Observable implements TreeSelectionListener {

	// Project Observer Array
	private ArrayList<ProjectInformationObserver> obsProj = new ArrayList<ProjectInformationObserver>();
	
	// Requirements Observer Array
	private ArrayList<RequirementsPanelController> obsReq = new ArrayList<RequirementsPanelController>();
	
	// Project Tree Controller
	private ProjectsTreeController control;
	
	//constructor
	public ProjTreeSelectionListener(ProjectsTreeController control) {
		
		this.control = control;
	}
	
	/* Value changed method. Examines nodes to determine whether they are leaves or branches.
	 * differentiates between requirement nodes and project nodes. If a project is selected
	 * the generalized overview populates the requirement window, else if a requirement is selected
	 * the specific requirement view populates the requirement window.*/
	public void valueChanged(TreeSelectionEvent arg0) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) control.getTree().getLastSelectedPathComponent();
		
		if(node == null) {
			
			return;
		}
		
		Object nodeInfo = node.getUserObject();
		
		if(nodeInfo instanceof Form || nodeInfo instanceof Document) {
			
			Project proj = (Project) nodeInfo;
			notifyProjObservers(proj.getId(), proj.getTitle(), proj.getDesc());
			Requirement[] reqArr = null;
			
			if(!node.isLeaf()) {
				
				reqArr = new Requirement[node.getChildCount()];
				
				for(int i = 0 ; i < node.getChildCount() ; i++) {
					
					DefaultMutableTreeNode reqNode = (DefaultMutableTreeNode) node.getChildAt(i);
					Requirement req = (Requirement) reqNode.getUserObject();
					
					reqArr[i] = req;
				}
			}
			
			notifyReqObservers(false, proj, reqArr);
			
		} else if(nodeInfo instanceof Requirement) {
			
			//Requirement req = (Requirement) nodeInfo;
			DefaultMutableTreeNode projNode = (DefaultMutableTreeNode) node.getParent();
			Project proj = (Project) projNode.getUserObject();
			
			Requirement req = (Requirement) node.getUserObject();
			Requirement[] reqArr = {req};

			notifyProjObservers(proj.getId(), proj.getTitle(), proj.getDesc());
			notifyReqObservers(true, proj, reqArr);
		}
	}
	
	public void registerProjObserver(ProjectInformationObserver o) {
		
		obsProj.add(o);
	}
	
	public void removeObserver(ProjectInformationObserver o) {
		
		obsProj.remove(o);
		obsReq.remove(o);
	}
	
	private void notifyProjObservers(String id, String title, String desc) {
		
		for(ProjectInformationObserver o : obsProj) {
			
			o.update(id, title, desc);
		}
	}
	
	public void registerReqObserver(RequirementsPanelController obs) {
		
		obsReq.add(obs);
	}
	
	public void notifyReqObservers(boolean spec, Project proj, Requirement[] reqs) {
		
		for(RequirementsPanelController o : obsReq) {
			
			o.update(spec, proj, reqs);
		}
	}
}
