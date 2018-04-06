package com.erealty.services;

import java.sql.SQLException;
import java.util.Set;

import com.erealty.dao.AbiturientDAO;
import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.dao.MarkDAO;
import com.erealty.model.Abiturient;

public class SubjectService {
	private DAOManager daoManager;

	public SubjectService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> selectSubjectsByAbiturientLogin(String login) {
		try {
			return (Set<String>) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
						Abiturient abitur = abiturientDAO.selectAbiturientByLogin(login);
						MarkDAO markDAO = daoManager.getMarkDAOTx();
						Set<String> otherSubjects = markDAO.selectOtherMarksOfAbiturient(abitur.getID());
						return otherSubjects;
					} catch (SQLException e) {
						return null;
					}
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
