package br.com.betha_project.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection
					("jdbc:postgresql://localhost:5432/betha", "postgres", "postgres");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
