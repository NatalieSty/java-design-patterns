package com.iluwatar.daofactory;
import java.sql.*;

public class MySQLDAOFactory extends DAOFactory {

	//method to create MySQL connection
	public static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl = "jdbc:mysql://127.0.0.1/mysqldb?useSSL=false&createDatabaseIfNotExist=true";
			con = DriverManager.getConnection(dburl, "root", "password");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found.\n");
			System.exit(1);
		}
		return con;
	}

	@Override
	public UserDAO getUserDAO() {
		return null;
	}
}
