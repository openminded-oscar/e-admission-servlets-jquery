package com.erealty.dao;

import java.sql.SQLException;

public interface DAOCommand {
	public Object execute(DAOManager daoManager) throws SQLException;
}
