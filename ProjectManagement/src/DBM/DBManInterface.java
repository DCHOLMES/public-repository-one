package DBM;

import Model.ProjectInterface;
import Model.RequirementInterface;

interface DBManInterface {

	public void saveProjToDB(ProjectInterface project);
	
	public void saveReqToDB(String parentId, RequirementInterface req);
	
	public void deleteFromDB(String id);
	
	public void connect();
}
