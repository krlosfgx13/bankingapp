package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name="banco", schema = "bankingapp")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBanco")
    private Integer bankId;

    @Column(name = "Nombre")
    private String name;

    @Column(name = "Direccion")
    private String address;

    @Column(name = "EfectivoDisponible")
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
