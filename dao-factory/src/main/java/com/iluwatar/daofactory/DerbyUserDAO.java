package com.iluwatar.daofactory;

import javax.sql.RowSet;
import java.util.Collection;
import java.sql.*;

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
    public boolean deleteUser() {
        // Implement delete customer here
        // Return true on success, false on failure
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

// getting the data back
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
            con.close();
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
    public boolean updateUser() {
        // implement update record here using data
        // from the customerData Transfer Object
        // Return true on success, false on failure or
        // error
        return false;
    }

    @Override
    public RowSet selectUserRS() {
        // implement search customers here using the
        // supplied criteria.
        // Return a RowSet.
        return null;
    }

    @Override
    public Collection selectUsersTO(User criteria) {
        // implement search customers here using the
        // supplied criteria.
        // Alternatively, implement to return a Collection
        // of Transfer Objects.
        return null;
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
