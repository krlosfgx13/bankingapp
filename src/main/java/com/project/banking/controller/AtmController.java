package com.project.banking.controller;

import com.project.banking.model.Atm;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.AtmService;
import com.project.banking.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AtmController {

    @Autowired
    AtmService service;

    @PostMapping("/atm")
    public ApiResponse createAtm(@RequestBody Atm atm){
        try{
            if(atm.getAddress() == "" || atm.getBankId() == null || atm.getCashAvailable() == 0.0){
                return new ApiResponse(500, "Llenar campos obligatorios", "error");
            }
            return service.createAtm(atm);
        }catch (Exception ex){
            return new ApiResponse(500, "Ha ocurrido un error", "error");
        }
    }

    @GetMapping("/atm")
    public List<Atm> getAllAtms(){
        try{
            return service.getAllAtms();
        }catch (Exception ex){
            return null;
        }
    }

    @GetMapping("/atm/{id}")
    public Atm getAtmById(@PathVariable(value="id") int id){
        try{
            return service.getAtmById(id);
        }catch (Exception ex){
            return null;
        }
    }

    @PutMapping("/atm/{id}")
    public ApiResponse updateAtm(@PathVariable(value="id") int id, float amount){
        try{
            return service.updateAtm(id, amount);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @DeleteMapping("/atm/{id}")
    public ApiResponse deleteAtm(@PathVariable(value="id") int id){
        try{
            return  service.deleteAtm(id);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }
}
