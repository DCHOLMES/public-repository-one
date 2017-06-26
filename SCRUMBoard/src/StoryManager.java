/* Author: Declan Holmes
 * Description: StoryManager class. Stores and manages stories created by users.*/

import java.util.ArrayList;
import java.util.Arrays;


public class StoryManager {

	private static StoryManager storyMan = new StoryManager();
	private ArrayList<StoryInterface> stories = new ArrayList<StoryInterface>();
	
	/* private constructor */
	private StoryManager() {
		
	}
	
	public static StoryManager getInstance() {
		
		return storyMan;
	}
	
	/* CreateStory method implementation. Adds story to stories arraylist, provided
	 * the id input by user does not already exist.*/
	public boolean CreateStory(int id, String description) {
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == id) {
				
				System.err.println("Error: Story ID already in use.");
				System.exit(1);
				return false;
			}
		}
		
		stories.add(new StoryBasic(id, description));
		return true;
	}
	
	public StoryInterface[] ListStories() {
		
		return stories.toArray(new StoryInterface[stories.size()]);
	}
	
	/* DeleteStory method implementation. Removes story, provided
	 * the id exists within the stories arraylist. */
	public boolean DeleteStory(int id) {
		
		StoryInterface toDelete = null;
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == id) {
				
				toDelete = e;
			}
		}
		
		if(toDelete != null) {
			
			stories.remove(toDelete);
			return true;
		} else {
			
			System.err.println("Error: Story ID not found.");
			System.exit(1);
			return false;
		}
	}
	
	public boolean CompleteStory(int id) {
		
		boolean found = false;
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == id) {
				
				e.CompleteStory();
				found = true;
			}
		}
		
		if(found == false) {
			System.err.println("Error: Story ID not found.");
			System.exit(1);
		}
		
		return found;
	}
	
	
	public boolean CreateTask(int storyId, int id, String description) {
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == storyId) {
				
				StoryBasic temp = (StoryBasic) e;
				return temp.CreateTask(id, description);
			}
		}
		
		System.err.println("Error: Invalid story ID.");
		System.exit(1);
		return false;
	}


	public TaskInterface[] getTasks(int id) {
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == id) {
				
				return e.getTasks().toArray(new TaskInterface[e.getTasks().size()]);
			}
		}
		
		return null;
	}
	
	public boolean DeleteTask(int storyId, int id) {
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == storyId) {
				
				return e.DeleteTask(id);
			}
		}
		
		return false;
	}
	
	public boolean MoveTask(int storyId, int id, String column) {
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == storyId) {
				
				StoryBasic temp = (StoryBasic) e;
				return temp.MoveTask(id, column);
			}
		}
		
		System.err.println("Error: Invalid story ID.");
		System.exit(1);
		return false;
	}
	
	public boolean UpdateTask(int storyId, int id, String description) {
		
		for(StoryInterface e : stories) {
			
			if(e.getId() == storyId) {
				
				StoryBasic temp = (StoryBasic) e;
				return temp.UpdateTask(id, description);
			}
		}
		
		System.err.println("Error: Invalid story ID.");
		System.exit(1);
		return false;
	}
	
	public void LoadData(StoryInterface[] storyArray) {
		
		stories = new ArrayList<StoryInterface>(Arrays.asList(storyArray));
	}
}
