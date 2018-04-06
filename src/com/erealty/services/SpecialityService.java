package com.erealty.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.erealty.dao.ApplicationDAO;
import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.dao.FacultyDAO;
import com.erealty.dao.MarkDAO;
import com.erealty.dao.SpecialityDAO;
import com.erealty.model.Abiturient;
import com.erealty.model.Applicant;
import com.erealty.model.Application;
import com.erealty.model.Mark;
import com.erealty.model.Speciality;
import com.erealty.viewutils.AbiturientUtil;

public class SpecialityService {
	private DAOManager daoManager;
	private static final Logger LOGGER = Logger.getLogger(SpecialityService.class);

	public SpecialityService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}

	@SuppressWarnings("unchecked")
	public List<Speciality> getAllSpecialities() throws SQLException {
		return (List<Speciality>) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					SpecialityDAO specialityDAO = daoManager.getSpecialityDAOTx();
					List<Speciality> result = specialityDAO.selectAllSpecialitiesByFaculty(null);
					return result;
				} catch (SQLException e) {
					System.out.println("Exception when selecting all specialities: " + e);
					LOGGER.error("Exception when selecting all specialities: " + e);
					return null;
				}
			}
		});
	}

	public String getFacultyNameBySpeciality(Speciality speciality) throws SQLException {
		return (String) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					int facultyID = speciality.getFacultyID();
					FacultyDAO facultyDAO = daoManager.getFacultyDAOTx();
					String facultyName = facultyDAO.selectFacultyById(facultyID).getTitle();
					return facultyName;
				} catch (SQLException e) {
					System.out.println("Exception when selecting all specialities: " + e);
					LOGGER.error("Exception when selecting all specialities: " + e);
					return null;
				}
			}
		});
	}

	public Integer getApplicationNumberBySpeciality(Speciality speciality, Boolean active) throws SQLException {
		return (Integer) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					int specialityID = speciality.getID();
					ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
					Integer applicationNumber = applicationDAO.selectApplicationsBySpeciality(specialityID, active)
							.size();
					return applicationNumber;
				} catch (SQLException e) {
					System.out.println("Exception when selecting all specialities: " + e);
					LOGGER.error("Exception when selecting all specialities: " + e);
					return null;
				}
			}
		});
	}

	public Speciality getSpecialityByCode(String code) {
		try {
			return (Speciality) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						SpecialityDAO specialityDAO = daoManager.getSpecialityDAOTx();
						Speciality speciality = specialityDAO.selectSpecialityByCode(code);
						return speciality;
					} catch (SQLException e) {
						System.out.println("Exception when selecting specialities by code: " + e);
						LOGGER.error("Exception when selecting specialities by code: " + e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Applicant> getApplicantsBySpecialityCode(String code) {
		try {
			return (List<Applicant>) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						List<Applicant> result = new ArrayList<>();
						List<Abiturient> abiturients = new ArrayList<>();
						ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
						SpecialityDAO specialityDAO = daoManager.getSpecialityDAOTx();
						Speciality speciality = specialityDAO.selectSpecialityByCode(code);
						List<Application> applications = applicationDAO.selectApplicationsBySpeciality(speciality.getID(),
								true);
						System.out.println(applications.size());
						for (Application application : applications) {
							Mark m = daoManager.getMarkDAOTx().selectMarkByID(application.getMark1ID());
							Abiturient abiturient = daoManager.getAbiturientDAOTx().selectAbiturientById(m.getAbiturientID());
							abiturients.add(abiturient);
						}

						for (Abiturient abiturient : abiturients) {
							MarkDAO markDAO = daoManager.getMarkDAOTx();
							List<Mark> marks = markDAO.selectMarkByAbiturient(abiturient.getID());
							result.add(new Applicant(AbiturientUtil.getFullName(abiturient), marks.get(0).getSubj(),
									marks.get(0).getPoints(), marks.get(1).getSubj(), marks.get(1).getPoints(),
									marks.get(2).getSubj(), marks.get(2).getPoints(), marks.get(3).getSubj(),
									marks.get(3).getPoints()));
						}
						Collections.sort(result);
						return result;
					} catch (SQLException e) {
						System.out.println("Exception when selecting all applicants: " + e);
						LOGGER.error("Exception when selecting all applicants: " + e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(
				new SpecialityService(DAOManager.getInstance()).getApplicantsBySpecialityCode("08.030101").size());
	}

	public Speciality getSpecialityByID(Integer specialityID)  {
		try {
			return (Speciality) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						SpecialityDAO specialityDAO = daoManager.getSpecialityDAOTx();
						Speciality speciality = specialityDAO.selectSpecialityByID(specialityID);
						return speciality;
					} catch (SQLException e) {
						System.out.println("Exception when selecting specialities by ID: " + e);
						LOGGER.error("Exception when selecting specialities by ID: " + e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when selecting specialities by ID: " + e);
			LOGGER.error("Exception when selecting specialities by ID: " + e);
		}
		return null;

	}
}
