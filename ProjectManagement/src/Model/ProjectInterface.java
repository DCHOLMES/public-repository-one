package Model;

public interface ProjectInterface {

	public String getTitle();
	
	public String getId();
	
	public String[] getPersonnel();
	
	public RequirementInterface[] getReqs();
	
	public String getDesc();
	
	public String getStatus();
	
	public void setTitle(String title);
	
	public void setDesc(String desc);
	
	public void addRequirement(RequirementInterface req);
	
	public void complete();
	
	public void hold();
	
	public void cancel();
	
	public void activate();

	public RequirementInterface getReqById(String id);
}
