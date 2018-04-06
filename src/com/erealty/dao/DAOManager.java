package com.erealty.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DAOManager {
	protected Connection connection = null;
	protected DataSource dataSource = null;

	public static DAOManager getInstance() {
		return DAOManagerSingleton.INSTANCE.get();
	}

	private DAOManager() throws Exception {
		super();
		dataSource = DataSource.getInstance();

	}

	private static class DAOManagerSingleton {

		public static final ThreadLocal<DAOManager> INSTANCE;

		static
        {
            ThreadLocal<DAOManager> dm;
            try
            {
                dm = new ThreadLocal<DAOManager>(){
                    @Override
                    protected DAOManager initialValue() {
                        try
                        {
                            return new DAOManager();
                        }
                        catch(Exception e)
                        {
                            return null;
                        }
                    }
                };
            }
            catch(Exception e){
                dm = null;
            }
            INSTANCE = dm;
        }
	}

		@SuppressWarnings("unused")
		private static final Logger LOGGER = Logger.getLogger(DAOManager.class);

		protected Connection getConnection() throws SQLException {
			if (this.connection == null) {
				this.connection = dataSource.getConnection();
			}
			return this.connection;
		}

		protected Connection getTxConnection() throws SQLException {
			Connection toReturn = getConnection();
			toReturn.setAutoCommit(false);
			return toReturn;
		}

		protected AdministratorDAO administratorDAO = null;
		protected AbiturientDAO abiturientDAO = null;
		protected FacultyDAO facultyDAO = null;
		protected SpecialityDAO specialityDAO = null;
		protected RequiredSubjectDAO requiredSubjectDAO = null;
		protected MarkDAO markDAO = null;
		protected ApplicationDAO applicationDAO = null;

		public AdministratorDAO getAdministratorDAO() throws SQLException {
			if (this.administratorDAO == null) {
				this.administratorDAO = new AdministratorDAO(getConnection());
			}
			return administratorDAO;
		}

		public AbiturientDAO getAbiturientDAO() throws SQLException {
			if (this.abiturientDAO == null) {
				this.abiturientDAO = new AbiturientDAO(getConnection());
			}
			return abiturientDAO;
		}

		public FacultyDAO getFacultyDAO() throws SQLException {
			if (this.facultyDAO == null) {
				this.facultyDAO = new FacultyDAO(getConnection());
			}
			return facultyDAO;
		}

		public SpecialityDAO getSpecialityDAO() throws SQLException {
			if (this.specialityDAO == null) {
				this.specialityDAO = new SpecialityDAO(getConnection());
			}
			return specialityDAO;
		}

		public RequiredSubjectDAO getRequiredSubjectDAO() throws SQLException {
			if (this.requiredSubjectDAO == null) {
				this.requiredSubjectDAO = new RequiredSubjectDAO(getConnection());
			}
			return requiredSubjectDAO;
		}

		public MarkDAO getMarkDAO() throws SQLException {
			if (this.markDAO == null) {
				this.markDAO = new MarkDAO(getConnection());
			}
			return markDAO;
		}

		public ApplicationDAO getApplicationDAO() throws SQLException {
			if (this.applicationDAO == null) {
				this.applicationDAO = new ApplicationDAO(getConnection());
			}
			return applicationDAO;
		}

		public AdministratorDAO getUserDAOTx() throws SQLException {
			if (this.administratorDAO == null) {
				this.administratorDAO = new AdministratorDAO(getTxConnection());
			}
			return administratorDAO;
		}

		public AbiturientDAO getAbiturientDAOTx() throws SQLException {
			if (this.abiturientDAO == null) {
				this.abiturientDAO = new AbiturientDAO(getTxConnection());
			}
			return abiturientDAO;
		}

		public FacultyDAO getFacultyDAOTx() throws SQLException {
			if (this.facultyDAO == null) {
				this.facultyDAO = new FacultyDAO(getTxConnection());
			}
			return facultyDAO;
		}

		public SpecialityDAO getSpecialityDAOTx() throws SQLException {
			if (this.specialityDAO == null) {
				this.specialityDAO = new SpecialityDAO(getTxConnection());
			}
			return specialityDAO;
		}

		public RequiredSubjectDAO getRequiredSubjectDAOTx() throws SQLException {
			if (this.requiredSubjectDAO == null) {
				this.requiredSubjectDAO = new RequiredSubjectDAO(getTxConnection());
			}
			return requiredSubjectDAO;
		}

		public MarkDAO getMarkDAOTx() throws SQLException {
			if (this.markDAO == null) {
				this.markDAO = new MarkDAO(getTxConnection());
			}
			return markDAO;
		}

		public ApplicationDAO getApplicationDAOTx() throws SQLException {
			if (this.applicationDAO == null) {
				this.applicationDAO = new ApplicationDAO(getTxConnection());
			}
			return applicationDAO;
		}

		public Object executeAndClose(DAOCommand command) throws SQLException {
			try {
				return command.execute(this);
			} finally {
				getConnection().close();
			}
		}
		
		public Object execute(DAOCommand command) throws SQLException {
			return command.execute(this);			
		}

		public Object transaction(DAOCommand command) throws SQLException {
			try {
				getConnection().setAutoCommit(false);
				Object returnValue = command.execute(this);
				getConnection().commit();
				return returnValue;
			} catch (Exception e) {
				getConnection().rollback();
				throw e; // or wrap it before rethrowing it
			} finally {
				getConnection().setAutoCommit(true);
			}
		}

		public Object transactionAndClose(DAOCommand command) throws SQLException {
			return executeAndClose(new DAOCommand() {
				public Object execute(DAOManager manager) throws SQLException {
					return manager.transaction(command);
				}
			});
		}

}