/* Author: Declan C Holmes.
 * Description: New Project Listener controller class.
 * Controller for the new project window okay button. On button click, new project is created. */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ProjectManager;
import View.NewProjectWindow;

public class NewProjectListener implements ActionListener {

	//constants
	private final String DOC = "Document";
	private final String FORM = "Form";
	
	//variables
	private ProjectManager projMan;
	private NewProjectWindow win;
	
	//constructor
	public NewProjectListener(ProjectManager projMan, NewProjectWindow win) {
		
		this.projMan = projMan;
		this.win = win;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(win.getTypeValue().equals(DOC)) {
			
			projMan.newDocument(win.getTitle(), win.getDesc());
		
		} else if(win.getTypeValue().equals(FORM)) {
			
			projMan.newForm(win.getTitle(), win.getDesc());
			
		} else {
			
			System.out.println("ERROR");
		}
		
		win.dispose();
	}
}
