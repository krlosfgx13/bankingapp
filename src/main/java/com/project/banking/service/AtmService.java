package com.project.banking.service;

import com.project.banking.model.Atm;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface AtmService {

    ApiResponse createAtm(Atm atm);
    ApiResponse updateAtm(Integer id, double amount);
    ApiResponse deleteAtm(Integer id);
    Atm getAtmById(Integer id);
    List<Atm> getAllAtms();
}
