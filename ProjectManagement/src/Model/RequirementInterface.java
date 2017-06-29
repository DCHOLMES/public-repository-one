package Model;

public interface RequirementInterface {

	public String getId();
	public String getDesc();
	public String getStatus();
	public void setDesc(String desc);
	public void cancel();
	public void activate();
}
