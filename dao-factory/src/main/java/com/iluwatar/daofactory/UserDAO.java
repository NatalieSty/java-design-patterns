package com.iluwatar.daofactory;

// Interface that all UserDAOs must support

import javax.sql.RowSet;
import java.util.Collection;

public interface UserDAO {
    int insertUser();
    boolean deleteUser();
    User findUser();
    boolean updateUser();
    RowSet selectUserRS();
    Collection selectUsersTO(User user);
}
