package Model;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import DBM.DBManager;
import DocGenModel.DocGenerator;
import DocGenModel.DocGeneratorPOI;
import View.ProjectScreen;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBManager dbMan = new DBManager();
		ProjectManager manage = new ProjectManager(dbMan);
		manage.go();
		ProjectScreen frame = new ProjectScreen(manage);
	}
}
