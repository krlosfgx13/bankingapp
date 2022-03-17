package com.project.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Persona", schema = "bankingapp")
public class Person {

    @Id
    @Column(name = "Dpi")
    private Long dpi;

    @Column(name = "PrimerNombre")
    private String firstName;

    @Column(name = "SegundoNombre")
    private String secondName;

    @Column(name = "TercerNombre")
    private String thirdName;

    @Column(name = "PrimerApellido")
    private String firstLastName;

    @Column(name = "SegundoApellido")
    private String secondLastName;

    @Column(name = "Direccion")
    private String address;

    @Column(name = "TelefonoMovil")
    private Long phoneNumber;

    @Column(name = "TelefonoResidencial")
    private Long homePhoneNumber;

    @Column(name = "CorreoElectronico")
    private String emailAddress;

    public Long getDpi() {
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Long getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setDpi(Long dpi) {
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

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHomePhoneNumber(Long homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
