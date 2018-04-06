package com.erealty.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.erealty.dao.AbiturientDAO;
import com.erealty.dao.ApplicationDAO;
import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.dao.SpecialityDAO;
import com.erealty.model.Abiturient;
import com.erealty.model.Application;
import com.erealty.model.Speciality;

public class ApplicationsService {
	private DAOManager daoManager;
	private static final Logger LOGGER = Logger.getLogger(ApplicationsService.class);

	public ApplicationsService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Application> getApplicationsBySpeciality(Speciality speciality, Boolean active) throws SQLException {
		return (List<Application>) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					int specialityID = speciality.getID();
					ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
					List<Application> applications = applicationDAO.selectApplicationsBySpeciality(specialityID,null);
					return applications;
				} catch (SQLException e) {
					System.out.println("Exception when selecting all specialities: "+e);
					LOGGER.error("Exception when selecting all specialities: "+e);
					return null;
				}
			}
		});
	}

	public Application getApplicationsBySpecialityAndAbiturient(String specialityCode, String login)  {
		try {
			return (Application) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
						SpecialityDAO specialityDAO = daoManager.getSpecialityDAOTx();
						Speciality s = specialityDAO.selectSpecialityByCode(specialityCode);
						AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
						Abiturient a = abiturientDAO.selectAbiturientByLogin(login);
						Application application = applicationDAO.selectApplicationBySpecialityAndAbiturient(s.getID(), a.getID());
						return application;
					} catch (SQLException e) {
						System.out.println("Exception when selecting all specialities: "+e);
						LOGGER.error("Exception when selecting all specialities: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when selecting all specialities: "+e);
			LOGGER.error("Exception when selecting all specialities: "+e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Boolean> getSpecialitiesToWhichApplied(String login)  {
		try {
			return (Map<String,Boolean>) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
						AbiturientDAO abiturientDAO = daoManager.getAbiturientDAOTx();
						SpecialityDAO specialityDAO = daoManager.getSpecialityDAOTx();
						Abiturient a = abiturientDAO.selectAbiturientByLogin(login);
						List<Application> applications = applicationDAO.selectApplicationByAbiturient(a.getID());
						Map<String,Boolean> specialitiesNames = new HashMap<>();
						for(Application application:applications){
							specialitiesNames.put(specialityDAO.selectSpecialityByID(application.getSpecialityID()).getName(),application.getActive());
						}
						return specialitiesNames;
					} catch (SQLException e) {
						System.out.println("Exception when selecting all specialities: "+e);
						LOGGER.error("Exception when selecting all specialities: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when selecting all specialities: "+e);
			LOGGER.error("Exception when selecting all specialities: "+e);
			return null;
		}
	}

	public Integer getNotActiveApplicationsCount() {
		try {
			return (Integer) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
						Integer notActiveApplicationsCount = applicationDAO.countNotActiveApplications();
						return notActiveApplicationsCount;
					} catch (SQLException e) {
						System.out.println("Exception when selecting all specialities: "+e);
						LOGGER.error("Exception when selecting all specialities: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when selecting all specialities: "+e);
			LOGGER.error("Exception when selecting all specialities: "+e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Application> getNotActiveApplications() {
		try {
			return (List<Application>) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
						List<Application> applications = applicationDAO.selectNotActiveApplications();
						return applications;
					} catch (SQLException e) {
						System.out.println("Exception when selecting all applications: "+e);
						LOGGER.error("Exception when selecting all applications: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when selecting all applications: "+e);
			LOGGER.error("Exception when selecting all applications: "+e);
		}
		return null;
	}

	public Boolean activateApplicationByID(Integer applicationID) {
			try {
				return (Boolean) daoManager.transaction(new DAOCommand() {
					@Override
					public Object execute(DAOManager daoManager) {
						try {
							ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
							applicationDAO.activateByID(applicationID);
							return true;
						} catch (SQLException e) {
							System.out.println("Exception when activating application: "+e);
							LOGGER.error("Exception when activating application: "+e);
							return null;
						}
					}
				});
			} catch (SQLException e) {
				System.out.println("Exception when activating application: "+e);
				LOGGER.error("Exception when activating application: "+e);
			}		
		return null;
	}

	public Boolean deleteApplicationByID(Integer applicationID) {
		try {
			return (Boolean) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						ApplicationDAO applicationDAO = daoManager.getApplicationDAOTx();
						applicationDAO.deleteByID(applicationID);
						return true;
					} catch (SQLException e) {
						System.out.println("Exception when selecting all application: "+e);
						LOGGER.error("Exception when selecting all application: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
			System.out.println("Exception when activating application: "+e);
			LOGGER.error("Exception when activating application: "+e);
		}
		return null;
	}


}
