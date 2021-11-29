package com.iluwatar.daofactory;

// Interface that all UserDAOs must support

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO {
    int insertUser(User user);
    boolean deleteUser(User user);
    User findUser(int newUserNo);
    boolean updateUser(User user);
    String selectUserRS(String criteriaCol, String criteria);
    Collection selectUsersTO(String criteriaCol, String criteria);
}
