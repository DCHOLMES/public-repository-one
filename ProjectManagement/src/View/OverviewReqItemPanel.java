/* Author: Declan C Holmes
 * Description: Overview Requirement Item Panel. Panel holding requirement overview item details.*/
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Controller.ReqItemController;

public class OverviewReqItemPanel extends JPanel{

	private final Color GREEN = new Color(186, 245, 186);
	private final Color RED = new Color(247, 176, 186);
	private JPanel parent;
	private JPanel info = new JPanel(new GridLayout(1,2));
	private JPanel descPan = new JPanel(new GridLayout(1,1));
	private JLabel id = new JLabel();
	private JLabel title = new JLabel();
	private JLabel desc = new JLabel();
	private ButtonGroup radioGroup = new ButtonGroup();
	private JRadioButton activeRButton = new JRadioButton("Active");
	private JRadioButton cancelRButton = new JRadioButton("Cancelled");
	private JPanel radioPan = new JPanel();
	
	public OverviewReqItemPanel(JPanel parent, String id, String status, String desc) {
	
		this.parent = parent;

		//create JLabels
		this.id = new JLabel(id);
		this.title =  new JLabel(status);
		this.desc =  new JLabel(desc);
		
		//set main panel properties
		this.setPreferredSize(new Dimension(parent.getPreferredSize().width - 10, parent.getPreferredSize().height / 8));
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		//this.addMouseListener(new ReqItemController());
	
		//set radio panel properties and add radio buttons
		radioPan.setPreferredSize(new Dimension(this.getPreferredSize().width * 2 /10 - 10, this.getPreferredSize().height - 10));
		radioGroup.add(activeRButton);
		radioGroup.add(cancelRButton);
		radioPan.add(activeRButton);
		radioPan.add(cancelRButton);
		
		System.out.println("STATUS == " + status);
		
		//set color and default radio button
		if(status == "Active") {
			
			setColor(GREEN);
			activeRButton.setSelected(true);
		
		} else if(status == "Cancelled") {
			
			setColor(RED);
			cancelRButton.setSelected(true);
		}
		
		//set info panel properties and add labels
		info.setPreferredSize(new Dimension(this.getPreferredSize().width * 1 / 10 - 10 , this.getPreferredSize().height - 10));
		info.add(this.id);
		info.add(this.title);
		info.add(this.desc);
		
		//set description panel properties and add description label
		descPan.setPreferredSize(new Dimension(this.getPreferredSize().width * 7 / 10 - 10, this.getPreferredSize().height - 10));
		descPan.add(this.desc);
		
		//add sub-panels to main panel
		this.add(info);
		this.add(descPan);
		this.add(radioPan);
		
		this.setVisible(true);
	}
	
	//Set Color Method. Sets main panel and sub-panels to specified color.
	public void setColor(Color col) {
		
		this.setBackground(col);
		info.setBackground(col);
		descPan.setBackground(col);
		radioPan.setBackground(col);
		activeRButton.setBackground(col);
		cancelRButton.setBackground(col);
	}
}
