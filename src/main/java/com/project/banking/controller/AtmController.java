package com.project.banking.controller;

import com.project.banking.service.impl.Example;
import org.springframework.beans.factory.annotation.Value;
import com.project.banking.model.Atm;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AtmController {

    @Autowired
    AtmService service;

//    @Autowired
//    Example ex;

//    @Value("${app.name}")
//    private String appName;

    @GetMapping("/atm/test")
    public String test(){
        Example ex = new Example();
        String asdf = ex.test();
        //return appName;
        return asdf;
    }

    @PostMapping("/atm")
    public ApiResponse createAtm(@RequestBody Atm atm){
        try{
            if(Objects.equals(atm.getAddress(), "") || atm.getBankId() == null || atm.getCashAvailable() == 0.0){
                return new ApiResponse(500, "Fill out mandatory fields.", "error");
            }
            return service.createAtm(atm);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong.", "error");
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
