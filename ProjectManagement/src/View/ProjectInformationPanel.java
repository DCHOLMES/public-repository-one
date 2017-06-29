package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.GenButtonListener;
import Controller.NewReqButtonListener;
import Controller.RequirementsPanelController;
import Model.ProjectManager;

public class ProjectInformationPanel extends JPanel {

	private final String DESC = "Description";
	
	private JLabel id = new JLabel();
	private JLabel title = new JLabel();
	private JLabel descLab = new JLabel(DESC);
	private JLabel desc = new JLabel();
	private Font idFont = id.getFont();
	private Font titleFont = title.getFont();
	private JButton newReqButton = new JButton("New Requirement");
	private JButton genButton = new JButton("Generate Document");
	private FlowLayout flow = new FlowLayout();

	
	public ProjectInformationPanel(ProjectManager projMan, RequirementsPanelController reqCon) {
		
		this.setPreferredSize(new Dimension(1500, 250));
		this.setBackground(new Color(149, 221, 245));
				
		flow.setVgap(25);
		this.setLayout(flow);
		flow.setAlignment(FlowLayout.LEFT);
		
		id.setFont(new Font(idFont.getName(), Font.PLAIN, 30));
		id.setPreferredSize(new Dimension(200, 50));
		title.setFont(new Font(titleFont.getName(), Font.PLAIN, 30));
		title.setPreferredSize(new Dimension(500, 50));

		newReqButton.addActionListener(new NewReqButtonListener(projMan, reqCon, this));
		addTitle();
		addButtons();
		
		showFields(false);
		this.setVisible(true);
	}
	
	private void addTitle() {
		
		JPanel group = new JPanel();
		group.setPreferredSize(new Dimension(this.getPreferredSize().width, this.getPreferredSize().height * 6/10));
		group.setBackground(new Color(149, 221, 245));
		FlowLayout groupLayout = new FlowLayout();
		groupLayout.setAlignment(FlowLayout.LEFT);
		group.setLayout(groupLayout);
		
		group.add(id);
		group.add(title);
		group.add(desc);
		this.add(group);
	}
	
	
	private void addButtons() {
		
		JPanel group = new JPanel();
		group.setPreferredSize(new Dimension(this.getPreferredSize().width, this.getPreferredSize().height * 4/10));
		group.setBackground(new Color(149, 221, 245));
		FlowLayout groupLayout = new FlowLayout();
		groupLayout.setAlignment(FlowLayout.LEFT);
		group.setLayout(groupLayout);
		
		genButton.addActionListener(new GenButtonListener(id));
		
		group.add(newReqButton);
		group.add(genButton);
		this.add(group);
	}
	
	public void setId(String id) {
		
		this.id.setText(id);
	}
	
	public void setTitle(String title) {
		
		this.title.setText(title);
	}
	
	public void setDesc(String desc) {
		
		this.desc.setText(desc);
	}
	
	public void showFields(Boolean vis) {
		
		descLab.setVisible(vis);
	}
	
	public String getId() {
		
		return id.getText();
	}
}
