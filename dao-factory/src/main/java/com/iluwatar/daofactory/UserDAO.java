package com.iluwatar.daofactory;

// Interface that all UserDAOs must support

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO {
    int insertUser(String name, String address, String city);
    boolean deleteUser();
    User findUser(int newCustNo);
    boolean updateUser();
    RowSet selectUserRS();
    Collection selectUsersTO(User user);
}
