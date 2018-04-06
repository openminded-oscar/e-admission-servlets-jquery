package com.erealty.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dbutils.UniversalTransformer;
import com.erealty.model.Speciality;

public class SpecialityDAO {
	private static final Logger LOGGER = Logger.getLogger(SpecialityDAO.class);
	private Connection connection;

	public SpecialityDAO(Connection connection) {
		this.connection = connection;
	}

	// create
	public boolean createSpeciality(Speciality speciality) throws SQLException {
		boolean added = false;
		Statement statement = connection.createStatement();
		String query = String.format(
				"INSERT INTO requiredSubject (code, name, descriptionUK, descriptionEN,"+
		" facultyID, budgetLimit, commerceLimit, annualPayment)" + "value ('%s',"+
						" '%s', '%s', '%s', '%d', '%d', '%d', '%d')", speciality.getCode(), speciality.getName(),
						speciality.getDescriptionUK(), speciality.getDescriptionEN(), speciality.getFacultyID(),
						speciality.getBudgetLimit(), speciality.getCommerceLimit(), speciality.getAnnualPayment());
		if (selectSpecialityByCode(speciality.getCode()) == null) {
			statement.executeUpdate(query);
			added = true;
		}
		statement.close();
		return added;
	}

	public List<Speciality> selectAllSpecialitiesByFaculty(Integer facultyID) throws SQLException {
		List<Speciality> specialities = new ArrayList<>();
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM speciality"+(facultyID==null?"":(" where facultyID = "+facultyID) );
		ResultSet rs = statement.executeQuery(query);
		Speciality s;
		while (true) {
			s = (Speciality) UniversalTransformer.fromResultSetToObject(rs, Speciality.class);
			if (s == null) {
				break;
			} else {
				specialities.add(s);
			}
		}
		rs.close();
		statement.close();
		return specialities;
	}

	public Speciality selectSpecialityByCode(String code) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM speciality WHERE code = " + code);
		Speciality speciality = null;
		speciality = (Speciality) UniversalTransformer.fromResultSetToObject(rs, Speciality.class);
		rs.close();
		statement.close();
		return speciality;
	}
	
	public List<Speciality> selectSpecialityByMaxAnnual(Integer maxAnnualPayment) throws SQLException {
		List<Speciality> specialities = new ArrayList<>();
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM speciality WHERE annualPayment <= " + maxAnnualPayment;
		ResultSet rs = statement.executeQuery(query);
		Speciality s;
		while (true) {
			s = (Speciality) UniversalTransformer.fromResultSetToObject(rs, Speciality.class);
			if (s == null) {
				break;
			} else {
				specialities.add(s);
			}
		}
		rs.close();
		statement.close();
		return specialities;
	}
	
	public List<Speciality> selectSpecialityByMinBudgetPlaces(Integer minBudgetPlaces) throws SQLException {
		List<Speciality> specialities = new ArrayList<>();
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM speciality WHERE budgetLimit >= " + minBudgetPlaces;
		ResultSet rs = statement.executeQuery(query);
		Speciality s;
		while (true) {
			s = (Speciality) UniversalTransformer.fromResultSetToObject(rs, Speciality.class);
			if (s == null) {
				break;
			} else {
				specialities.add(s);
			}
		}
		rs.close();
		statement.close();
		return specialities;
	}

	public void deleteSpecialityByCode(String code) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM speciality WHERE code = '" + code+"'");
		statement.close();
		LOGGER.info(code + " speciality was deleted");
	}

	public void updateSpecialityPayment(String code, Integer annualPayment) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE speciality SET annualPayment = " + annualPayment + " WHERE code = '" + code + "'");
		LOGGER.info("Set annual payment for speciality: " + code + ": " + annualPayment);
		statement.close();
	}
	
	public void updateSpecialityBudgetLimit(String code, Integer budgetLimit) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE speciality SET budgetLimit = " + budgetLimit + " WHERE code = '" + code + "'");
		LOGGER.info("Set budget limit for speciality: " + code + ": " + budgetLimit);
		statement.close();
	}
	
	public void updateSpecialitycommerceLimit(String code, Integer commerceLimit) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE speciality SET commerceLimit = " + commerceLimit + " WHERE code = '" + code + "'");
		LOGGER.info("Set commerce limit for speciality: " + code + ": " + commerceLimit);
		statement.close();
	}

	public Speciality selectSpecialityByID(Integer specialityID) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM speciality WHERE id = " + specialityID);
		Speciality speciality = null;
		speciality = (Speciality) UniversalTransformer.fromResultSetToObject(rs, Speciality.class);
		rs.close();
		statement.close();
		return speciality;
	}
}