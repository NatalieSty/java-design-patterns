package com.iluwatar.daofactory;
import java.sql.*;

public class MySQLDAOFactory extends DAOFactory {
	public static final String Driver = "com.mysql.jdbc.Driver";

	//method to create MySQL connection
	public static Connection createConnection(String dburl) {
		try {
			Class.forName(Driver);
			Connection con = DriverManager.getConnection(dburl);
			return con;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found.\n");
			System.exit(1);
		}
		return null;
	}
	public UserDao getUserDao() {
		return new MySQLUserDao();
	}
}
