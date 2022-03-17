package com.project.banking.service;

import com.project.banking.model.Person;
import com.project.banking.response.ApiResponse;

import java.util.List;

public interface PersonService {

    ApiResponse createPerson(Person person);
    ApiResponse updatePerson(Long id, Person person);
    ApiResponse deletePerson(Long id);
    Person getPersonById(Long id);
    List<Person> getAllPersons();
}
