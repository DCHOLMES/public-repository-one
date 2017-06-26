/* Author: Declan Holmes
 * Description: SCRUMBoardDriver class. Singleton. Responsible for processing user commands
 * and calling their related method. Point at which data is saved and loaded for
 * the solution. Command methods originate here, and move down the tree of classes
 * to the actual point of implementation. */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SCRUMBoardDriver {

	/* Command constants for comparison to user input */
	private final String CREATE_STORY = "create story";
	private final String LIST_STORY = "list stories";
	private final String DEL_STORY = "delete story";
	private final String COMPLETE_STORY = "complete story";
	private final String CREATE_TASK = "create task";
	private final String LIST_TASK = "list tasks";
	private final String DEL_TASK = "delete task";
	private final String MOVE_TASK = "move task";
	private final String UPDATE_TASK = "update task";
	
	/* Command arg lengths for comparison to user input */
	private final int CREATE_STORY_NUM_ARGS = 3;
	private final int LIST_STORY_NUM_ARGS = 1;
	private final int DEL_STORY_NUM_ARGS = 2;
	private final int COMPLETE_STORY_NUM_ARGS = 2;
	private final int CREATE_TASK_NUM_ARGS = 4;
	private final int LIST_TASK_NUM_ARGS = 2;
	private final int DEL_TASK_NUM_ARGS = 3;
	private final int MOVE_TASK_NUM_ARGS = 4;
	private final int UPDATE_TASK_NUM_ARGS = 4;
	
	/* Other instance variables*/
	private static SCRUMBoardDriver drive = new SCRUMBoardDriver();
	private StoryManager storyMan = StoryManager.getInstance();
	private final String FILE = "SCRUM_DATA";
	
	/* Private constructor */
	private SCRUMBoardDriver() {
		
	}
	
	public static SCRUMBoardDriver getInstance() {
		
		return drive;
	}
	
	/* Go method with args[] signature. For use where arguments are provided at
	 * program start up. Attempts to execute provided command, saves data,
	 * then exits immediately*/
	public void Go(String args[]) {
		
		LoadData();
		
		try {
			
			if(args[0].equals(CREATE_STORY)) {
				
				if(args.length == CREATE_STORY_NUM_ARGS) {
					
					CreateStory(Integer.parseInt(args[1]), args[2]);
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + CREATE_STORY 
							+ "' requires " + CREATE_STORY_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(LIST_STORY)) {
				
				if(args.length == LIST_STORY_NUM_ARGS) {
					
					ListStories();
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + LIST_STORY 
							+ "' requires " + LIST_STORY_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(DEL_STORY)) {
				
				if(args.length == DEL_STORY_NUM_ARGS) {
					
					DeleteStory(Integer.parseInt(args[1]));
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + DEL_STORY 
							+ "' requires " + DEL_STORY_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(COMPLETE_STORY)) {
				
				if(args.length == COMPLETE_STORY_NUM_ARGS) {
					
					CompleteStory(Integer.parseInt(args[1]));
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + COMPLETE_STORY 
							+ "' requires " + COMPLETE_STORY_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(CREATE_TASK)) {
				
				if(args.length == CREATE_TASK_NUM_ARGS) {
					
					CreateTask(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + CREATE_TASK 
							+ "' requires " + CREATE_TASK_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(LIST_TASK)) {
				
				if(args.length == LIST_TASK_NUM_ARGS) {
					
					ListTasks(Integer.parseInt(args[1]));
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + LIST_TASK 
							+ "' requires " + LIST_TASK_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(DEL_TASK)) {
				
				if(args.length == DEL_TASK_NUM_ARGS) {
					
					DeleteTask(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + DEL_TASK 
							+ "' requires " + DEL_TASK_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(MOVE_TASK)) {
				
				if(args.length == MOVE_TASK_NUM_ARGS) {
					
					MoveTask(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + MOVE_TASK 
							+ "' requires " + MOVE_TASK_NUM_ARGS + ".");
					System.exit(1);
				}
			} else if (args[0].equals(UPDATE_TASK)) {
				
				if(args.length == UPDATE_TASK_NUM_ARGS) {
					
					UpdateTask(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
					
				} else {
					
					System.out.println("Error: Invalid number of arguments, command '" + UPDATE_TASK 
							+ "' requires " + UPDATE_TASK_NUM_ARGS + ".");
					System.exit(1);
				}
			} else {
				
				System.out.println("Error: Invalid command.");
				System.exit(1);
			}
			
		} catch (NumberFormatException e) {
			
			System.err.println("Error: Arguments such as id's must be numerical.");
			System.exit(1);
		} catch (Exception e) {
			
			System.err.println("Error: An unknown exception has occured.");
			System.exit(1);
		}
		
		SaveData();
		System.exit(0);
	}
	
	private boolean CreateStory(int id, String description) {
		
		return storyMan.CreateStory(id, description);
	}
	
	private void ListStories() {
		
		StoryInterface[] stories = storyMan.ListStories();
		
		for(int i = 0 ; i < stories.length ; i++) {
			
			System.out.println("ID: " + stories[i].getId() + " Description: " 
					+ stories[i].getDescription() + " Complete: " + stories[i].getComplete());
		}
	}
	
	private boolean DeleteStory(int id) {
		
		return storyMan.DeleteStory(id);
	}
	
	private boolean CompleteStory(int id) {
		
		return storyMan.CompleteStory(id);
	}
	
	private boolean CreateTask(int storyId, int id, String description) {
		
		return storyMan.CreateTask(storyId, id, description);
	}
	
	public void ListTasks(int id) {
		
		TaskInterface[] tasks = storyMan.getTasks(id);
		
		if(tasks != null) {
			for(int i = 0 ; i < tasks.length ; i++) {
				
				System.out.println(tasks[i].getStatus() + " - Task ID: " + tasks[i].getId() + " Description: " + tasks[i].getDescription());
			}
		} else {
			System.err.println("Error: Invalid story ID, or specified story contains zero tasks.");
			System.exit(1);
		}
	}
	
	private boolean DeleteTask(int storyId, int id) {
		
		return storyMan.DeleteTask(storyId, id);
	}
	
	private boolean MoveTask(int storyId, int id, String column) {
		
		return storyMan.MoveTask(storyId, id, column);
	}
	
	private boolean UpdateTask(int storyId, int id, String description) {
		
		return storyMan.UpdateTask(storyId, id, description);
	}
	
	/* SaveData method. Saves the story array object including all tasks,
	 * to file SCRUM_DATA.*/
	private void SaveData() {
		
		FileOutputStream fOut = null;
		ObjectOutputStream out = null;
		StoryInterface[] storyArray = storyMan.ListStories();
		
		try {
			
			fOut = new FileOutputStream(FILE);
			out = new ObjectOutputStream(fOut);
			
			out.writeObject(storyArray);
			out.flush();
			
			fOut.close();
			out.close();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("ERROR: Save file not found.");
			System.exit(1);
		} catch (IOException e) {
			
			System.out.println("ERROR: Input/Output exception.");
			System.exit(1);
		}
	}
	
	/* LoadData method. Loads the story array object including all tasks,
	 * to file SCRUM_DATA.*/
	private void LoadData() {
		
		FileInputStream fIn = null;
		File file = new File(FILE);
		ObjectInputStream in = null;
		StoryInterface[] storyArray = null;
	
		if(file.exists()) {
			try {
				
				fIn = new FileInputStream(file);
				in = new ObjectInputStream(fIn);
				
				storyArray = (StoryInterface[]) in.readObject();
				
				in.close();
				fIn.close();
				
			} catch (FileNotFoundException e) {
				
				System.out.println("Error: Save file not found.");
				System.exit(1);
			} catch (IOException e) {
				
				System.out.println("Error: Input/Output exception.");
				System.exit(1);
			} catch (ClassNotFoundException e) {
				
				System.out.println("Error: Class not found exception.");
				System.exit(1);
			}
			
			storyMan.LoadData(storyArray);
		}
	}
	
}
