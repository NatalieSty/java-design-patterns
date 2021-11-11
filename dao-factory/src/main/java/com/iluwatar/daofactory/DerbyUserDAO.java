package com.iluwatar.daofactory;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.RowSet;
import javax.sql.rowset.BaseRowSet;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;
import java.util.function.BiPredicate;

// CloudscapeCustomerDAO implementation of the
// CustomerDAO interface. This class can contain all
// Cloudscape specific code and SQL statements.
// The client is thus shielded from knowing
// these implementation details.
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
        Statement stmt = null;
        try {
            stmt = con.createStatement();
//            stmt.execute(SQL_CREATE);
            if (!isTableExist("DERBYUSER")) {
                stmt.execute(SQL_CREATE);
                System.out.println("Table created");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // The following methods can use
    // CloudscapeDAOFactory.createConnection()
    // to get a connection as required
    @Override
    public int insertUser(String name, String address, String city) {
        // Implement insert user here.
        // Return newly created user number
        // or a -1 on error
        int last_inserted_id = -1;
        try {
            String query = "INSERT INTO DERBYUSER(NAME, ADDRESS, CITY) VALUES ('"+name+"', '"+address+"', '"+city+"')";
            PreparedStatement prest;
            prest = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            prest.executeUpdate();

            ResultSet rs = prest.getGeneratedKeys();
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
        Boolean isDeleted = false;
        int id = user.getUserId();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String query = "DELETE FROM DERBYUSER WHERE ID = "+id;
            int num = stmt.executeUpdate(query);
            System.out.println("Number of records deleted are: "+num);
            if (num > 0) {
                isDeleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
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
//            con.close();
        } catch (Exception e) {
            System.err.println("Exception: "+e.getMessage());
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
        Boolean isUpdated = false;
        try {
            Statement stmt = con.createStatement();
            int id = user.getUserId();
            String newName = user.getName();
            String newAddress = user.getStreetAddress();
            String newCity = user.getCity();
            String query = "UPDATE DERBYUSER SET NAME = '"+newName+"', ADDRESS='"+newAddress+"', CITY='"+newCity+"' WHERE ID = "+id+"";
            int num = stmt.executeUpdate(query);
            System.out.println("Number of records updated are: "+num);
            if (num > 0) {
                isUpdated = true;
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return isUpdated;
    }

    @Override
    public ResultSet selectUserRS(String criteriaCol, String criteria) {
        // implement search customers here using the
        // supplied criteria.
        // Return a RowSet.
        ResultSet res = null;
//        JdbcRowSet rs = new JdbcRowSetImpl(con);
//		rs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);

        try {
            Statement sta = con.createStatement();
            res = sta.executeQuery("SELECT ID, Address, Name, City FROM DERBYUSER WHERE CITY = '" + criteria + "'");

//            while (res.next()) {
//                System.out.println(res.getInt("ID"));
//                System.out.println(res.getString("ADDRESS"));
//                System.out.println(res.getString("NAME"));
//                System.out.println(res.getString("CITY"));
//
//            }
            res.close();
            sta.close();
        } catch (Exception e) {
            System.err.println("Exception: "+e.getMessage());
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
            ResultSet res = sta.executeQuery("SELECT ID, Address, Name, City FROM DERBYUSER WHERE CITY = '" + criteria + "'");

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
            System.err.println("Exception: "+e.getMessage());
        }
        return selectedUsers;
    }

    public boolean isTableExist(String sTablename) throws SQLException{
        if(con!=null)
        {
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, sTablename.toUpperCase(),null);
            if(rs.next())
            {
                System.out.println("Table "+rs.getString("TABLE_NAME")+"already exists !!");
            }
            else
            {
                System.out.println("Write your create table function here !!!");
            }
            return true;
        }
        return false;
    }
}
