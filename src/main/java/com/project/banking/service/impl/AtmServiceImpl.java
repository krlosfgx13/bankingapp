package com.project.banking.service.impl;

import com.project.banking.model.Atm;
import com.project.banking.model.Bank;
import com.project.banking.repository.AtmRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.AtmService;
import com.project.banking.service.BankService;
import com.project.banking.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class AtmServiceImpl implements AtmService {

//    @Autowired
//    private AtmRepository repository;
//
//    @Autowired
//    BankService bankService;

    @Value("${app.name}")
    private String appName;

    private final AtmRepository repository;
    private final BankService bankService;

    public AtmServiceImpl(AtmRepository repository, BankService bankService){
        this.repository = repository; //comment in main branch.
        this.bankService = bankService; //comment in main branch.
    }

    ApiResponse response = new ApiResponse();

    @Override
    public String test(){
        try{
            Properties conf = PropertiesLoader.loadProperties();
            return conf.getProperty("app.name");
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }

    @Override
    public ApiResponse createAtm(Atm atm) {
        Bank bank = bankService.getBankById(atm.getBankId());
        if(bank == null){
            return new ApiResponse(500, "Bank Id is not valid.", "error");
        }
        try{
            repository.save(atm);
            response.setMessage("Operation performed successfully.");
            response.setCode(200);
            response.setStatus("success");
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updateAtm(Integer id, double amount) {
        try {
            Atm atmObj = repository.findByAtmId(id);

            if(atmObj != null){
                atmObj.setCashAvailable(amount);
                repository.save(atmObj);
            }else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }

        }catch (Exception ex){
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public ApiResponse deleteAtm(Integer id) {
        try{
            Atm atm = repository.findByAtmId(id);
            if(atm != null){
                repository.delete(atm);
            }else{
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        }catch (Exception ex){
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
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
