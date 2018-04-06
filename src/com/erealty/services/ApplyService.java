package com.erealty.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dao.AbiturientDAO;
import com.erealty.dao.ApplicationDAO;
import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.dao.MarkDAO;
import com.erealty.dao.RequiredSubjectDAO;
import com.erealty.model.Application;
import com.erealty.model.Mark;
import com.erealty.model.RequiredSubject;

public class ApplyService {
	private DAOManager daoManager;
	private static final Logger LOGGER = Logger.getLogger(ApplyService.class);

	public ApplyService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}

	public Boolean apply(String login, String specialityCode) throws SQLException {
		return (Boolean) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
					AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
					Integer abiturientID = abiturientDAO.selectAbiturientByLogin(login).getID();
					if (applicationDAO.countAbiturientApplications(abiturientID) > 4)
						return false;
					else {
						RequiredSubjectDAO requiredSubjectDAO = daoManager.getRequiredSubjectDAOTx();
						Integer specialityID = daoManager.getSpecialityDAOTx().selectSpecialityByCode(specialityCode)
								.getID();
						List<RequiredSubject> requiredSubjects = requiredSubjectDAO
								.selectRequiredSubjectsBySpecialityId(specialityID);
						System.out.println(requiredSubjects);
						MarkDAO markDAO = daoManager.getMarkDAOTx();
						boolean[] found = new boolean[4];
						Mark[] marks = new Mark[4];
						for (int i = 0; i < 4; ++i) {
							found[i] = false;
							marks[i] = null;
						}
						for (RequiredSubject subject : requiredSubjects) {
							Mark current = markDAO.selectMarkByAbiturientAndSubject(
									abiturientDAO.selectAbiturientByLogin(login).getID(), subject.getSubj());
							if (current != null) {
								if (marks[subject.getOrdinal() - 1] != null) {
									if (current.getPoints() > marks[subject.getOrdinal() - 1].getPoints()) {
										marks[subject.getOrdinal() - 1] = current;
									}
								} else {
									marks[subject.getOrdinal() - 1] = current;
								}
								found[subject.getOrdinal() - 1] = true;
							} else {
								continue;
							}
						}
						boolean toAdd = true;
						for (int i = 0; i < 4; ++i) {
							toAdd &= found[i];
						}
						if (toAdd) {
							System.out.println("" + specialityID + marks[0].getID() + marks[1].getID()
									+ marks[2].getID() + marks[3].getID());
							return applicationDAO.createApplication(new Application(specialityID, marks[0].getID(),
									marks[1].getID(), marks[2].getID(), marks[3].getID(), false));
						}
					}
				} catch (SQLException e) {
					System.out.println("Exception when selecting all specialities: " + e);
					LOGGER.error("Exception when selecting all specialities: " + e);
					return null;
				}
				return null;
			}
		});
	}

	public Boolean ifApplyAvailable(String login, String specialityCode) throws SQLException {
		return (Boolean) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
					AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
					Integer abiturientID = abiturientDAO.selectAbiturientByLogin(login).getID();
					if (applicationDAO.countAbiturientApplications(abiturientID) > 4)
						return false;
					else {
						RequiredSubjectDAO requiredSubjectDAO = daoManager.getRequiredSubjectDAOTx();
						Integer specialityID = daoManager.getSpecialityDAOTx().selectSpecialityByCode(specialityCode)
								.getID();
						List<RequiredSubject> requiredSubjects = requiredSubjectDAO
								.selectRequiredSubjectsBySpecialityId(specialityID);
						System.out.println(requiredSubjects);
						MarkDAO markDAO = daoManager.getMarkDAOTx();
						boolean[] found = new boolean[4];
						Mark[] marks = new Mark[4];
						for (int i = 0; i < 4; ++i) {
							found[i] = false;
							marks[i] = null;
						}
						for (RequiredSubject subject : requiredSubjects) {
							Mark current = markDAO.selectMarkByAbiturientAndSubject(
									abiturientDAO.selectAbiturientByLogin(login).getID(), subject.getSubj());
							if (current != null) {
								if (marks[subject.getOrdinal() - 1] != null) {
									if (current.getPoints() > marks[subject.getOrdinal() - 1].getPoints()) {
										marks[subject.getOrdinal() - 1] = current;
									}
								} else {
									marks[subject.getOrdinal() - 1] = current;
								}
								found[subject.getOrdinal() - 1] = true;
							} else {
								continue;
							}
						}
						boolean toAdd = true;
						for (int i = 0; i < 4; ++i) {
							toAdd &= found[i];
						}
						return toAdd;
					}
				} catch (SQLException e) {
					System.out.println("Exception when selecting all specialities: " + e);
					LOGGER.error("Exception when selecting all specialities: " + e);
					return null;
				}
			}
		});

	}

}
