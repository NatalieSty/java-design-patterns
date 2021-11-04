package com.iluwatar.daofactory;

import java.util.Collection;

public class App {
    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        // create the required DAO Factory
        DAOFactory cloudscapeFactory =
                DAOFactory.getDAOFactory(DAOFactory.CLOUDSCAPE);

// Create a DAO
        UserDAO userDAO =
                cloudscapeFactory.getUserDAO();

// create a new customer
        int newCustNo = userDAO.insertUser();

// Find a customer object. Get the Transfer Object.
        User user = userDAO.findUser();

// modify the values in the Transfer Object.
        user.setStreetAddress("12345 8th Street, Seattle, WA, 98001");
        user.setName("Sam Smith");
// update the customer object using the DAO
        userDAO.updateUser();

// delete a customer object
        userDAO.deleteUser();
// select all customers in the same city
        User criteria=new User();
        criteria.setCity("New York");
        Collection customersList =
                userDAO.selectUsersTO(criteria);
// returns customersList - collection of Customer
// Transfer Objects. iterate through this collection to
// get values.
    }
}
