package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "Cajero", schema = "bankingapp")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCajero")
    private Integer atmId;

    @Column(name = "IdBanco")
    private Integer bankId;

    @Column(name = "Direccion")
    private String address;

    @Column(name = "EfectivoDisponible")
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
