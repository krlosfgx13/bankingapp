package com.project.banking.request;

import javax.persistence.Column;
import java.util.List;

public class BankRequest {

    private String name;

    private String address;

    private float cashAvailable;

    private List<Integer> listOfCurrencies;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public float getCashAvailable() {
        return cashAvailable;
    }

    public List<Integer> getListOfCurrencies() {
        return listOfCurrencies;
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

    public void setListOfCurrencies(List<Integer> listOfCurrencies) {
        this.listOfCurrencies = listOfCurrencies;
    }
}
