/* Author: Declan Holmes
 * Description: StoryAbstract class. Provides functionality that should exist across
 * all stories deriving from this class. */

import java.io.Serializable;
import java.util.ArrayList;


public abstract class StoryAbstract implements StoryInterface, Serializable{

	protected final String TO_DO = "To Do";
	protected final String IN_PROC = "In Process";
	protected final String TO_VER = "To Verify";
	protected final String DONE = "Done";
	protected ArrayList<TaskInterface> tasks = new ArrayList<TaskInterface>();
	protected int id;
	protected String description;
	protected boolean complete = false;
	
	public int getId() {
		
		return id;
	}
	
	public ArrayList<TaskInterface> getTasks() {
		
		ArrayList<TaskInterface> retList = new ArrayList<TaskInterface>();
		
		for(TaskInterface e : tasks) {
			
			if(e.getStatus().equals(TO_DO)) {
				
				retList.add(e);
				
			}
		}
		
		for(TaskInterface e : tasks) {
			
			if(e.getStatus().equals(IN_PROC)) {
				
				retList.add(e);
			}
		}
		
		for(TaskInterface e : tasks) {
			
			if(e.getStatus().equals(TO_VER)) {
				
				retList.add(e);
			}
		}
		
		for(TaskInterface e : tasks) {
			
			if(e.getStatus().equals(DONE)) {
				
				retList.add(e);
			}
		}
		
		return retList;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public boolean getComplete() {
		
		return complete;
	}
	
	public boolean DeleteTask(int id) {
		
		TaskInterface toDelete = null;
		
		for(TaskInterface e : tasks) {
			
			if(e.getId() == id) {
				
				toDelete = e;
			}
		}
		
		if(toDelete != null) {
			
			tasks.remove(toDelete);
			return true;
		} else {
			
			System.err.println("Error: Task ID not found.");
			System.exit(1);
			return false;
		}
	}
	
	public boolean UpdateTask(int id, String description) {
		
		for(TaskInterface e : tasks) {
			
			if(e.getId() == id) {
				
				TaskBasic temp = (TaskBasic) e;
				temp.Update(description);
				return true;
			}
		}
		
		System.err.println("Error: Task ID not found.");
		System.exit(1);
		return false;
	}

	public boolean CompleteStory() {
		
		for(TaskInterface e : tasks) {
			
			if(!e.IsDone()) {
				
				return false;
			}
		}
		
		complete = true;
		return true;
	}
}
