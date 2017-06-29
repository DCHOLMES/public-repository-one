package Controller;

import java.util.Observable;
import java.util.Observer;

import View.ProjectInformationPanel;

public class ProjectInformationObserver implements Observer {

	private ProjectInformationPanel pan;
	
	public ProjectInformationObserver(ProjectInformationPanel pan) {
		
		this.pan = pan;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(String id, String title, String desc) {
		
		pan.setId(id);
		pan.setTitle(title);
		pan.setDesc(desc);
		
		if(id.equals("")) {
			
			pan.showFields(false);
		
		} else {
			
			pan.showFields(true);
		}
	}
}
