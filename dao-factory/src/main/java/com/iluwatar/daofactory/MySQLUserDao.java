//package com.iluwatar.daofactory;
//
//import javax.sql.RowSet;
//import java.util.Collection;
//import java.sql.*;
//
//public class MySQLUserDAO implements UserDAO {
//
//	private String DBURL;
//	public MySQLUserDAO(String DBURL) {
//		super();
//		this.DBURL = DBURL;
//	}
//	//tableName = name of table to insert to
//	//values = values to insert, comma-separated eg: 1, John, 25 1st Street, Chicago
//	@Override
//	public int insertUser(String tableName, String values) {
//		Connection con = MySQLDAOFactory.createConnection(DBURL);
//		String query = "Insert into " + tableName + " Values (" + values + ")";
//		Statement stmt = con.createStatement();
//		try {
//			stmt.executeUpdate(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		}
//		con.close();
//	}
//	//tableName = name of table to delete from
//	//where = where condition for deletion
//	@Override
//	public boolean deleteUser(String tableName, String where) {
//		Connection con = MySQLDAOFactory.createConnection(DBURL);
//		String query = "Delete from " + tableName + " where " + where;
//		Statement stmt = con.createStatement();
//		try {
//			stmt.executeUpdate(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		con.close();
//		return true;
//	}
//	//tableName = name of table to search
//	//where = condition to search on
//	@Override
//	public User findUser(String tableName, string where) {
// 		Connection con = MySQLDAOFactory.createConnection(DBURL);
//                String query = "Select * from " + tableName + " where " + where;
//		Statement stmt = con.createStatement();
//		User u = null;
//		try {
//			ResultSet res = stmt.executeUpdate(query);
//			while (res.next()) {
//				u = new User();
//				u.setUserId(res.getInt("userId"));
//				u.setName(res.getString("name"));
//				u.setStreetAddress(res.getString("streetAddress"));
//				u.setCity(res.getString("city"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//		con.close();
//		return u;
//	}
//	//tableName = name of table to update
//	//values = String[4] of new values leave null if not updating column [userId, name, streetAddress, city]
//	//where = condition to update
//	@Override
//	public boolean updateUser(String tableName, String[] values, String where) {
//		if (values.length != 4|| (values[0] == null && values[1] == null && values[2] == null && values[3] == null)) {
//			System.out.println("values is not correct length or all inputs are null");
//			return false;
//		}
//		Connection con = MySQLDAOFactory.createConnection(DBURL);
//		Statement stmt = con.createStatement();
//		String query = "update " + tableName + " set ";
//		if (values[0] != null) {
//			query = query + "userId = " + values[0];
//			if (values[1] != null || values[2] != null || values[3] != null) {
//				query = query + ", ";
//			}
//		}
//                if (values[1] != null) {
//			query = query + "name = " + values[1];
//		      	if (values[2] != null || values[3] != null) {
//				query = query + ", ";
//			}
//		}
//                if (values[2] != null) {
//                        query = query + "streetAddress = " + values[2];
//                        if (values[3] != null) {
//		     		query = query + ", ";
//			}
//                }
//                if (values[3] != null) {
//	                query = query + "city = " + values[3];
//		}
//		query = query + " where " + where;
//		try {
//			stmt.executeUpdate(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		con.close();
//		return true;
//	}
//	@Override
//	public RowSet selectUserRS(String tableName, String where) {
//		Connection con = MySQLDAOFactory.createConnection(DBURL);
//		JdbcRowSet rs = new JdbcRowSetImpl(con);
//		rs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
//		String query = "Select * from " + tableName + " where " + where;
//		try {
//			rs.setCommand(query);
//			rs.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//		con.close();
//		return rs;
//	}
//
//}
