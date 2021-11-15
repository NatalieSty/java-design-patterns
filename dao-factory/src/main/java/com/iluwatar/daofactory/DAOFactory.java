package com.iluwatar.daofactory;

public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int DERBY = 1;
    public static final int MYSQL = 2;
    public static final int MONGO = 3;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract UserDAO getUserDAO();

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case DERBY:
                return new DerbyDAOFactory();
            case MYSQL    :
//                return new OracleDAOFactory();
            case MONGO    :
//                return new SybaseDAOFactory();
            default        :
                return null;
        }
    }
}
