package com.iluwatar.daofactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MySQLDAOFactoryTest {

    @Test
    void getUserDAOTest() {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        UserDAO userDAO = mySQLFactory.getUserDAO();
        assertTrue(userDAO instanceof MySQLUserDAO);
    }
}