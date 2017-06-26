/* Author: Declan Holmes
 * Description: TaskAbstract class. Provides basic implementation of methods 
 * that are likley to exist for all Task classes. */

import java.io.Serializable;

public abstract class TaskAbstract implements TaskInterface, Serializable {

	protected int id;
	protected String description;
	protected String status;

	public int getId() {

		return id;
	}

	public String getDescription() {
		
		return description;
	}
	
	public void Update(String description) {
		
		this.description = description;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public boolean IsDone() {
		
		if(status.equals(DONE)) {
			
			return true;
			
		} else {
			
			return false;
		}
	}
}
