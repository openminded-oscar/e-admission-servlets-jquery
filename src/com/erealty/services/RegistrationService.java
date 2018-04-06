package com.erealty.services;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.erealty.dao.AbiturientDAO;
import com.erealty.dao.DAOCommand;
import com.erealty.dao.DAOManager;
import com.erealty.model.Abiturient;

public class RegistrationService {
	private DAOManager daoManager;
	private static final Logger LOGGER = Logger.getLogger(RegistrationService.class);

	public RegistrationService(DAOManager daoManager) {
		super();
		this.daoManager = daoManager;
	}

	public Boolean register(Abiturient abiturient) throws SQLException{
		return (Boolean) daoManager.transaction(new DAOCommand() {
			@Override
			public Object execute(DAOManager daoManager) {
				try {
					AbiturientDAO userDAO = daoManager.getAbiturientDAO();
					Boolean result = userDAO.createAbiturient(abiturient);
					return result;
				} catch (SQLException e) {
					LOGGER.error("Exception when registering new user: "+e);
					return null;
				}
			}
		});
	}
}
