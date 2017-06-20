/* Author: Declan Holmes
 * Date: 20/06/2017
 */

import java.util.Scanner;

public class Main {

	static final String WELCOME = "Hello! Please enter a series of values seperated by the ',' character";
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		MedianCalculator calc;
		String input  = "";
		String[] values;
		int[] range;
		
		System.out.println(WELCOME);
		
		input = scan.nextLine();
		
		values = input.split(",");
		
		calc = new MedianCalculator(StringsToInts(values));
		
		System.out.println(calc.GetMedian());
	}
	
	// StringsToInts method, converts the user input values into an integer array.
	public static int[] StringsToInts(String[] values) {
		
		int[] output = new int[values.length];
				
		for(int i = 0 ; i < values.length ; i++) {
			
			output[i] = Integer.parseInt(values[i]);
		}
		
		return output;
	}
}
