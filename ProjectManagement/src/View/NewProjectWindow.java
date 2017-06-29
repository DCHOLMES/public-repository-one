/* Author: Declan C Holmes
 * Description: NewProjectWindow view class. Displays input fields 
 * for the creation of new projects.*/

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import Controller.NewProjectListener;
import Model.ProjectManager;

public class NewProjectWindow extends JFrame {
	
	//constants
	private final String OK_CONST = "OK";
	private final String CAN_CONST = "Cancel";
	private final String DOC = "Document";
	private final String FORM = "Form";
	private final String[] TYPE_LIST = {DOC, FORM};
	
	//project manager variable
	private ProjectManager projMan;
	
	//component variables
	private JLabel titleLab = new JLabel("Title");
	private JTextField titleField = new JTextField();
	private JLabel typeLab = new JLabel("Type");
	private JComboBox<String> typeList = new JComboBox<String>(TYPE_LIST);
	private JLabel descLab = new JLabel("Description");
	private JTextArea descField = new JTextArea();
	private JScrollPane scroll = new JScrollPane();
	private JButton ok = new JButton();
	private JButton cancel = new JButton();
	
	//constructor
	public NewProjectWindow(ProjectManager projMan) {
		
		this.projMan = projMan;
		
		// set window properties
		this.setMinimumSize(new Dimension(600, 450));
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// set component preferred sizes
		titleLab.setPreferredSize(new Dimension(50,30));
		titleField.setPreferredSize(new Dimension(150, 30));
		descLab.setPreferredSize(new Dimension(50,50));
		scroll.setPreferredSize(new Dimension(150, 150));
		ok.setPreferredSize(new Dimension(100,40));
		cancel.setPreferredSize(new Dimension(100,40));
		typeList.setPreferredSize(new Dimension(150, 30));
		
		// set button properties
		ok.setText(OK_CONST);
		cancel.setText(CAN_CONST);
		ok.addActionListener(new NewProjectListener(projMan, this));

		// set description text field properties
		descField.setLineWrap(true);
		descField.setWrapStyleWord(true);
		
		// set type list drop down properties
		typeList.setBackground(Color.WHITE);
		
		// add components to window
		addTitle();
		
		addTypeList();
		
		addDesc();
		
		addButtons();
	}
	
	/* add title method. Adds the title input components to the frame.*/
	private void addTitle() {
		
		JPanel group = new JPanel();
		GridBagConstraints conLab = new GridBagConstraints();
		GridBagConstraints conDesc = new GridBagConstraints();
		GridBagConstraints conGroup = new GridBagConstraints();
		
		// set group properties
		group.setLayout(new GridBagLayout());
		group.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		// set constraints and add title label
		conLab.gridx = 0;
		conLab.gridy = 0;
		conLab.anchor = GridBagConstraints.FIRST_LINE_START;
		conLab.weightx = 1.0;
		conLab.insets = new Insets(10,10,10,10);
		
		group.add(titleLab, conLab);
		
		// set constraints and add title text field
		conDesc.gridx = 1;
		conDesc.gridy = 0;
		conDesc.anchor = GridBagConstraints.FIRST_LINE_END;
		conDesc.weightx = 1.0;
		conDesc.fill = GridBagConstraints.HORIZONTAL;
		conDesc.insets = new Insets(10,10,10,10);
		
		group.add(titleField, conDesc);
		
		// set constraints and add group to main window
		conGroup.gridx = 0;
		conGroup.gridy = 0;
		conGroup.anchor = GridBagConstraints.FIRST_LINE_START;
		conGroup.weightx = 1.0;
		conGroup.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(group, conGroup);
	}
	
