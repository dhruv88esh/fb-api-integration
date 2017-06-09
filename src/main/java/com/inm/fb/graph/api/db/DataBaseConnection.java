/**
 * 
 */
package com.inm.fb.graph.api.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.inm.fb.graph.api.config.AppProperties;

/**
 * Database connection class
 * 
 * @author dkumar
 * 
 */
public class DataBaseConnection {

	private static final Logger LOGGER = Logger
			.getLogger(DataBaseConnection.class);

	/**
	 * Return the connection for a database
	 * 
	 * @return Connection object
	 */
	public static Connection getConnection() {

		LOGGER.info("Connecting to the postgres db...");
		Connection connection = null;
		try {
			Class.forName(AppProperties.getProperties().getProperty(
					"POSTGRESQL_DRIVER"));
			connection = DriverManager.getConnection(AppProperties
					.getProperties().getProperty("JDBC_POSTGRESQL_URL"),
					AppProperties.getProperties().getProperty("USERNAME"),
					AppProperties.getProperties().getProperty("PASSWORD"));
			LOGGER.info("Connected to database successfully.");
		} catch (Exception e) {
			LOGGER.error("Unable to connect to postgres database.", e);
		}
		return connection;
	}

	/**
	 * Commit and close the connection
	 * 
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.commit();
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.error("Unable to close connection.", e);
		}
	}
}
