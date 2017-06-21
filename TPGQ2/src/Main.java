/* Author: Declan Holmes
 * Date: 20/06/2017
 */

import java.util.Scanner;

public class Main {

	static final String WELCOME = "Hello! Please enter a series of integers seperated by the ',' character (no spaces)";
	static final String ERROR = "ERROR! The values supplied by the user could not be parsed, only integers will be accepted each seperated by the ',' character";
	static final String TERMINATE = "Terminating program...";
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		MedianCalculator calc;
		String input  = "";
		String[] values;
		
		System.out.println(WELCOME);
		
		input = scan.nextLine();
		
		values = input.split(",");
		
		calc = new MedianCalculator(StringsToInts(values));
		
		System.out.println(calc.GetMedian());
		
		System.out.println(TERMINATE);
	}
	
	// StringsToInts method, converts the user input values into an integer array.
	public static int[] StringsToInts(String[] values) {
		
		int[] output = new int[values.length];
				
		try {
			
			for(int i = 0 ; i < values.length ; i++) {
				
				output[i] = Integer.parseInt(values[i]);
			}
			
		} catch(NumberFormatException e) {
			
			System.out.println(ERROR + "\n" + TERMINATE);
			System.exit(1);
		}
		return output;
	}
}
