package Model;
import java.util.ArrayList;

public abstract class Project implements ProjectInterface {

	//constants
	protected String ACTIVE = "Active";
	protected String COMPLETE = "Complete";
	protected String CANCELLED = "Cancelled";
	protected String HOLD = "Hold";
	
	//variables
	protected String title;
	protected String id;
	protected String desc;
	protected String status;
	private ArrayList<RequirementInterface> reqs = new ArrayList<RequirementInterface>();
	private ArrayList<Personnel> personnelList = new ArrayList<Personnel>();
	
	//accessors
	public String getTitle() {
		
		return title;
	}
	
	public String getId() {
		
		return id;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public RequirementInterface[] getReqs() {
		
		return reqs.toArray(new RequirementInterface[0]);
	}
	
	public String[] getPersonnel() {
		 
		String[] retList = new String[personnelList.size()];
		int index = 0;
		
		for(Personnel p : personnelList) {
			
			retList[index] = p.toString();
			index++;
		}
		
		return retList;
	}
	
	public String getDesc() {
		
		return desc;
	}
	
	//mutators
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public void setDesc(String desc) {
		
		this.desc = desc;
	}
	
	/*add personnel to array list. If personnel already exists in list return false,
	 * else add the personnel and return true. */
	public boolean addPersonnel(Personnel pers) {
		
		for(Personnel p : personnelList) {
		
			if(p.equals(pers)) {
				
				return false;
			
			} else {
				
				personnelList.add(pers);
			}
		}
		
		return true;
	}
	
	public void addRequirement(RequirementInterface req) {
		
		reqs.add(req);
	}
	
	public void activate() {
		
		this.status = ACTIVE;
	}
	
	public void cancel() {
		
		this.status = CANCELLED;
	}
	
	public void hold() {
		
		this.status = HOLD;
	}
	
	public void complete() {
		
		this.status = COMPLETE;
	}
	
	public String toString() {
		
		return title;
	}
	
	public RequirementInterface getReqById(String id) {
		
		for(RequirementInterface r : reqs) {
			
			if(r.getId().equals(id)) {
				
				return r;
			}
		}
		
		return null;
	}
}
