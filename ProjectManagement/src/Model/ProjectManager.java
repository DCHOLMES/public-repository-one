package Model;
/* Author: Declan C. Holmes
 * Project Manager Class. Provides the interface through which interaction
 * with underlying data encapsulation classes are created, modified and managed.
 * */

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;

import DBM.DBManager;

public class ProjectManager extends Observable {

	//constants
	private int RAN_MIN = 10000000;
	private int RAN_MAX = 99999999;
	
	//observers
	private ArrayList<Observer> obs = new ArrayList<Observer>();
	
	//variables
	private ArrayList<ProjectInterface> projects = new ArrayList<ProjectInterface>();
	private Scanner scan =  new Scanner(System.in);
	private String input = "";
	private Random ran = new Random();
	private DBManager db;
	
	
	//default constructor
	public ProjectManager(DBManager db) {
		
		this.db = db;
		projects = db.loadProjects();
		
		for(ProjectInterface p : projects) {
			
			ArrayList<RequirementInterface> reqs = db.loadRequirements(p.getId());
			
			for(RequirementInterface r : reqs) {
				
				p.addRequirement(r);
			}
		}
	}
	
	public void go() {
		
		/*Document doc = this.newDocument("PROJECT 1", "test");
		Form myform = this.newForm("PROJECT 2", "test");
		
		this.addRequirement(myform.getId(), "43433", "This is test requirement number 1");
		this.addRequirement(myform.getId(), "12345", "This is test requirement number 2");
		this.addRequirement(myform.getId(), "65433", "This is test requirement number 3");
		this.addRequirement(myform.getId(), "64345", "This is test requirement number 4");
		this.addRequirement(doc.getId(), "123", "mydesc");
		
		myform.getReqs()[2].cancel();*/
	} 
	
	
	//New Document method. Creates a new document and adds it to the array list 
	public Document newDocument(String title, String desc) {
		
		Document doc = new Document(genProjId(), title, desc);
		
		projects.add(doc);
		
		notifyObservers();
		
		db.saveProjToDB(doc);
		
		return doc;
	}
	
	
	//New Form method. Creates a new form and adds it to the array list 
	public Form newForm(String title, String desc) {
		
		Form form = new Form(genProjId(), title, desc);
		
		projects.add(form);
		
		db.saveProjToDB(form);
		
		return form;
	}
	
	
	//Add requirement method. Adds a requirement to a project record
	public void addRequirement(String projId, String desc, String status) {
		
		RequirementInterface req = new Requirement(genReqId(), desc, status);
		
		ProjectInterface proj = getProject(projId);
		
		proj.addRequirement(req);
		
		db.saveReqToDB(projId, req);
		
		this.notifyObservers();
	}
	
	
	//Get all projects method. Returns an array of Projects.
	public ProjectInterface[] getAllProjects() {
		
		ProjectInterface[] projs = new ProjectInterface[projects.size()];
				
		projects.toArray(projs);
		
		return projs;
	}
	
	
	//Get project method. Returns a project with an id matching the id supplied.
	public ProjectInterface getProject(String id) {
		
		for(ProjectInterface p : projects) {
			
			if(p.getId().equals(id)) {
				
				return p;
			}
		}
		
		return null;
	}
	
	
	//Complete project method. Sets the status of a project to complete
	private void completeProject(String projId) {
		
		getProject(projId).complete();
	}
	
	
	//Hold project method. Set the status of a project to hold.
	private void holdProject(String projId) {
		
		getProject(projId).hold();
	}
	
	
	//Cancel project method. Sets the status of a project to cancelled.
	private void cancelProject(String projId) {
		
		getProject(projId).cancel();
	}
	
	
	//Activate project. Sets the status of a project to active.
	private void activateProject(String projId) {
		
		getProject(projId).activate();
	}
	
	
	//Gen Id method. Generates an Id for a project, checks for existing id in previously created projects.
	private String genProjId() {
		
		int ranNum;
		String ranStr = "";
		boolean idExists = false;
		
		ranNum = ran.nextInt((RAN_MAX - RAN_MIN) + 1) + RAN_MIN;
		ranStr = Integer.toString(ranNum);
		
		for(ProjectInterface p : projects) {
			
			if(p.getId().equals(ranStr)) {
				
				idExists = true;
			}
		}
		
		if(idExists == true) {
			
			return genProjId();
		}
		
		return ranStr;
	}
	
	public String genReqId() {
		
		int ranNum;
		String ranStr = "";
		boolean idExists = false;
		
		ranNum = ran.nextInt((RAN_MAX - RAN_MIN) + 1) + RAN_MIN;
		ranStr = Integer.toString(ranNum);
		
		for(ProjectInterface p : projects) {
			
			for(int i = 0 ; i < p.getReqs().length ; i++) {
				
				if(ranStr.equals(p.getReqs()[i])) {
					
					idExists = true;
				}
			}
		}
		
		if(idExists == true) {
			
			return genReqId();
		}
		
		return ranStr;
	}
	
	public void registerObserver(Observer ob) {
		
		obs.add(ob);
	}
	
	public void removeObserver(Observer ob) {
		
		obs.remove(ob);
	}
	
	public void notifyObservers() {
		
		for(Observer o : obs) {
			
			o.update(this, getAllProjects());
		}
	}
}
