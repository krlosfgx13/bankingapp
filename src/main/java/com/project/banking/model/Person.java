package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "person", schema = "bankingapp")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "dpi")
    private String dpi;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "third_name")
    private String thirdName;

    @Column(name = "last_name")
    private String firstLastName;

    @Column(name = "second_last_name")
    private String secondLastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "home_phone_number")
    private Long homePhoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    public Integer getPersonId() {
        return personId;
    }

    public String getDpi() {
        return dpi;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHomePhoneNumber(Long homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
