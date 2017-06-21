/* Author: Declan Holmes
 * Date: 20/06/2017
 */
public class MedianCalculator {

	// instance variable
	private int[] range;
	
	
	// constructor
	public MedianCalculator(int[] range) {
		
		this.range = range;
		
		Sort(0, range.length - 1);
		
	}
	
	// Quicksort method, orders the array by element values.
	public void Sort(int start, int end) {
		
		int i = start;
		int x = end;
		int piv = range[start +(end - start) /2];
		
		while(i <= x) {
			
			while(range[i] < piv) {
				
				i++;
			}
			
			while(range[x] > piv) {
				
				x--;
			}
			
			if(i <= x) {
				
				// swap values at elements i and x
				int temp = range[i];
				range[i] = range[x];
				range[x] = temp;
				
				i++;
				x--;
			}
		}
		
		if(start < x) {
			
			Sort(start, x);
		}
		
		if(i < end) {
			
			Sort(i, end);
		}
	}
	
	// GetMedian method, returns the middle residing in the middle of the array, if two exist returns the average of those two.
	public double GetMedian() {
		
		double arrSize = range.length;
		double lowerMidNum = range[(range.length / 2) - 1];
		double upperMidNum = range[(range.length / 2)];
		
		if(arrSize % 2 != 0) {
			
			return range[range.length / 2];
		
		} else {
			
			return (lowerMidNum + upperMidNum) / 2;
		}
	}
}
