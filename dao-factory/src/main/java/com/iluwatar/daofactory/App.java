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
        DAOFactory derbyFactory = DAOFactory.getDAOFactory(DAOFactory.DERBY);

        // Create a DAO
        UserDAO userDAO = derbyFactory.getUserDAO();
        DerbyDAOFactory.createConnection();
        // create a new customer
        int newCustNo = userDAO.insertUser("Sam Doe", "333 4th Street", "Seattle");
        User user = userDAO.findUser(newCustNo);
        System.out.println(user.getUserId()+ user.getName() + user.getStreetAddress() + user.getCity());
        user.setStreetAddress("12345 8th Street");
        user.setName("Sam Smith");
        user.setCity("New York");
        userDAO.updateUser();
        userDAO.deleteUser();

        User criteria=new User();
        criteria.setCity("New York");
        Collection customersList = userDAO.selectUsersTO(criteria);

    }
}
