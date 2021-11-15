package com.iluwatar.daofactory;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.RowSet;
import javax.sql.rowset.BaseRowSet;
import java.util.*;
import java.sql.*;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;


// CloudscapeCustomerDAO implementation of the
// CustomerDAO interface. This class can contain all
// Cloudscape specific code and SQL statements.
// The client is thus shielded from knowing
// these implementation details.
@Slf4j
public class DerbyUserDAO implements UserDAO{
    Connection con = DerbyDAOFactory.createConnection();
    public DerbyUserDAO() {
        // initialization

        String SQL_CREATE = "CREATE TABLE DERBYUSER"
                + "("
                + " ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),"
                + " NAME VARCHAR(140) NOT NULL,"
                + " ADDRESS VARCHAR(140) NOT NULL,"
                + " CITY VARCHAR(140) NOT NULL"
                + ")";
        //Creating the Statement object
        try {
            DatabaseMetaData dbm = con.getMetaData();
            Statement stmt = con.createStatement();
            ResultSet rs = dbm.getTables(null, "APP", "DERBYUSER", null);
            if (!rs.next()) {
                stmt.execute(SQL_CREATE);
                LOGGER.info("Table created");
            }else{
                LOGGER.info("Table already exists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // The following methods can use
    // CloudscapeDAOFactory.createConnection()
    // to get a connection as required
    @Override
    public int insertUser(User user) {
        // Implement insert user here.
        // Return newly created user number
        // or a -1 on error
        int last_inserted_id = -1;
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO DERBYUSER(NAME, ADDRESS, CITY) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getStreetAddress());
            statement.setString(3, user.getCity());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return last_inserted_id;
    }

    @Override
    public boolean deleteUser(User user) {
        // Implement delete customer here
        // Return true on success, false on failure
        int id = user.getUserId();
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM DERBYUSER WHERE ID = ?");

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User findUser(int newCustNo) {
        // Implement find a customer here using supplied
        // argument values as search criteria
        // Return a Transfer Object if found,
        // return null on error or if not found
        User user = new User();
        int id = -1;
        String address = "";
        String name = "";
        String city = "";
        try {
            Statement sta = con.createStatement();

            ResultSet res = sta.executeQuery(
                    "SELECT * FROM DERBYUSER WHERE ID = " + newCustNo);

            while (res.next()) {
                id = res.getInt("ID");
                address = res.getString("ADDRESS");
                name = res.getString("NAME");
                city = res.getString("CITY");
            }
            res.close();
            sta.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        user.setUserId(id);
        user.setName(name);
        user.setStreetAddress(address);
        user.setCity(city);
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        // implement update record here using data
        // from the customerData Transfer Object
        // Return true on success, false on failure or
        // error

        try {
            Statement stmt = con.createStatement();
            int id = user.getUserId();
            String newName = user.getName();
            String newAddress = user.getStreetAddress();
            String newCity = user.getCity();
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE DERBYUSER SET NAME = ? , ADDRESS = ?, CITY = ? WHERE ID = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newAddress);
            preparedStatement.setString(3, newCity);
            preparedStatement.setInt(4, id);
            return preparedStatement.executeUpdate() > 0;

        }catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        }

        return false;
    }

    @Override
    public ResultSet selectUserRS(String criteriaCol, String criteria) {
        // implement search customers here using the
        // supplied criteria.
        // Return a RowSet.
        ResultSet res = null;
        try {
            Statement sta = con.createStatement();
            res = sta.executeQuery("SELECT ID, Address, Name, City FROM DERBYUSER WHERE "+criteriaCol+" = '" + criteria + "'");

            res.close();
            sta.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return res;
    }

    @Override
    public Collection selectUsersTO(String criteriaCol, String criteria) {
        // implement search customers here using the
        // supplied criteria.
        // Alternatively, implement to return a Collection
        // of Transfer Objects.
        ArrayList<User> selectedUsers = new ArrayList<>();

        try {
            Statement sta = con.createStatement();
            ResultSet res = sta.executeQuery("SELECT ID, Address, Name, City FROM DERBYUSER WHERE "+criteriaCol+" = '" + criteria + "'");

            while (res.next()) {
                User user = new User();
                user.setUserId(res.getInt("ID"));
                user.setStreetAddress(res.getString("ADDRESS"));
                user.setName(res.getString("NAME"));
                user.setCity(res.getString("CITY"));
                selectedUsers.add(user);

            }
            res.close();
            sta.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return selectedUsers;
    }

//    private User createUser(ResultSet resultSet) throws SQLException {
//        User user = new User();
//        user.setUserId(resultSet.getInt("ID"));
//        user.setName(resultSet.getString("NAME"));
//        user.setStreetAddress(resultSet.getString("ADDRESS"));
//        user.setCity(resultSet.getString("CITY"));
//        return user;
//    }
}
