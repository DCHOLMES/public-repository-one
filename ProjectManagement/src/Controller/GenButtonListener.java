package Controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

import DocGenModel.DocGeneratorPOI;

public class GenButtonListener implements ActionListener{

	private DocGeneratorPOI gen = new DocGeneratorPOI();
	private JLabel id;
	
	public GenButtonListener(JLabel id) {
		
		this.id = id;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		gen.createDoc(id.getText());
		
		try {
			
			Desktop.getDesktop().open(new File("POITest.docx"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
