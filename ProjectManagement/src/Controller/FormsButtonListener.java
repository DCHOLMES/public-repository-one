package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormsButtonListener implements ActionListener {

	private ProjectsTreeController tree;
	
	public FormsButtonListener(ProjectsTreeController tree) {
		
		this.tree = tree;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		tree.showForms();
	}
}
