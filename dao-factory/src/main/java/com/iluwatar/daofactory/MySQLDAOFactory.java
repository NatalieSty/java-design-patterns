package com.iluwatar.daofactory;
import java.sql.*;

public class MySQLDAOFactory extends DAOFactory {

	//method to create MySQL connection
	public static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/dao-factory?createDatabaseIfNotExist=true";
			con = DriverManager.getConnection(dburl, "root", "");
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
