/* Author : Declan Holmes
 * Student Number : 3342769
 * Description : Runnable class for retrieving Course information
 * from VTAC, creating and returning Course object
 */
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class GetInfoRunnable implements Runnable{

	// variables
	private final String BACH_PATTERN = "Bachelor Degree is ";
	private final String JAN_PATTERN = "Jan 2014";
	private final String H1_HE = "<h1 class=\"HE\">";
	private final String H1_END = "</h1>";
	private final String H2_END = "</h2>";
	private final String H1_VET = "<h1 class=\"VET\">";
	private final String H1_GRAD = "<h1 class=\"Grad\">";
	private final String H1_HEGRAD = "<h1 class=\"HE-Grad\">";
	private final String H1_HEVET = "<h1 class=\"HE-VET\">";
	private final String H1_HEGET = "<h1 class=\"GET\">";
	private final String H2_INST = "<h2 class=\"instname\">";
	private ArrayList<Course> courses;
	private String site;
	private WebDriver drive = new HtmlUnitDriver();
	private String temp;
	private String name;
	private String uni;
	private Double atar;
	private int index;
	private boolean found;
	private String area = "";
	
	// constructor
	public GetInfoRunnable(ArrayList<Course> courses, String site, String area) {
		
		this.courses = courses;
		this.site = site;
		this.area = area;
	}
	
	// Run Method
	@Override
	public void run() {
		
		drive.get(site);
		
		temp = drive.getPageSource();
		
		// find course name
		name = findName();
		
		//find institution name
		uni = findUni();
		
		// find bachelor score requirement
		index = temp.indexOf(BACH_PATTERN);
		
		try {
			atar = Double.parseDouble(temp.substring(index+19, index+21));
		
		} catch(NumberFormatException e) {
			
		}
		
		if(atar == null) {
			
			index = temp.indexOf(JAN_PATTERN);
			
			if(index != 0) {
				index += 8;
			
				while(found == false) {
					
					try {						
						if(Pattern.matches("\\d+(\\.\\d+)?", temp.substring(index, index+4))) {
							
							atar = Double.parseDouble(temp.substring(index, index+4));
							
							if(atar == 2014 || atar == 8859) {
								
								atar = null;
							}
							
							found = true;
						} else {
							
							index++;
						}
						
					} catch(Exception StringIndexOutOfBoundsException) {
						found = true;
					}
				}
			}
		}
		
		courses.add(new Course(name, uni, atar, area));
		System.out.println(name + " || " + atar + " || " + uni + " || " + area);
	}
	
	// Method for retrieving course name from html src code
	public String findName() {
		
		int openTag;
		int closeTag;
		
		if(temp.contains(H1_HE)) {
			
			openTag = temp.indexOf(H1_HE);
			closeTag = temp.indexOf(H1_END, openTag);
			
			return temp.substring(openTag + H1_HE.length(), closeTag).replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
		}
		
		if(temp.contains(H1_VET)) {
			
			openTag = temp.indexOf(H1_VET);
			closeTag = temp.indexOf(H1_END, openTag);
			
			return temp.substring(openTag + H1_VET.length(), closeTag).replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
		}
		
		if(temp.contains(H1_GRAD)) {
			
			openTag = temp.indexOf(H1_GRAD);
			closeTag = temp.indexOf(H1_END, openTag);
			
			return temp.substring(openTag + H1_GRAD.length(), closeTag).replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
		}
		
		if(temp.contains(H1_HEGRAD)) {
			
			openTag = temp.indexOf(H1_HEGRAD);
			closeTag = temp.indexOf(H1_END, openTag);
			
			return temp.substring(openTag + H1_HEGRAD.length(), closeTag).replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
		}
		
		if(temp.contains(H1_HEVET)) {
			
			openTag = temp.indexOf(H1_HEVET);
			closeTag = temp.indexOf(H1_END, openTag);
			
			return temp.substring(openTag + H1_HEVET.length(), closeTag).replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
		}
		
		if(temp.contains(H1_HEGET)) {
			
			openTag = temp.indexOf(H1_HEGET);
			closeTag = temp.indexOf(H1_END, openTag);
			
			return temp.substring(openTag + H1_HEGET.length(), closeTag).replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
		}
		
		return null;
	}
	
	// Method for retrieving institution name from html src code
	public String findUni() {
		
		if(temp.contains(H2_INST)) {
			
			int openTag = temp.indexOf(H2_INST);
			int closeTag = temp.indexOf(H2_END, openTag);
			
			return temp.substring(openTag + H2_INST.length(), closeTag).replaceFirst("\\s+$", "").replaceFirst("^\\s+", "");
		}
		
		return null;
	}
}
