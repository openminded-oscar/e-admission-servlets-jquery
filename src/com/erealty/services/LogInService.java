package com.erealty.services;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.model.Abiturient;
import com.erealty.model.Administrator;

public class LogInService {
	private DAOManager daoManager;
	private static final Logger LOGGER = Logger.getLogger(LogInService.class);


	public LogInService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}
	
	public Administrator selectAdminByLogin(String login) {
		try {
			return (Administrator) daoManager.transaction(new DAOCommand() {
				@Override
				public Object execute(DAOManager daoManager) {
					try {
						Administrator admin;
						admin = daoManager.getAdministratorDAO().selectAdminByLogin(login);
						return admin;
					} catch (SQLException e) {
						LOGGER.error("Exception when registering new user: "+e);
						return null;
					}
				}
			});
		} catch (SQLException e) {
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
						Abiturient abitur;
						abitur = daoManager.getAbiturientDAO().selectAbiturientByLogin(login);
						return abitur;
					} catch (SQLException e) {
						LOGGER.error("Exception when selecting abitur: "+e);
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
}
