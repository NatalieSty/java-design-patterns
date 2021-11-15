package com.iluwatar.daofactory;
import java.sql.*;

public class DerbyDAOFactory extends DAOFactory {

    // method to create Cloudscape connections
    public static Connection createConnection() {
        // Use DRIVER and DBURL to create a connection
        // Recommend connection pool implementation/usage

        Connection conn1 = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // connect method #1 - embedded driver
            String dbURL1 = "jdbc:derby:dao-factory/DerbyDB;create=true";
            conn1 = DriverManager.getConnection(dbURL1);
            if (conn1 != null) {
                System.out.println("Connected to database #1");
            }


        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn1;
    }
    @Override
    public UserDAO getUserDAO() {
        // DerbyUserDAO implements UserDAO
        return new DerbyUserDAO();
    }

}
