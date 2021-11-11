package com.iluwatar.daofactory;

public abstract class DAOFactory {
    // List of DAO types supported by the factory
    // TODO: decide on which databases to use
    public static final int DERBY = 1;
    public static final int ORACLE = 2;
    public static final int SYBASE = 3;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract UserDAO getUserDAO();
    // TODO: Create more DAO
//    public abstract AccountDAO getAccountDAO();
//    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case DERBY:
                return new DerbyDAOFactory();
            case ORACLE    :
//                return new OracleDAOFactory();
            case SYBASE    :
//                return new SybaseDAOFactory();
            default        :
                return null;
        }
    }
}
