package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "atm", schema = "bankingapp")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atm_id")
    private Integer atmId;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "address")
    private String address;

    @Column(name = "cash_available")
    private float cashAvailable;

    public Integer getAtmId() {
        return atmId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public String getAddress() {
        return address;
    }

    public float getCashAvailable() {
        return cashAvailable;
    }

    public void setAtmId(Integer atmId) {
        this.atmId = atmId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCashAvailable(float cashAvailable) {
        this.cashAvailable = cashAvailable;
    }
}
