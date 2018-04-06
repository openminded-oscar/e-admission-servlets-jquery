package com.erealty.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dbutils.UniversalTransformer;
import com.erealty.model.Faculty;

public class FacultyDAO {
	private static final Logger LOGGER = Logger.getLogger(FacultyDAO.class);
	private Connection connection;

	public FacultyDAO(Connection connection) {
		this.connection = connection;
	}

	// create
	public boolean createFaculty(Faculty faculty) throws SQLException {
		boolean added = false;
		Statement statement = connection.createStatement();
		String query = String.format("INSERT INTO faculty (title, descriptionUK, "+
		"descriptionEN, telNumber, eMail, director) "+
				"value ('%s', '%s', '%s','%s', '%s', '%s')",faculty.getTitle(),
				faculty.getDescriptionUK(),faculty.getDescriptionEN(),faculty.getTelNumber(),
				faculty.geteMail(),faculty.getDirector());
		if (selectFacultyByTitle(faculty.getTitle()) == null) {
			statement.executeUpdate(query);
			added = true;
		}
		statement.close();
		return added;
	}

	// read
	public List<Faculty> selectAllFaculties() throws SQLException {
		List<Faculty> faculties = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM faculty");
		Faculty s;
		while (true) {
			s = (Faculty) UniversalTransformer.fromResultSetToObject(rs, Faculty.class);
			if (s == null) {
				break;
			} else
				faculties.add(s);
		}
		rs.close();
		statement.close();
		return faculties;
	}

	public Faculty selectFacultyById(Integer id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM faculty WHERE ID = " + id.toString());
		Faculty u = null;
		u = (Faculty) UniversalTransformer.fromResultSetToObject(rs, Faculty.class);
		rs.close();
		statement.close();
		return u;
	}

	public Faculty selectFacultyByTitle(String title) throws SQLException {
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM faculty WHERE title = '" + title + "'";
		System.out.println(query);
		ResultSet rs = statement.executeQuery(query);
		Faculty u = null;
		u = (Faculty) UniversalTransformer.fromResultSetToObject(rs, Faculty.class);
		rs.close();
		statement.close();
		return u;
	}

	public void deleteFacultyByID(Integer ID) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM faculty WHERE ID = '" + ID + "'");
		statement.close();
	}

	public void updateFacultyDirector(String title, String newDirector) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE faculty SET director = '" + newDirector + "' WHERE title = '" + title + "'");
		LOGGER.info("Director of " + title + " was changed. Current: "+newDirector);
		statement.close();
	}
}
