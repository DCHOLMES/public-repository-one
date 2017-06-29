package Model;

public class Document extends Project {

	public Document(String id, String title, String desc) {
	
		this.title = title;
		this.id = id;
		this.desc = desc;
		this.status = this.ACTIVE;
	}
}
