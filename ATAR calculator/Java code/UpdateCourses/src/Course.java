/* Author : Declan Holmes
 * Student Number : 3342769
 * Description : Course object
 */
public class Course {

	// variables
	private String courseName;
	private String university;
	private Double atar = null;
	private String area = "";

	
	// Constructors
	public Course(String courseName, String university, Double atar, String area) {
		
		this.courseName = courseName;
		this.university = university;
		this.atar = atar;
		this.area = area;
	}
	
	// Accessor Methods
	public String getName() {
		
		return courseName;
	}
	
	public String getUni() {
		
		return university;
	}
	
	public Double getAtar() {
		
		return atar;
	}
}
