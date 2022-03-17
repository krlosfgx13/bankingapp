package com.project.banking.controller;

import com.project.banking.model.Bank;
import com.project.banking.model.Person;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.BankAccountService;
import com.project.banking.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonService service;

    @PostMapping("/person")
    public ApiResponse createPerson(@RequestBody Person person){
        try{
            return service.createPerson(person);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @GetMapping("/person")
    public List<Person> getAllPersons(){
        try{
            return service.getAllPersons();
        }
        catch (Exception ex){
            return null;
        }
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable(value="id") Long id){
        try{
            return service.getPersonById(id);
        }catch (Exception ex){
            return null;
        }
    }

    @PutMapping("/person/{id}")
    public ApiResponse updatePerson(@PathVariable(value="id") Long id, @RequestBody Person request){
        try{
            return service.updatePerson(id, request);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @DeleteMapping("/person/{id}")
    public ApiResponse DeletePerson(@PathVariable(value="id") Long id){
        try{
            return service.deletePerson(id);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }
}
