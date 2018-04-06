package com.erealty.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dbutils.UniversalTransformer;
import com.erealty.model.Abiturient;

public class AbiturientDAO {
	private static final Logger LOGGER = Logger.getLogger(AbiturientDAO.class);
	private Connection connection;

	public AbiturientDAO(Connection connection) {
		this.connection = connection;
	}

	// create
	public boolean createAbiturient(Abiturient abitur) throws SQLException {
		boolean added = false;
		Statement statement = connection.createStatement();
		String abiturExactPhotoPath = (File.separator.equals("\\") ? abitur.getPhotoPath().replace("\\", "\\\\")
				: abitur.getPhotoPath());
		String mName = abitur.getmName();
		String docPhoto1Path = abitur.getDocPhoto1Path();
		String docPhoto2Path = abitur.getDocPhoto2Path();
		
		String command = "INSERT INTO abiturient (login, password, photoPath, fName, lName, DOB, telNumber, eMail, ZNONumber, GradCertNumber"
				+ (mName == null ? "" : ",mName") + (docPhoto1Path == null ? "" : ",docPhoto1Path")
				+ (docPhoto2Path == null ? "" : ",docPhoto2Path") + ")"
				+ " value ('%s', '%s', '%s', '%s', '%s', '%s', '%s','%s', %d, %d"
				+ (mName == null ? "" : "," + "'"+mName+"'") + (docPhoto1Path == null ? "" : "," + "'"+docPhoto1Path+"'")
				+ (docPhoto2Path == null ? "" : "," +"'" + docPhoto2Path+"'") + ")";
		String query = String.format(command, abitur.getLogin(), abitur.getPassword(), abiturExactPhotoPath,
				abitur.getfName(), abitur.getlName(), abitur.getDOB().toString(), abitur.getTelNumber(),
				abitur.geteMail(), abitur.getZNONumber(), abitur.getGradCertNumber());
		
		System.out.println(query);
		if (selectAbiturientById(abitur.getID()) == null) {
			statement.executeUpdate(query);
			added = true;
		}
		statement.close();
		return added;
	}

	// read
	public List<Abiturient> selectAllAbiturients() throws SQLException {
		List<Abiturient> abiturients = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM abiturient");
		Abiturient s;
		while (true) {
			s = (Abiturient) UniversalTransformer.fromResultSetToObject(rs, Abiturient.class);
			if (s == null) {
				break;
			} else
				abiturients.add(s);
		}
		rs.close();
		statement.close();
		return abiturients;
	}

	// readByExamBookNumber
	public Abiturient selectAbiturientById(Integer ID) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM abiturient WHERE ID = " + ID);
		Abiturient u = null;
		u = (Abiturient) UniversalTransformer.fromResultSetToObject(rs, Abiturient.class);
		rs.close();
		statement.close();
		return u;
	}

	public void deleteAbiturientByID(Integer ID) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM abiturient WHERE ID = '" + ID + "'");
		statement.close();
	}

	public void updateAbiturientLName(String newLastName, Integer userID) throws SQLException {
		Statement statement = connection.createStatement();
		int updatedAbiturients = statement
				.executeUpdate("UPDATE abiturient SET lName = '" + newLastName + "' WHERE ID = " + userID + "");
		LOGGER.info("data about" + updatedAbiturients + " abiturients updated");
		System.out.println("data about" + updatedAbiturients + " abiturients updated");
		statement.close();
	}

	public void updateAbiturientPhoneNumber(String oldPhoneNumber, String newPhoneNumber) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE abiturient SET telNumber = '" + newPhoneNumber + "' WHERE telNumber = '"
				+ oldPhoneNumber + "'");
		statement.close();
	}

	public Abiturient selectAbiturientByLogin(String login) throws SQLException {
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM abiturient WHERE login = '" + login+"'";
		ResultSet rs = statement.executeQuery(query);
		Abiturient u = null;
		u = (Abiturient) UniversalTransformer.fromResultSetToObject(rs, Abiturient.class);
		rs.close();
		statement.close();
		return u;
	}
}
