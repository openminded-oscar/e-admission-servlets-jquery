package com.erealty.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dao.AbiturientDAO;
import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.dao.MarkDAO;
import com.erealty.model.Abiturient;
import com.erealty.model.Application;
import com.erealty.model.Mark;

public class MarkService {
	private DAOManager daoManager;
	private static final Logger LOGGER = Logger.getLogger(MarkService.class);

	public MarkService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}

	@SuppressWarnings("unchecked")
	public List<Mark> getAllMarksByAbiturientLogin(String login) throws SQLException {
		return (List<Mark>) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					AbiturientDAO abiturDAO = daoManager.getAbiturientDAOTx();
					Abiturient abiturient = abiturDAO.selectAbiturientByLogin(login);
					MarkDAO markDAO = daoManager.getMarkDAOTx();
					List<Mark> marks = markDAO.selectMarkByAbiturient(abiturient.getID());
					return marks;
				} catch (SQLException e) {
					System.out.println("Exception when selecting all specialities: " + e);
					LOGGER.error("Exception when selecting all specialities: " + e);
					return null;
				}
			}
		});
	}

	public Boolean addMark(Mark mark) {
		try {
			return (Boolean) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						MarkDAO markDAO = daoManager.getMarkDAOTx();
						List<Mark> marks = markDAO.selectMarkByAbiturient(mark.getAbiturientID());
						if(marks==null){
						return markDAO.createMark(mark);
						}else{
							if(marks.size()<=5){
								return markDAO.createMark(mark);
							}
							else {
								return false;
							}
						}
						
					} catch (SQLException e) {
						System.out.println("Exception when creating new mark" + e);
						LOGGER.error("Exception when creating new mark" + e);
						return false;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when creating new mark" + e);
			LOGGER.error("Exception when creating new mark" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mark> getAllMarksByApplication(Application application) {
		try {
			return (List<Mark>) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						List<Mark> marks = new ArrayList<Mark>();
						MarkDAO markDAO = daoManager.getMarkDAOTx();
						marks.add(markDAO.selectMarkByID(application.getMark1ID()));
						marks.add(markDAO.selectMarkByID(application.getMark2ID()));
						marks.add(markDAO.selectMarkByID(application.getMark3ID()));
						marks.add(markDAO.selectMarkByID(application.getGraduationMarkID()));
						return marks;
					} catch (SQLException e) {
						System.out.println("Exception when getting applications marks: " + e);
						LOGGER.error("Exception when getting applications marks: " + e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when getting applications marks: " + e);
			LOGGER.error("Exception when getting applications marks: " + e);
		}
		return null;
	}
}
