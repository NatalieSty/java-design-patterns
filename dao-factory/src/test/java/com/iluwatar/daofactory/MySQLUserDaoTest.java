package com.iluwatar.daofactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class MySQLUserDaoTest {
    private MySQLUserDao dao;
    private User user = new User();
    private Connection con;
    /**
     * Creates customers schema.
     *
     * @throws SQLException if there is any error while creating schema.
     */
    @BeforeEach
    void createSchema() throws SQLException {
        this.con = MySQLDAOFactory.createConnection();
        String SQL_CREATE = "CREATE TABLE MYSQLUSER"
                + "("
                + " ID INT NOT NULL AUTO_INCREMENT,"
                + " NAME VARCHAR(140) NOT NULL,"
                + " ADDRESS VARCHAR(140) NOT NULL,"
                + " CITY VARCHAR(140) NOT NULL,"
                + " PRIMARY KEY ( ID ) "
                + ")";
        //Creating the Statement object
        try {
            DatabaseMetaData dbm = con.getMetaData();
            Statement stmt = con.createStatement();
            ResultSet rs = dbm.getTables(null, "APP", "MYSQLUSER", null);
            if (!rs.next()) {
                stmt.execute(SQL_CREATE);
                System.out.println("Table created");
            }else{
                System.out.println("already exists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Represents the scenario where DB connectivity is present.
     */
    @Nested
    public class ConnectionSuccess {

        /**
         * Setup for connection success scenario.
         *
         * @throws Exception if any error occurs.
         */
        @BeforeEach
        public void setUp() throws Exception {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            dao = (MySQLUserDao) mysqlFactory.getUserDAO();
            user.setName("Sam Doe");
            user.setStreetAddress("333 4th Street");
            user.setCity("Seattle");
            int newCustNo = dao.insertUser(user);
            user.setUserId(newCustNo);
            assertNotNull(newCustNo);
        }

        /**
         * Represents the scenario when DAO operations are being performed on a non existing User.
         */
        @Nested
        class NonExistingUser {

            @Test
            void addingShouldResultInSuccess() throws Exception {


                final var nonExistingUser = new User();
                nonExistingUser.setName("Robert True");
                nonExistingUser.setStreetAddress("1234 123th AVE");
                nonExistingUser.setCity("York");
                var result = dao.insertUser(nonExistingUser);
                nonExistingUser.setUserId(result);
                assertNotNull(result);

                assertEquals(nonExistingUser.getUserId(), dao.findUser(result).getUserId());
            }

            @Test
            void deletionShouldBeFailure() throws Exception {
                final var nonExistingUser = new User();
                nonExistingUser.setName("Robert True");
                var result = dao.deleteUser(nonExistingUser);

                assertFalse(result);
            }

            @Test
            void updateShouldBeFailure() throws Exception {
                final var nonExistingId = getNonExistingUserId();
                final var newName = "Douglas MacArthur";
                final var user = new User();
                user.setName(newName);
                var result = dao.updateUser(user);
                assertFalse(result);
            }

            @Test
            void retrieveShouldReturnNoUser() throws Exception {
                assertEquals(dao.findUser(getNonExistingUserId()).getUserId(), -1);
            }
        }

        /**
         * Represents a scenario where DAO operations are being performed on an already existing
         * customer.
         */
        @Nested
        class ExistingCustomer {

            @Test
            void deletionShouldBeSuccessAndUserShouldBeNonAccessible() throws Exception {

                var result = dao.deleteUser(user);
                assertTrue(result);
            }

            @Test
            void selectUserRSWithMoreUsers() throws Exception {
                final var newName = "Bernard GG";
                final var newAddress = "444 3th AVE";
                final var newCity = "Seattle";
                final var newUser = new User();
                newUser.setUserId(user.getUserId());
                newUser.setName(newName);
                newUser.setStreetAddress(newAddress);
                newUser.setCity(newCity);
                dao.insertUser(newUser);
                final String criteriaCol = "CITY";
                final String criteria = "Seattle";
                ResultSet rs = dao.selectUserRS(criteriaCol, criteria);
                assertNotNull(rs);
            }

            @Test
            void selectUserTOWithMoreUsers() throws Exception {
                final var newName = "Bernard GG";
                final var newAddress = "444 3th AVE";
                final var newCity = "Seattle";
                final var newUser = new User();
                newUser.setUserId(user.getUserId());
                newUser.setName(newName);
                newUser.setStreetAddress(newAddress);
                newUser.setCity(newCity);
                dao.insertUser(newUser);
                final String criteriaCol = "CITY";
                final String criteria = "Seattle";
                Collection<User> userList = dao.selectUsersTO(criteriaCol, criteria);
                assertNotNull(userList);
                assertEquals(userList.size(), 2);

            }

            @Test
            void updateShouldBeSuccessAndAccessingTheSameUserShouldReturnUpdatedInformation() throws
                    Exception {
                final var newName = "Bernard GG";
                final var newAddress = "444 3th AVE";
                final var newCity = "Jersey";
                final var newUser = new User();
                newUser.setUserId(user.getUserId());
                newUser.setName(newName);
                newUser.setStreetAddress(newAddress);
                newUser.setCity(newCity);
                var result = dao.updateUser(newUser);

                assertTrue(result);

                final var u = dao.findUser(user.getUserId());
                assertEquals(newName, u.getName());
            }
        }
    }


    /**
     * Delete customer schema for fresh setup per test.
     *
     * @throws SQLException if any error occurs.
     */
    @AfterEach
    void deleteSchema() throws SQLException {
        final String DELETE_SCHEMA_SQL = "DROP TABLE MYSQLUSER";
        try (Connection con = MySQLDAOFactory.createConnection();
             var statement = con.createStatement()) {
            statement.execute(DELETE_SCHEMA_SQL);
        }
    }

    /**
     * An arbitrary number which does not correspond to an active User id.
     *
     * @return an int of a customer id which doesn't exist
     */
    private int getNonExistingUserId() {
        return 999;
    }
}
