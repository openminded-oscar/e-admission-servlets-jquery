package com.erealty.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dbutils.UniversalTransformer;
import com.erealty.model.RequiredSubject;

public class RequiredSubjectDAO {
	private static final Logger LOGGER = Logger.getLogger(RequiredSubjectDAO.class);
	private Connection connection;

	public RequiredSubjectDAO(Connection connection) {
		this.connection = connection;
	}

	// create
	public boolean createRequiredSubject(RequiredSubject requiredSubject) throws SQLException {
		boolean added = false;
		Statement statement = connection.createStatement();
		String query = String.format(
				"INSERT INTO requiredSubject (specialityID, ordinal, subj)" + "value ('%s', '%s', '%s')",
				requiredSubject.getSpecialityID(), requiredSubject.getOrdinal(), requiredSubject.getSubj());
		statement.executeUpdate(query);
		added = true;
		statement.close();
		return added;
	}

	public List<RequiredSubject> selectAllRequiredSubjects() throws SQLException {
		List<RequiredSubject> allRequiredSubjects = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM faculty");
		RequiredSubject s;
		while (true) {
			s = (RequiredSubject) UniversalTransformer.fromResultSetToObject(rs, RequiredSubject.class);
			if (s == null) {
				break;
			} else {
				allRequiredSubjects.add(s);
			}
		}
		rs.close();
		statement.close();
		return allRequiredSubjects;
	}

	public List<RequiredSubject> selectRequiredSubjectsBySpecialityId(Integer ID) throws SQLException {
		List<RequiredSubject> requiredSubjects = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM requiredSubject WHERE specialityID = " + ID.toString());
		RequiredSubject u = null;
		while (true) {
			u = (RequiredSubject) UniversalTransformer.fromResultSetToObject(rs, RequiredSubject.class);
			if (u == null) {
				break;
			} else {
				requiredSubjects.add(u);
			}
		}
		rs.close();
		statement.close();
		return requiredSubjects;
	}

	public List<RequiredSubject> selectRequiredSubjectsBySubject(String subject) throws SQLException {
		List<RequiredSubject> requiredSubjects = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM requiredSubject WHERE specialityID = '" + subject + "'");
		RequiredSubject u = null;
		while (true) {
			u = (RequiredSubject) UniversalTransformer.fromResultSetToObject(rs, RequiredSubject.class);
			if (u == null) {
				break;
			} else {
				requiredSubjects.add(u);
			}
		}
		rs.close();
		statement.close();
		return requiredSubjects;
	}

	public void deleteRequiredSubjectBySpecialityAndSubject(Integer specialityID, String subj) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate(
				"DELETE FROM requiredSubject WHERE specialityID = " + specialityID + " AND subject = '" + subj + "'");
		statement.close();
		LOGGER.info(subj + "from speciality with id=" + specialityID + "was deleted");
	}

	public void updateFacultyDirector(Integer specialityID, String oldSubject, String newSubject) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE requiredSubject SET subj = '" + newSubject + "' WHERE subj = '" + oldSubject + "' AND specialityID = "+specialityID);
		LOGGER.info("subject for speciality: " + specialityID + " was changed. " + oldSubject+"->"+newSubject);
		statement.close();
	}
}
