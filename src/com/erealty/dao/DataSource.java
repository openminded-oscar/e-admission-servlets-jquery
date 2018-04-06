package com.erealty.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

    private static DataSource     datasource;
    private ComboPooledDataSource cpds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/admission?characterEncoding=UTF-8");
        cpds.setUser("root");
        cpds.setPassword("root");

        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(7);
        cpds.setAcquireIncrement(15);
        cpds.setMaxPoolSize(1000);
        cpds.setMinPoolSize(3);
        cpds.setMaxStatements(10);

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}