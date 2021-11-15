package com.iluwatar.daofactory;

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        // create the required DAO Factory
        DAOFactory derbyFactory = DAOFactory.getDAOFactory(DAOFactory.DERBY);

        // Create a DAO for Derby
        UserDAO derbyUserDAO = derbyFactory.getUserDAO();
        DerbyDAOFactory.createConnection();
        // create, update, find a customer, or search by criteria
        int userId = createUser(derbyUserDAO);
        User user = findUser(userId, derbyUserDAO);
        updateUser(user, derbyUserDAO);
        deleteUser(user, derbyUserDAO);
        String criteriaCol = "City";
        String criteria = "Seattle";
        findUserWithCriteria(derbyUserDAO, criteriaCol, criteria);

    }

    private static int createUser(UserDAO userDAO){
        User u = new User();
        u.setName("Sam Doe");
        u.setStreetAddress("333 4th Street");
        u.setCity("Seattle");
        int newCustNo = userDAO.insertUser(u);

        return newCustNo;
    }

    private static User findUser(int id, UserDAO userDAO){
        User user = userDAO.findUser(id);
        LOGGER.info("User Created: " + user.getUserId());
        return user;
    }

    private static User updateUser(User user, UserDAO userDAO){
        user.setStreetAddress("12345 8th Street");
        user.setName("Sam Smith");
        user.setCity("York");
        userDAO.updateUser(user);
        LOGGER.info("User Updated: " + user.getUserId());
        return user;
    }

    private static void deleteUser(User user, UserDAO userDAO){
        userDAO.deleteUser(user);
        LOGGER.info("User Deleted: " + user.getUserId());
    }

    private static void findUserWithCriteria(UserDAO userDAO, String criteriaCol, String criteria){
        Collection<User> userList = userDAO.selectUsersTO(criteriaCol, criteria);
        LOGGER.info("Found "+ userList.size() +" Users With "+criteriaCol+ " = "+criteria+":");
        for (User i: userList){
            LOGGER.info("User "+i.getUserId());
        }
    }

}
