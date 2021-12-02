//package com.iluwatar.daofactory;
//
//import javax.sql.RowSet;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.sql.*;
//import lombok.extern.slf4j.Slf4j;
//@Slf4j
//public class MySQLUserDao implements UserDAO {
//	Connection con = MySQLDAOFactory.createConnection();
//	public MySQLUserDao() {
//		 String SQL_CREATE =  "CREATE TABLE MYSQLUSER"
//                + "("
//                + " ID INT NOT NULL AUTO_INCREMENT,"
//                + " NAME VARCHAR(140) NOT NULL,"
//                + " ADDRESS VARCHAR(140) NOT NULL,"
//                + " CITY VARCHAR(140) NOT NULL,"
//                + " PRIMARY KEY ( ID ) "
//                + ")";
//        //Creating the Statement object
//        try {
//            DatabaseMetaData dbm = con.getMetaData();
//            Statement stmt = con.createStatement();
//            ResultSet rs = dbm.getTables(null, "APP", "MYSQLUSER", null);
//            if (!rs.next()) {
//                stmt.execute(SQL_CREATE);
//                LOGGER.info("Table created");
//            }else{
//                LOGGER.info("Table already exists");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//	@Override
//	public int insertUser(User user) {
//        int last_inserted_id = -1;
//        try {
//            PreparedStatement statement = con.prepareStatement("INSERT INTO MYSQLUSER(NAME, ADDRESS, CITY) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, user.getName());
//            statement.setString(2, user.getStreetAddress());
//            statement.setString(3, user.getCity());
//            statement.execute();
//            ResultSet rs = statement.getGeneratedKeys();
//            if (rs.next()) {
//                last_inserted_id = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return last_inserted_id;
//    }
//	//tableName = name of table to delete from
//	//where = where condition for deletion
//	@Override
//	public boolean deleteUser(User user) {
//        int id = user.getUserId();
//        try {
//            PreparedStatement stmt = con.prepareStatement("DELETE FROM MYSQLUSER WHERE ID = ?");
//
//            stmt.setInt(1, id);
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//
//	@Override
//	public User findUser(int newCustNo) {
//        User user = new User();
//        int id = -1;
//        String address = "";
//        String name = "";
//        String city = "";
//        try {
//            Statement sta = con.createStatement();
//
//            ResultSet res = sta.executeQuery(
//                    "SELECT * FROM MYSQLUSER WHERE ID = " + newCustNo);
//
//            while (res.next()) {
//                id = res.getInt("ID");
//                address = res.getString("ADDRESS");
//                name = res.getString("NAME");
//                city = res.getString("CITY");
//            }
//            res.close();
//            sta.close();
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//        user.setUserId(id);
//        user.setName(name);
//        user.setStreetAddress(address);
//        user.setCity(city);
//        return user;
//    }
//
//	@Override
//	public boolean updateUser(User user) {
//        try {
//            Statement stmt = con.createStatement();
//            int id = user.getUserId();
//            String newName = user.getName();
//            String newAddress = user.getStreetAddress();
//            String newCity = user.getCity();
//            PreparedStatement preparedStatement = con.prepareStatement("UPDATE MYSQLUSER SET NAME = ? , ADDRESS = ?, CITY = ? WHERE ID = ?");
//            preparedStatement.setString(1, newName);
//            preparedStatement.setString(2, newAddress);
//            preparedStatement.setString(3, newCity);
//            preparedStatement.setInt(4, id);
//            return preparedStatement.executeUpdate() > 0;
//
//        }catch (SQLException throwables) {
//            LOGGER.error(throwables.getMessage());
//        }
//
//        return false;
//    }
//
//
//    @Override
//    public Collection selectUsersTO(String criteriaCol, String criteria) {
//        ArrayList<User> selectedUsers = new ArrayList<>();
//
//        try {
//            Statement sta = con.createStatement();
//            ResultSet res = sta.executeQuery("SELECT ID, Address, Name, City FROM MYSQLUSER WHERE "+criteriaCol+" = '" + criteria + "'");
//
//            while (res.next()) {
//                User user = new User();
//                user.setUserId(res.getInt("ID"));
//                user.setStreetAddress(res.getString("ADDRESS"));
//                user.setName(res.getString("NAME"));
//                user.setCity(res.getString("CITY"));
//                selectedUsers.add(user);
//
//            }
//            res.close();
//            sta.close();
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//        return selectedUsers;
//    }
//
//}
