package com.iluwatar.daofactory;

import java.sql.SQLException;
import java.util.Collection;

public class App {
    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        // create the required DAO Factory
        DAOFactory cloudscapeFactory = DAOFactory.getDAOFactory(DAOFactory.CLOUDSCAPE);

        // Create a DAO
        UserDAO userDAO = cloudscapeFactory.getUserDAO();
        CloudscapeDAOFactory.createConnection();
        // create a new customer
        int newCustNo = userDAO.insertUser("Sam Doe", "333 4th Street, Seattle, WA, 98001");
        User user = userDAO.findUser(newCustNo);
        user.setStreetAddress("12345 8th Street, Seattle, WA, 98001");
        user.setName("Sam Smith");
        userDAO.updateUser();
        userDAO.deleteUser();

        User criteria=new User();
        criteria.setCity("New York");
        Collection customersList = userDAO.selectUsersTO(criteria);

    }
}
