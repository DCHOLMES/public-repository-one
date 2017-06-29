package Model;

public class Form extends Project {

	public Form(String id, String title, String desc) {
		
		this.title = title;
		this.id = id;
		this.desc = desc;
		this.status = this.ACTIVE;
	}
}
