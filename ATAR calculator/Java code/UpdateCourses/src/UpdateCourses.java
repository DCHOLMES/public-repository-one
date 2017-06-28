/* Author : Declan Holmes
 * Student no : 3342769
 * Description : Connects to the VTAC website searches
 * through the courses and collects their details*/
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;


public class UpdateCourses {

	public static void main(String[] args) {

		// variables
		final String PATTERN = "<a href=\"/CourseSearch/searchguide.htm?courseCode=";
		final String VTAC = "http://vtac.edu.au/CourseSearch/searchguide.htm";
		final String COURSE_EX = "?courseCode=";
		WebDriver drive = new HtmlUnitDriver();
		WebElement el;
		Select sel;
		ArrayList<WebElement> options = new ArrayList<WebElement>();
		ArrayList<String> courseNum = new ArrayList<String>();
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<String> area = new ArrayList<String>();
		String temp = null;
		int index = 0;
		int numOp;
		
		//load page, submit to return a list of all courses
		drive.get(VTAC);
		el = drive.findElement(By.name("keyword"));
		
		el.submit();
		
		//get options from area of interest drop down
		sel = new Select(drive.findElement(By.id("asced")));
		options = new ArrayList<WebElement>(sel.getOptions());
		numOp = options.size();
		
		new DBProcessor();
		
		for(int i = 0 ; i < numOp ; i++) {
			area.add(options.get(i).getText());
		}
		
		for(int i = 1 ; i < numOp ; i++) {
			
			sel.selectByIndex(i);

			el = drive.findElement(By.name("keyword"));
			el.submit();
			sel = new Select(drive.findElement(By.id("asced")));
			
			// get page source
			temp = drive.getPageSource();
			index = temp.indexOf(PATTERN);
			
			// find substring course code and add it to the array list 
			courseNum.add(temp.substring(index+50, index+60));
			
			courseNum.clear();
			while((index = temp.indexOf(PATTERN, index + 60)) != -1) {
				
				courseNum.add(temp.substring(index+50, index+60));
			}
			
			// for each course number create and run a thread
			for(String s : courseNum) {
				
				Thread thr = new Thread(new GetInfoRunnable(courses, VTAC + COURSE_EX + s, area.get(i)));
				thr.start();

				// put the thread to sleep so as not to create too much traffic
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
