/* Author: Declan C Holmes
 * Description: Requirements Panel Controller. Controls the changing of sub-panels in the
 * Requirements Panel. The sub-panel shown is dependent on the node type selected in the
 * project panel. */

package Controller;

import java.util.Observable;
import java.util.Observer;

import Model.Project;
import Model.ProjectManager;
import Model.Requirement;
import View.RequirementsPanel;

public class RequirementsPanelController implements Observer {

	private RequirementsPanel pan;
	
	public RequirementsPanelController(RequirementsPanel pan) {
		
		this.pan = pan;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	
	public void update(boolean spec, Project proj, Requirement[] reqs) {
		
		if(spec == true) {
			
			setSpecific(proj, reqs[0]);
			
		} else {
			
			setOverview(reqs);
		}
	}
	
	private void setOverview(Requirement[] reqs) {
		
		pan.removeSpec();
		pan.add(pan.getOverviewPan());
		pan.getOverviewPan().removeAll();
		
		if(reqs != null) {
			
			for(int i = 0 ; i < reqs.length ; i++) {
			
				pan.getOverviewPan().addRequirement(reqs[i].getId(), reqs[i].getStatus(), reqs[i].getDesc());
			}
		}
		
		pan.validate();
		
		pan.repaint();
	}
	
	public void setSpecific(String projId, ProjectManager projMan) {
		
		pan.removeSpec();
		pan.remove(pan.getOverviewPan());
		pan.add(pan.getSpecPanel(projId, projMan));
		
		pan.validate();
		
		pan.repaint();
	}
	
	public void setSpecific(Project proj, Requirement req) {
		
		pan.removeSpec();
		pan.remove(pan.getOverviewPan());
		pan.add(pan.getSpecPanel(proj.getId(), req.getId(), req.getDesc(), req.getStatus()));
		
		pan.validate();
		
		pan.repaint();
	}
}
