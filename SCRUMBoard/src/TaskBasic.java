/* Author: Declan Holmes
 * Description: TaskBasic class. Concrete implementation of a story, implemented
 * to specifically meet the requirements for this solution. Inherits from TaskAbstract */

public class TaskBasic extends TaskAbstract {
	
	public TaskBasic(int id, String description) {
		
		this.id = id;
		this.description = description;
		status = TO_DO;
	}
	
	/* ProgressTask method implementation. Progresses tasks to specified column, 
	 * provided : 1) the column exists	2) It's not skipping any columns if moving forward */
	public boolean ProgressTask(String column) {
		
		int indexCol = -1;
		int indexCur = -1;
		
		if(status.equals(DONE)) {
			
			System.err.println("Error: Tasks cannot be moved once in the 'Done' column.");
			System.exit(1);
			return false;
		}

		for(int i = 0 ; i < STATUS_ARRAY.length ; i++) {
			
			if(column.equals(STATUS_ARRAY[i])) {
				
				indexCol = i;
			}
			
			if(status.equals(STATUS_ARRAY[i])) {
				
				indexCur = i;
			}
		}
		
		if(indexCol == -1 || indexCur == -1) {
			
			System.err.println("Error: Invalid column name.");
			System.exit(1);
			return false;
			
		} else if(indexCol < indexCur && indexCol != 3) {
			
			status = STATUS_ARRAY[indexCol];
			return true;
			
		} else if(indexCol - indexCur == 1) {
			
			status = STATUS_ARRAY[indexCol];
			return true;
		}
		
		System.err.println("Error: Invalid move. A task may move backward to 'In Process' or 'To Do',"
				+ " but must progress forward incrementally.");
		System.exit(1);
		return false;
	}
}
