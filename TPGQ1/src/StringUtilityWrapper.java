/* Author: Declan Holmes
 * Date: 20/06/2017
 */

import java.util.ArrayList;
import java.util.Random;

public class StringUtilityWrapper {

	// instance variables
	String input = "";
	
	// constructor
	public StringUtilityWrapper(String input) {
		
		this.input = input;
	}
	
	// reverse method, returns the mirror image of the string
	public String Reverse() {
		
		String output = "";
		char[] inputCharArr = input.toCharArray();
		
		for(int i = inputCharArr.length - 1 ; i >= 0 ; i--) {
			
			output += String.valueOf(inputCharArr[i]);
		}
		
		return output;
	}
	
	// reshuffle method, randomly moves character positions of the string
	public String Reshuffle() {
		
		Random rand = new Random();
		int high = input.length();
		ArrayList<Integer> usedIndexesArr = new ArrayList<Integer>();
		int currIndex;
		String output = "";
		
		while(output.length() != input.length()) {
			
			currIndex = rand.nextInt(high);
			
			if(ExistsInList(usedIndexesArr, currIndex) != true) {
				
				usedIndexesArr.add(currIndex);

				output += input.charAt(currIndex);
							
			}
		}
		
		return output;
	}
	
	// ExistsInList method, returns whether or not the supplied index occurs in the supplied arraylist 
	private boolean ExistsInList(ArrayList<Integer> list, int index) {
		
		for(int i = 0 ; i < list.size() ; i++) {
			
			if(list.get(i) == index) {
				
				return true;
			}
		}
		
		return false;
	}
}
