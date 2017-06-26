/* Author: Declan Holmes
 * Description: Main class, entry point of program. */
public class Main {

	public static void main(String[] args) {
		
		SCRUMBoardDriver drive = SCRUMBoardDriver.getInstance();

		try {
			
			drive.Go(args);
			
		} catch (Exception e) {
			
			System.err.println("Error: An unknown exception has occured.");
		}
	}
}
