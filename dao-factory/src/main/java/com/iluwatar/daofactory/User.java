package com.iluwatar.daofactory;

public class User implements java.io.Serializable {
    // member variables
    private int UserId;
    private String name;
    private String streetAddress;
    private String city;

    // getter and setter methods...

    public int getUserId() {
        return this.UserId;
    }

    public String getCity() {
        return this.city;
    }

    public String getName() {
        return this.name;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
