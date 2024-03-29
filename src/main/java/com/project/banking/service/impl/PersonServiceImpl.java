package com.project.banking.service.impl;

import com.project.banking.model.Person;
import com.project.banking.repository.PersonRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;
    ApiResponse response = new ApiResponse();

    @Override
    public ApiResponse createPerson(Person person) {
        try{
            String dpi = person.getDpi();
            if(dpi.length() == 13){
                if(person.getFirstName() == "" || person.getFirstLastName() == "" ||
                        person.getAddress() == ""){
                    response.setMessage("Fill out mandatory fields.");
                    response.setCode(500);
                    response.setStatus("error");
                    return response;
                }
                repository.save(person);
                response.setMessage("Operation performed successfully.");
                response.setCode(200);
                response.setStatus("success");
            }else{
                return new ApiResponse(500, "DPI is not valid.", "error");
            }
            return response;
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ApiResponse updatePerson(Integer id, Person person) {
        try {
            Person personObj = repository.findByPersonId(id);

            if(personObj != null){
                personObj.setFirstLastName(person.getFirstLastName());
                personObj.setSecondName(person.getSecondName());
                personObj.setThirdName(person.getThirdName());
                personObj.setAddress(person.getAddress());
                personObj.setEmailAddress(person.getEmailAddress());
                personObj.setPhoneNumber(person.getPhoneNumber());
                personObj.setHomePhoneNumber(person.getHomePhoneNumber());
                repository.save(personObj);

            }else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }

        }catch (Exception ex){
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public ApiResponse deletePerson(Integer id) {
        try{
            Person person = repository.findByPersonId(id);

            if(person != null){
                repository.delete(person);
            }else{
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        }catch (Exception ex){
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public Person getPersonById(Integer id) {
        try{
            return repository.findByPersonId(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();
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