	/* add type list method. Adds the type list components to the frame. */
	private void addTypeList() {
		
		JPanel group = new JPanel();
		GridBagConstraints conGroup = new GridBagConstraints();
		GridBagConstraints conLab = new GridBagConstraints();
		GridBagConstraints conField = new GridBagConstraints();
		
		// set group properties
		group.setLayout(new GridBagLayout());
		group.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		// set constraints and add type label
		conLab.gridx = 0;
		conLab.gridy = 0;
		conLab.anchor = GridBagConstraints.LINE_START;
		conLab.weightx = 1.0;
		conLab.insets = new Insets(10,10,10,10);
		
		group.add(typeLab, conLab);
		
		// set constraints and add type drop down field
		conField.gridx = 1;
		conField.gridy = 0;
		conField.anchor = GridBagConstraints.LINE_END;
		conField.weightx = 1.0;
		conField.fill = GridBagConstraints.HORIZONTAL;
		conField.insets = new Insets(10,10,10,10);
		
		group.add(typeList, conField);
		
		// set constraints and add group to main window
		conGroup.gridx = 0;
		conGroup.gridy = 1;
		conGroup.anchor = GridBagConstraints.LINE_START;
		conGroup.weightx = 1.0;
		conGroup.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(group, conGroup);
	}
	
	/* add description method. Adds the description components to the frame.*/
	private void addDesc() {
		
		JPanel group = new JPanel();
		GridBagConstraints conGroup = new GridBagConstraints();
		GridBagConstraints conLab = new GridBagConstraints();
		GridBagConstraints conField = new GridBagConstraints();
		
		// set group properties
		group.setLayout(new GridBagLayout());
		group.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		// set constraints and add description label
		conLab.gridx = 0;
		conLab.gridy = 0;
		conLab.anchor = GridBagConstraints.LINE_START;
		conLab.fill = GridBagConstraints.HORIZONTAL;
		conLab.weightx = 1.0;
		conLab.insets = new Insets(10,10,10,10);
		
		group.add(descLab, conLab);
		
		// set constraints and add description text area
		conField.gridx = 0;
		conField.gridy = 1;
		conField.weightx = 1.0;
		conField.gridwidth = 3;
		conField.anchor = GridBagConstraints.LINE_START;
		conField.fill = GridBagConstraints.BOTH;
		conField.insets = new Insets(0,10,10,10);
		
		scroll = new JScrollPane(descField);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(150, 150));
		
		group.add(scroll, conField);
		
		// set constraints and add group to main window
		conGroup.gridx = 0;
		conGroup.gridy = 2;
		conGroup.anchor = GridBagConstraints.FIRST_LINE_START;
		conGroup.weightx = 1.0;
		conGroup.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(group, conGroup);
	}
	
	/* add buttons method. Adds the buttons to the frame.*/
	private void addButtons() {
		
		JPanel group = new JPanel();
		GridBagConstraints conGroup = new GridBagConstraints();
		GridBagConstraints conOk = new GridBagConstraints();
		GridBagConstraints conCan = new GridBagConstraints();
		
		// set group properties
		group.setLayout(new GridBagLayout());
		
		conOk.gridx = 0;
		conOk.gridy = 0;
		conOk.weightx = 1.0;
		conOk.gridwidth = 1;
		conOk.anchor = GridBagConstraints.LAST_LINE_END;
		conOk.insets = new Insets(10,10,10,10);
		
		group.add(ok, conOk);
		
		// set group properties
		group.setLayout(new GridBagLayout());
		
		conCan.gridx = 1;
		conCan.gridy = 0;
		conCan.weightx = 1.0;
		conCan.gridwidth = 1;
		conCan.anchor = GridBagConstraints.LAST_LINE_END;
		conCan.insets = new Insets(10,10,10,10);
		
		group.add(cancel, conCan);
		
		// set constraints and add group to main window
		conGroup.gridx = 0;
		conGroup.gridy = 3;
		conGroup.anchor = GridBagConstraints.LAST_LINE_END;
		conGroup.weightx = 1.0;
		
		this.add(group, conGroup);
	}
	
	// get title method. Returns the input text in the field titlefield.
	public String getTitle() {
		
		return titleField.getText();
	}
	
	// get description method. Returns the input text in the field descfield.
	public String getDesc() {
		
		return descField.getText();
	}
	
	public String getTypeValue() {
		
		return typeList.getSelectedItem().toString();
	}
}
