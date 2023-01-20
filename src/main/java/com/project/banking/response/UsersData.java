package com.project.banking.response;

import java.sql.Timestamp;

public class UsersData {
    private Integer personId;
    private String userName;
    private String firstName;
    private String lastName;
    private String address;
    private Integer phoneNumber;
    private Integer homePhoneNumber;
    private String emailAddress;
    private Timestamp creationDate;

    public Integer getPersonId() {
        return personId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHomePhoneNumber(Integer homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

}
