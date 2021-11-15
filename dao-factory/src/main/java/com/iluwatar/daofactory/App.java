package com.iluwatar.daofactory;

import javax.sql.RowSet;
import java.sql.ResultSet;
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
        User u = new User();
        u.setName("Sam Doe");
        u.setStreetAddress("333 4th Street");
        u.setCity("Seattle");
        int newCustNo = userDAO.insertUser(u);
        User user = userDAO.findUser(newCustNo);
        System.out.println(user.getUserId()+ user.getName() + user.getStreetAddress() + user.getCity());
        user.setStreetAddress("12345 8th Street");
        user.setName("Sam Smith");
        user.setCity("York");
        userDAO.updateUser(user);
        System.out.println(user.getUserId()+ user.getName() + user.getStreetAddress() + user.getCity());
        userDAO.deleteUser(user);
        String criteriaCol = "CITY";
        String criteria = "Seattle";
        userDAO.selectUserRS(criteriaCol, criteria);
        Collection<User> userList = userDAO.selectUsersTO(criteriaCol, criteria);
        System.out.println(userList.size());
        for (User uu: userList){
            System.out.println(uu.getUserId());
            System.out.println(uu.getName());
            System.out.println(uu.getStreetAddress());
        }

    }
}
