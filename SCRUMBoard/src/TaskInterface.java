/* Author: Declan Holmes
 * Description: TaskInterface class. Declares those methods/constants necessary for a story object. */
public interface TaskInterface {

	final String TO_DO = "To Do";
	final String IN_PROC = "In Process";
	final String TO_VER = "To Verify";
	final String DONE = "Done";
	final int NUM_STAT = 4;
	final String[] STATUS_ARRAY = {TO_DO, IN_PROC, TO_VER, DONE};
	abstract int getId();
	abstract String getDescription();
	abstract String getStatus();
	abstract boolean IsDone();
}
