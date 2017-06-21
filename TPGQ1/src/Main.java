/* Author: Declan Holmes
 * Date: 20/06/2017
 */

import java.util.Scanner;

public class Main {

	// constants
	final static String WELCOME = "Hello! Please choose from the following Options\n1. Reverse a string\n2. Shuffle a string\n3. Exit";
	final static String INPUT_PROMPT = "Please enter a string";
	final static String INVALID = "ERROR! Invalid user input, please select from option 1, 2, or 3";
	final static String TERMINATE = "Terminating program...";
	final static String REVERSE = "1";
	final static String SHUFFLE = "2";
	final static String EXIT = "3";
	
	// main method
	public static void main(String[] args) {
		
		PromptUser();
	}
	
	// PromptUser method, prompts the user with options, takes user input and performs the required operation
	public static void PromptUser() {
		
		Scanner scan = new Scanner(System.in);
		StringUtilityWrapper utilObj;
		boolean exit = false;
		String input = "";
		
		while(exit == false) {
			
			System.out.println(WELCOME);
			input = scan.nextLine();
			
			if(input.equals(REVERSE)) {
				
				System.out.println(INPUT_PROMPT);
				
				input = scan.nextLine();
				
				utilObj = new StringUtilityWrapper(input);
				
				System.out.println(utilObj.Reverse());
				
			} else if(input.equals(SHUFFLE)) {
				
				System.out.println(INPUT_PROMPT);
				
				input = scan.nextLine();
				
				utilObj = new StringUtilityWrapper(input);
				
				System.out.println(utilObj.Reshuffle());
	
			} else if(input.equals(EXIT)) {
				
				exit = true;
				
				System.out.println(TERMINATE);
				
			} else {
				
				System.out.println(INVALID);
			}
		}
	}
}
