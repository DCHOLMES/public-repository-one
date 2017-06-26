/* Author: Declan Holmes
 * Description: StoryInterface class. Declares those methods necessary for a story object. */
import java.util.ArrayList;


public interface StoryInterface {
	
	abstract ArrayList<TaskInterface> getTasks();
	abstract int getId();
	abstract String getDescription();
	abstract boolean DeleteTask(int id);
	abstract boolean CompleteStory();
	abstract boolean getComplete();
}
