package DBM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Document;
import Model.Form;
import Model.ProjectInterface;
import Model.Requirement;
import Model.RequirementInterface;

public class DBManager implements DBManInterface {

	private final String USER = "ProjMan";
	private final String INSERT = "insert into ";
	private final String REQ_TABLE = "projman.requirements";
	private final String PROJ_TABLE = "projman.projects";
	private final String COL_PROJ_ID = "ProjId";
	private final String COL_PROJ_TITLE = "Title";
	private final String COL_PROJ_DESC = "Descript";
	private final String COL_PROJ_STAT = "ProjStatus";
	private final String COL_PROJ_TYPE = "Type";
	private final String COL_REQ_ID = "ReqId";
	private final String COL_REQ_PARENT_ID = "ParentId";
	private final String COL_REQ_DESC = "Descript";
	private final String COL_REQ_STAT = "ReqStatus";
	private final String VALUES = "values ";
	private final String PASSWORD = "ProjMan";
	private final String INSERT_PROJ_STATEMENT = INSERT + PROJ_TABLE + "(" + COL_PROJ_ID + "," + COL_PROJ_TITLE + "," + COL_PROJ_DESC + "," + COL_PROJ_STAT
			+ "," + COL_PROJ_TYPE + ")" + VALUES + "(?, ?, ?, ?, ?)";
	private final String INSERT_REQ_STATEMENT = INSERT + REQ_TABLE + "(" + COL_REQ_ID + "," + COL_REQ_PARENT_ID + "," + COL_REQ_DESC + "," + COL_REQ_STAT
			+ ")" + VALUES + "(?, ?, ?, ?)";
	private final String SELECT_REQ_STATEMENT = "select * from " + REQ_TABLE + " where " + COL_REQ_PARENT_ID + " = ?";
	private final String SELECT_ALL_PROJ_STATEMENT = "select * from " + PROJ_TABLE; 
	private String srcUrl = "jdbc:mysql://localhost:3306/?useSSL=false";
	private Connection conn;
	
	@Override
	public void saveProjToDB(ProjectInterface project) {

		try {
			
			connect();
			PreparedStatement projStmnt = conn.prepareStatement(INSERT_PROJ_STATEMENT);
				
			projStmnt.setString(1, project.getId());
			projStmnt.setString(2, project.getTitle());
			projStmnt.setString(3, project.getDesc());
			projStmnt.setString(4, project.getStatus());
			
			if(project instanceof Form) {
				
				projStmnt.setString(5, "form");
			
			} else {
				
				projStmnt.setString(5, "doc");
			}
			
			projStmnt.execute();
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveReqToDB(String parentId, RequirementInterface req) {
				
		try {
			
			connect();
			
			PreparedStatement reqStmnt = conn.prepareStatement(INSERT_REQ_STATEMENT);
			
			reqStmnt.setString(1, req.getId());
			reqStmnt.setString(2, parentId);
			reqStmnt.setString(3, req.getDesc());
			reqStmnt.setString(4, req.getStatus());
			
			reqStmnt.execute();
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFromDB(String id) {
		
		
	}

	@Override
	public void connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(srcUrl, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProjectInterface> loadProjects() {
		
		PreparedStatement stmnt;
		ArrayList<ProjectInterface> projList = new ArrayList<ProjectInterface>();
		
		try {
			
			connect();
			stmnt = conn.prepareStatement(SELECT_ALL_PROJ_STATEMENT);
			
			ResultSet rslt = stmnt.executeQuery(SELECT_ALL_PROJ_STATEMENT);
			
			while(rslt.next()) {
				
				if(rslt.getString(COL_PROJ_TYPE) == "form") {
					
					projList.add(new Form(rslt.getString(COL_PROJ_ID), rslt.getString(COL_PROJ_TITLE), rslt.getString(COL_PROJ_DESC)));
					
				} else {
					
					projList.add(new Document(rslt.getString(COL_PROJ_ID), rslt.getString(COL_PROJ_TITLE), rslt.getString(COL_PROJ_DESC)));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projList;
	}
	
	public ArrayList<RequirementInterface> loadRequirements(String projId) {
		
		PreparedStatement stmnt;
		ArrayList<RequirementInterface> reqList = new ArrayList<RequirementInterface>();
		
		try {
			
			connect();
			stmnt = conn.prepareStatement(SELECT_REQ_STATEMENT);
			
			stmnt.setString(1, projId);
			
			ResultSet rslt = stmnt.executeQuery();
			
			while(rslt.next()) {
				
				reqList.add(new Requirement(rslt.getString(COL_REQ_ID), rslt.getString(COL_REQ_DESC), rslt.getString(COL_REQ_STAT)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reqList;
	}
}
