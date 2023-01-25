package com.project.banking.controller;

import com.project.banking.model.User;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.UsersResponse;
import com.project.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user")
    public ApiResponse createUser(@RequestBody User user){
        try{
            return service.createUser(user);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @PostMapping("/login")
    public Boolean authenticateUser(@RequestBody User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try{
            return service.authenticateUser(user.getUserName(), user.getPassword());
        }catch (Exception ex){
            return false;
        }
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/user")
    public List<User> getAllUsers(){
        try {
            return service.getAllUsers();
        }catch (Exception ex){
            return  null;
        }
    }

    @CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
    @GetMapping("/userdata")
    public UsersResponse getAllUsersData(){
        try {
            return service.getUsersData();
        }catch (Exception ex){
            return  null;
        }
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value="id") Integer id){
        try {
            return service.getUserById(id);
        }catch (Exception ex){
            return  null;
        }
    }

    @PutMapping("/user/{id}")
    public ApiResponse updateUser(@PathVariable(value="id") Integer id, @RequestBody User user){
        try {
            return service.updateUserPassword(id, user);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }

    @DeleteMapping("/user/{id}")
    public ApiResponse DeleteUser(@PathVariable(value="id") Integer id){
        try {
            return  service.deleteUser(id);
        }catch (Exception ex){
            return new ApiResponse(500, "Something went wrong", "error");
        }
    }
}
