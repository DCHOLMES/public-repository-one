package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ProjectManager;
import View.NewProjectWindow;

public class AddButtonListener implements ActionListener {
	
	private NewProjectWindow newWin;
	private ProjectManager projMan;
	
	public AddButtonListener(ProjectManager projMan) {
		
		this.projMan = projMan;
		newWin = new NewProjectWindow(projMan);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		newWin.setVisible(true);
	}
}
