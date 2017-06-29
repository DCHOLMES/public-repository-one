package View;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JPanel;

import Controller.AddButtonListener;
import Controller.AllButtonListener;
import Controller.DocsButtonListener;
import Controller.FormsButtonListener;
import Controller.ProjectsTreeController;
import Controller.RemButtonListener;
import Model.ProjectManager;

public class ProjPanToolBar extends JPanel {

	private Button addBut = new Button("+");
	private Button remBut = new Button("-");
	private Button formBut = new Button("Forms");
	private Button docBut = new Button("Docs");
	private Button allBut = new Button("All");
	private ProjectManager projMan;
	private ProjectsTreeController treeCon;
	
	public ProjPanToolBar(ProjectManager projMan, ProjectsTreeController treeCon) {
		
		this.projMan = projMan;
		this.treeCon = treeCon;
		
		this.setLayout(new BorderLayout());
		
		JPanel butPan = new JPanel();
		JPanel fdPan = new JPanel();
		
		addBut.addActionListener(new AddButtonListener(projMan));
		remBut.addActionListener(new RemButtonListener());
		allBut.addActionListener(new AllButtonListener(projMan, treeCon));
		docBut.addActionListener(new DocsButtonListener(projMan, treeCon));
		formBut.addActionListener(new FormsButtonListener(treeCon));
		
		fdPan.add(formBut);
		fdPan.add(docBut);
		fdPan.add(allBut);
		butPan.add(addBut);
		butPan.add(remBut);
		this.add(fdPan, BorderLayout.WEST);
		this.add(butPan, BorderLayout.EAST);
	}
}
