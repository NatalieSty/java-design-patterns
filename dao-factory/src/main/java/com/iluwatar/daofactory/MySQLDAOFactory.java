package com.iluwatar.daofactory;
import java.sql.*;

public class MySQLDAOFactory extends DAOFactory {

	//method to create MySQL connection
	public static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql:dao-factory/MySQLDB;create=true";
			con = DriverManager.getConnection(dburl);
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
