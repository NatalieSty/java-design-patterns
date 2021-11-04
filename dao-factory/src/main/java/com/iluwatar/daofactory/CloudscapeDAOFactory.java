package com.iluwatar.daofactory;
import java.sql.*;

public class CloudscapeDAOFactory extends DAOFactory {
    public static final String DRIVER=
            "COM.cloudscape.core.RmiJdbcDriver";
    public static final String DBURL=
            "jdbc:cloudscape:rmi://localhost:1099/CoreJ2EEDB";

    // method to create Cloudscape connections
    public static Connection createConnection() {
        // Use DRIVER and DBURL to create a connection
        // Recommend connection pool implementation/usage
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(DBURL);
            return connection;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } catch (ClassNotFoundException classNotFound) {
            System.out.println( "Driver Not Found");
            System.exit(1);
        }
        return null;
    }
    public UserDAO getUserDAO() {
        // CloudscapeUserDAO implements UserDAO
        return new CloudscapeUserDAO();
    }
//    public AccountDAO getAccountDAO() {
//        // CloudscapeAccountDAO implements AccountDAO
//        return new CloudscapeAccountDAO();
//    }
//    public OrderDAO getOrderDAO() {
//        // CloudscapeOrderDAO implements OrderDAO
//        return new CloudscapeOrderDAO();
//    }
}
