package com.erealty.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.erealty.dbutils.UniversalTransformer;
import com.erealty.model.Mark;

public class MarkDAO {
	private static final Logger LOGGER = Logger.getLogger(MarkDAO.class);
	private Connection connection;

	public MarkDAO(Connection connection) {
		this.connection = connection;
	}

	// create
	public boolean createMark(Mark mark) throws SQLException {
		boolean added = false;
		Statement statement = connection.createStatement();
		String query = String.format("INSERT INTO mark (abiturientID, subj, points) value (%d, '%s', %d)",
				mark.getAbiturientID(), mark.getSubj(), mark.getPoints());
		if (selectMarkByAbiturientAndSubject(mark.getAbiturientID(), mark.getSubj()) == null) {
			statement.executeUpdate(query);
			added = true;
		}
		statement.close();
		return added;
	}

	// read
	public List<Mark> selectAllMarks() throws SQLException {
		List<Mark> marks = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM mark");
		Mark s;
		while (true) {
			s = (Mark) UniversalTransformer.fromResultSetToObject(rs, Mark.class);
			if (s == null) {
				break;
			} else
				marks.add(s);
		}
		rs.close();
		statement.close();
		return marks;
	}

	public Mark selectMarkByAbiturientAndSubject(Integer abiturientID, String subject) throws SQLException {
		String query = "SELECT * FROM mark WHERE abiturientID=" + abiturientID
				+ (subject == null ? "" : " AND subj='" + subject + "'");
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		Mark mark;
		mark = (Mark) UniversalTransformer.fromResultSetToObject(rs, Mark.class);
		rs.close();
		statement.close();
		return mark;
	}

	public List<Mark> selectMarkByAbiturient(Integer abiturientID) throws SQLException {
		List<Mark> marks = new ArrayList<>();
		String query = "SELECT * FROM mark WHERE abiturientID=" + abiturientID;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		Mark s;
		while (true) {
			s = (Mark) UniversalTransformer.fromResultSetToObject(rs, Mark.class);
			if (s == null) {
				break;
			} else
				marks.add(s);
		}
		rs.close();
		statement.close();
		return marks;
	}

	public void deleteMarkByAbiturient(Integer ID) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM mark WHERE abiturientID = '" + ID + "'");
		statement.close();
	}

	public void deleteMarkByAbiturientAndSubj(Integer ID, String subj) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM mark WHERE abiturientID = '" + ID + "' and subj=" + subj);
		statement.close();
	}

	public void deleteMarkByID(Integer ID) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM mark WHERE ID = '" + ID + "'");
		statement.close();
	}

	public void updateMarkBySubjAndAbitur(Integer abiturientID, String subj, Integer points) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE faculty SET points = " + points + " WHERE subj = '" + subj
				+ "' AND abiturientID = " + abiturientID);
		LOGGER.info("Set " + points + "in" + subj + " for abiturient with id " + abiturientID);
		statement.close();
	}

	public Mark selectMarkByID(Integer mark1id) throws SQLException {
		Mark mark;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM mark WHERE ID = " + mark1id);
		mark = (Mark) UniversalTransformer.fromResultSetToObject(rs, Mark.class);
		rs.close();
		statement.close();
		return mark;
	}

	public Set<String> selectOtherMarksOfAbiturient(Integer abiturientID) throws SQLException {
		String query = "SHOW COLUMNS FROM mark LIKE 'subj'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		String s;
		Set<String> all = new TreeSet<>();
		while(rs.next()){
			s = rs.getString("Type");
			Pattern p = Pattern.compile("\'([^\']*)\'");
			Matcher m = p.matcher(s);
			while (m.find()) {
				all.add(m.group(1));
			}
		}		
		Set<String> subjects = new HashSet<>();
		query = "SELECT DISTINCT subj FROM mark WHERE abiturientID="+ abiturientID + "";
		statement = connection.createStatement();
		rs = statement.executeQuery(query);		
		while (rs.next()) {
			s = rs.getString(1);
			subjects.add(s);
		}
		rs.close();
		statement.close();
		all.removeAll(subjects);
		return all;
	}
}