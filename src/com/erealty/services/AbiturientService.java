package com.erealty.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dao.AbiturientDAO;
import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.model.Abiturient;

public class AbiturientService {
	private DAOManager daoManager;
	private static final Logger LOGGER = Logger.getLogger(AbiturientService.class);

	public AbiturientService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}
	
	// create
	public boolean createAbiturient(Abiturient abiturient) throws SQLException {
		return (boolean) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
					return abiturientDAO.createAbiturient(abiturient);
				} catch (SQLException e) {
					LOGGER.error("Exception when selecting abitur: "+e);
					return null;
				}
			}
		});
	}

	// read
	@SuppressWarnings("unchecked")
	public List<Abiturient> selectAllAbiturients() throws SQLException {
		return (List<Abiturient>) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
					return abiturientDAO.selectAllAbiturients();
				} catch (SQLException e) {
					LOGGER.error("Exception when selecting abitur: "+e);
					return null;
				}
			}
		});
	}

	public Abiturient selectAbiturientById(Integer id) {
		try {
			return (Abiturient) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
						Abiturient abitur = abiturientDAO.selectAbiturientById(id);
						return abitur;
					} catch (SQLException e) {
						LOGGER.error("Exception when selecting abitur: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			LOGGER.error("Exception when selecting abitur: "+e);
			e.printStackTrace();
		}
		return null;
	}

	public Abiturient selectAbiturientByLogin(String login) {
		try {
			return (Abiturient) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
						Abiturient abitur = abiturientDAO.selectAbiturientByLogin(login);
						return abitur;
					} catch (SQLException e) {
						LOGGER.error("Exception when selecting abitur by login: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			LOGGER.error("Exception when selecting abitur by login: "+e);
			e.printStackTrace();
			return null;
		}
	}
	// // delete
	// public void deleteStudentByExamBook(Integer examBookNumber) throws
	// SQLException {
	// Statement statement = connection.createStatement();
	// statement.executeUpdate("DELETE FROM student WHERE exam_book_no = '" +
	// examBookNumber + "'");
	// statement.close();
	// }
	//
	// // updateGroup
	// public void updateStudentGroup(String oldGroup, String newGroup) throws
	// SQLException {
	// Statement statement = connection.createStatement();
	// int updatedStudents = statement.executeUpdate(
	// "UPDATE student SET group_name = '" + newGroup + "' WHERE group_name = '"
	// + oldGroup + "'");
	// System.out.println("data about" + updatedStudents + " students updated");
	// statement.close();
	// }
}
