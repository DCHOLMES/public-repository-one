package Model;

public class Requirement implements RequirementInterface {

	//constants
	private String ACTIVE = "Active";
	private String CANCELLED = "Cancelled";
	
	//variables
	private String id;
	private String desc;
	private String status;
	
	public Requirement(String id, String desc, String status) {
		
		this.id = id;
		this.desc = desc;
		this.status = status;
	}
	
	@Override
	public String getId() {
		
		return id;
	}

	@Override
	public String getDesc() {
		
		return desc;
	}

	@Override
	public void setDesc(String desc) {
		
		this.desc = desc;
	}

	@Override
	public String getStatus() {
		
		return status;
	}

	@Override
	public void cancel() {
		
		this.status = CANCELLED;
	}

	@Override
	public void activate() {
		
		this.status = ACTIVE;
	}
	
	public String toString() {
		
		return desc;
	}
}
