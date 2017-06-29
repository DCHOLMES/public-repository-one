package View;

import java.awt.Menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainToolBar extends JMenuBar {

	private JMenu fileMenu = new JMenu("File");
	
	public MainToolBar() {
		
		this.add(fileMenu);
	}
}
