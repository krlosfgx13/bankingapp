package com.project.banking.service;

import com.project.banking.model.Person;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface PersonService {

    ApiResponse createPerson(Person person);
    ApiResponse updatePerson(Integer id, Person person);
    ApiResponse deletePerson(Integer id);
    Person getPersonById(Integer id);
    List<Person> getAllPersons();
}
