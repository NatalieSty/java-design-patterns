package com.iluwatar.daofactory;

import javax.sql.RowSet;
import java.util.Collection;
import java.sql.*;

// CloudscapeCustomerDAO implementation of the
// CustomerDAO interface. This class can contain all
// Cloudscape specific code and SQL statements.
// The client is thus shielded from knowing
// these implementation details.
public class CloudscapeUserDAO implements UserDAO{

    public CloudscapeUserDAO() {
        // initialization
    }
    // The following methods can use
    // CloudscapeDAOFactory.createConnection()
    // to get a connection as required
    @Override
    public int insertUser() {
        // Implement insert user here.
        // Return newly created user number
        // or a -1 on error
        return 0;
    }

    @Override
    public boolean deleteUser() {
        // Implement delete customer here
        // Return true on success, false on failure
        return false;
    }

    @Override
    public User findUser() {
        // Implement find a customer here using supplied
        // argument values as search criteria
        // Return a Transfer Object if found,
        // return null on error or if not found
        return null;
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
}
