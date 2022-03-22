package com.project.banking.service.impl;

import com.project.banking.model.Atm;
import com.project.banking.model.Bank;
import com.project.banking.repository.AtmRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.AtmService;
import com.project.banking.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtmServiceImpl implements AtmService {

//    @Autowired
//    private AtmRepository repository;
//
//    @Autowired
//    BankService bankService;

    private final AtmRepository repository;
    private final BankService bankService;

    public AtmServiceImpl(AtmRepository repository, BankService bankService){
        this.repository = repository; //comment in main branch.
        this.bankService = bankService; //comment in main branch.
    }

    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createAtm(Atm atm) {
        Bank bank = bankService.getBankById(atm.getBankId());
        if(bank == null){
            return new ApiResponse(500, "Id Banco no valido.", "error");
        }
        try{
            repository.save(atm);
            response.setMessage("Operacion realizada con exito.");
            response.setCode(200);
            response.setStatus("success");
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateAtm(Integer id, float amount) {
        try {
            Atm atmObj = repository.findByAtmId(id);

            if(atmObj != null){
                atmObj.setCashAvailable(amount);
                repository.save(atmObj);
            }else {
                return new ApiResponse(500, "Ha ocurrido un error.", "error");
            }

        }catch (Exception ex){
            throw ex;
        }
        return new ApiResponse(200, "Operacion realizada con exito.", "success");
    }

    @Override
    public ApiResponse deleteAtm(Integer id) {
        try{
            Atm atm = repository.findByAtmId(id);
            if(atm != null){
                repository.delete(atm);
            }else{
                return new ApiResponse(500, "Ha ocurrido un error.", "error");
            }
        }catch (Exception ex){
            throw ex;
        }
        return new ApiResponse(200, "Operacion realizada con exito.", "success");
    }

    @Override
    public Atm getAtmById(Integer id) {
        try{
            return repository.findByAtmId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Atm> getAllAtms() {
        List<Atm> list = new ArrayList<>();
        try{
            list = repository.findAll();
            if(!list.isEmpty()){
                return list;
            }
        }catch(Exception ex){
            throw ex;
        }
        return list;
    }
}
