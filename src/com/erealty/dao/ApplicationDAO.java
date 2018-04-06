package com.erealty.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dbutils.UniversalTransformer;
import com.erealty.model.Application;

public class ApplicationDAO {
	private static final Logger LOGGER = Logger.getLogger(ApplicationDAO.class);
	private Connection connection;

	public ApplicationDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean createApplication(Application application) throws SQLException {
		boolean added = false;
		Statement statement = connection.createStatement();
		String query = String.format(
				"INSERT INTO application (specialityID, mark1ID,  mark2ID,  mark3ID, graduationMarkID, active)"
						+ "value (%d, %d, %d, %d, %d, %s)",
				application.getSpecialityID(), application.getMark1ID(), application.getMark2ID(),
				application.getMark3ID(), application.getGraduationMarkID(), application.getActive().toString());
		if (selectApplicationBySpecialityAndFirstMark(application.getSpecialityID(),
				application.getMark1ID()) == null) {
			statement.executeUpdate(query);
			added = true;
		}
		statement.close();
		return added;
	}

	public List<Application> selectApplicationsBySpeciality(Integer specialityID, Boolean active) throws SQLException {
		List<Application> applications = new ArrayList<>();
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM application WHERE specialityID = " + specialityID
				+ (active == null ? "" : " AND active = " + active);
		ResultSet rs = statement.executeQuery(query);
		System.out.println("I am RS!!!"+rs!=null);
		Application s;
		while (true) {
			s = (Application) UniversalTransformer.fromResultSetToObject(rs, Application.class);
			if (s == null) {
				break;
			} else {
				applications.add(s);
			}
		}
		rs.close();
		statement.close();
		return applications;
	}

	public Application selectApplicationBySpecialityAndAbiturient(Integer specialityID, Integer userID)
			throws SQLException {
		Application application = null;
		Statement statement = connection.createStatement();
		String query = "SELECT a.* FROM application a INNER JOIN mark m on mark1ID=m.id WHERE m.abiturientID = "
				+ userID + " AND specialityID = " + specialityID;
		System.out.println(query);
		ResultSet rs = statement.executeQuery(query);
		application = (Application) UniversalTransformer.fromResultSetToObject(rs, Application.class);
		rs.close();
		statement.close();
		return application;
	}

	public List<Application> selectApplicationByAbiturient(Integer userID) throws SQLException {
		List<Application> applications = new ArrayList<Application>();
		Statement statement = connection.createStatement();
		String query = "SELECT a.* FROM application a INNER JOIN mark m on mark1ID=m.id WHERE m.abiturientID = "
				+ userID;
		ResultSet rs = statement.executeQuery(query);
		Application s;
		while (true) {
			s = (Application) UniversalTransformer.fromResultSetToObject(rs, Application.class);
			if (s == null) {
				break;
			} else {
				applications.add(s);
			}
		}
		rs.close();
		statement.close();
		return applications;
	}

	public Application selectNotActiveApplicationBySpecialityAndAbiturient(Integer specialityID, Integer userID)
			throws SQLException {
		Application application = null;
		Statement statement = connection.createStatement();
		String query = "SELECT application.* FROM application a INNER JOIN mark m on mark1ID=mark.id WHERE m.abiturientID = "
				+ userID + " AND specialityID = " + specialityID + " AND active = false";
		ResultSet rs = statement.executeQuery(query);
		application = (Application) UniversalTransformer.fromResultSetToObject(rs, Application.class);
		rs.close();
		statement.close();
		return application;
	}

	public Application selectApplicationBySpecialityAndFirstMark(Integer specialityID, Integer mark1ID)
			throws SQLException {
		Application application = null;
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM application WHERE mark1ID = " + mark1ID + " AND specialityID = " + specialityID;
		ResultSet rs = statement.executeQuery(query);
		application = (Application) UniversalTransformer.fromResultSetToObject(rs, Application.class);
		rs.close();
		statement.close();
		return application;
	}

	public Application selectApplicationBySpecialityAndMarks(Integer specialityID, Integer mark1ID, Integer mark2ID,
			Integer mark3ID, Integer graduationMarkID) throws SQLException {
		Application application = null;
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM application WHERE mark1ID = " + mark1ID + " AND mark2ID = " + mark2ID
				+ " AND mark3ID = " + mark3ID + "graduationMarkID = " + graduationMarkID + " AND specialityID = "
				+ specialityID;
		ResultSet rs = statement.executeQuery(query);
		application = (Application) UniversalTransformer.fromResultSetToObject(rs, Application.class);
		rs.close();
		statement.close();
		return application;
	}

	public void deleteApplicationBySpecialityAndAbiturient(Integer specialityID, Integer abiturientID)
			throws SQLException {
		Statement statement = connection.createStatement();
		String query = "DELETE FROM application where ID IN (SELECT application.ID FROM application a INNER JOIN mark m on"
				+ " mark1ID=mark.ID WHERE abiturientID = " + abiturientID + " AND specialityID = " + specialityID;
		statement.executeUpdate(query);
		statement.close();
		LOGGER.info("Application to speciality " + specialityID + "from user " + abiturientID + " was deleted");
	}

	public void deleteApplicationByAbiturient(Integer abiturientID) throws SQLException {
		Statement statement = connection.createStatement();
		String query = "DELETE FROM application where ID IN (SELECT application.ID FROM application a INNER JOIN mark m on"
				+ " mark1ID=mark.ID WHERE abiturientID = " + abiturientID;
		statement.executeUpdate(query);
		statement.close();
		LOGGER.info("Applications from user " + abiturientID + " were deleted");
	}

	public Integer countNotActiveApplications() throws SQLException {
		Statement statement = connection.createStatement();
		String query = "SELECT count(ID) FROM application  WHERE active = false ";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		Integer count = rs.getInt(1);
		rs.close();
		statement.close();
		return count;
	}

	public Integer countAbiturientApplications(Integer abiturientID) throws SQLException {
		Statement statement = connection.createStatement();
		String query = "SELECT count(a.ID) FROM application a INNER JOIN mark m ON m.id=a.mark1ID"
				+ "  WHERE abiturientID = " + abiturientID;
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		Integer count = rs.getInt(1);
		rs.close();
		statement.close();
		return count;
	}

	public List<Application> selectNotActiveApplications() throws SQLException {
		List<Application> applications = new ArrayList<>();
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM application WHERE active = false";
		ResultSet rs = statement.executeQuery(query);
		Application s;
		while (true) {
			s = (Application) UniversalTransformer.fromResultSetToObject(rs, Application.class);
			if (s == null) {
				break;
			} else {
				applications.add(s);
			}
		}
		rs.close();
		statement.close();
		return applications;
	}

	public void deleteByID(Integer applicationID) throws SQLException {
		Statement statement = connection.createStatement();
		String query = "DELETE FROM application where ID = " + applicationID;
		statement.executeUpdate(query);
		statement.close();
		LOGGER.info("Application " + +applicationID + " was deleted");
	}

	public void activateByID(Integer applicationID) throws SQLException {
		Statement statement = connection.createStatement();
		String query = "UPDATE application SET active=true where ID = " + applicationID;
		statement.executeUpdate(query);
		statement.close();
		LOGGER.info("Application " + applicationID + " activated");
	}

}