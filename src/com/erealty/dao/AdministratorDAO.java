package com.erealty.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erealty.dbutils.UniversalTransformer;
import com.erealty.model.Administrator;

public class AdministratorDAO {
	private Connection connection;

	public AdministratorDAO(Connection connection) {
		this.connection = connection;
	}

	// create
	public boolean createAdministrator(Administrator administrator) throws SQLException {
		boolean added = false;
		Statement statement = connection.createStatement();
		String adminExactPhotoPath = (File.separator.equals("\\") ? administrator.getPhotoPath().replace("\\", "\\\\")
				: administrator.getPhotoPath());
		String query = String.format("INSERT INTO administrator (login,password,photo_path) value ('%s', '%s', '%s')",
				administrator.getLogin(), administrator.getPassword(), adminExactPhotoPath);
		if (selectAdminByLogin(administrator.getLogin()) == null) {
			statement.executeUpdate(query);
			added = true;
		}
		statement.close();
		return added;
	}

	// read
	public List<Administrator> selectAllAdmins() throws SQLException {
		List<Administrator> users = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM administrator");
		Administrator s;
		while (true) {
			s = (Administrator) UniversalTransformer.fromResultSetToObject(rs, Administrator.class);
			if (s == null) {
				break;
			} else
				users.add(s);
		}
		rs.close();
		statement.close();
		return users;
	}

	// readByExamBookNumber
	public Administrator selectAdminById(Integer id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM administrator WHERE id = " + id.toString());
		Administrator u = null;
		while (rs.next()) {
			// s = StudentTransformer.fromResultSetToObject(rs);
			u = (Administrator) UniversalTransformer.fromResultSetToObject(rs, Administrator.class);
			if (u == null) {
				break;
			}
		}
		rs.close();
		statement.close();
		return u;
	}

	public Administrator selectAdminByLogin(String login) throws SQLException {
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM administrator WHERE login = '" + login + "'";
		ResultSet rs = statement.executeQuery(query);
		Administrator u = null;
		// s = StudentTransformer.fromResultSetToObject(rs);
		u = (Administrator) UniversalTransformer.fromResultSetToObject(rs, Administrator.class);
		rs.close();
		statement.close();
		return u;
	}

	public void deleteUserByID(String login) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM administrator WHERE login = '" + login + "'");
		statement.close();
	}

	public void updateAdminPhotoPath(String oldPath, String newPath) throws SQLException {
		Statement statement = connection.createStatement();
		int updatedStudents = statement.executeUpdate("UPDATE administrator SET photoPath = '" + newPath + "' WHERE photoPath = '" + oldPath + "'");
		System.out.println("data about" + updatedStudents + " students updated");
		statement.close();
	}
}