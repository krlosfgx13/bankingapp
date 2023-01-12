package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name="bank", schema = "bankingapp")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "cash_available")
    private float cashAvailable;

    public Integer getBankId() {
        return bankId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public float getCashAvailable() {
        return cashAvailable;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCashAvailable(float cashAvailable) {
        this.cashAvailable = cashAvailable;
    }
}
