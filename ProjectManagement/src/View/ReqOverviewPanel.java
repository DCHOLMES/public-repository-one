package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ReqOverviewPanel extends JPanel {
	
	private JPanel parent;
	
	public ReqOverviewPanel(JPanel parent) {
		
		this.parent = parent;
		
		this.setPreferredSize(new Dimension( parent.getPreferredSize().width, parent.getPreferredSize().height));
		
		System.out.println(parent.getHeight());
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout());
		
		this.setVisible(true);
	}
	
	public void addRequirement(String id, String status, String desc) {
		
		this.add(new OverviewReqItemPanel(this, id, status, desc));
	}
}
