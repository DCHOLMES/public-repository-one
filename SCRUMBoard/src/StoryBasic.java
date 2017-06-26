/* Author: Declan Holmes
 * Description: StoryBasic class. Concrete implementation of a story, implemented
 * to specifically meet the requirements for this solution. Inherits from StoryAbstract */

public class StoryBasic extends StoryAbstract{

	public StoryBasic(int id, String description) {
		
		this.id = id;
		this.description = description;
	}

	public boolean CreateTask(int id, String description) {
		
		for(TaskInterface e : tasks) {
			
			if(e.getId() == id) {
				
				System.err.println("Error: Story ID already in use.");
				System.exit(1);
				return false;
			}
		}
		
		tasks.add(new TaskBasic(id, description));
		return true;
	}

	public boolean MoveTask(int id, String column) {
		
		for(TaskInterface e : tasks) {
			
			if(e.getId() == id) {
				
				TaskBasic temp = (TaskBasic) e;
				return temp.ProgressTask(column);
			}
		}
		
		System.err.println("Error: Task ID not found.");
		System.exit(1);
		return false;
	}
}
