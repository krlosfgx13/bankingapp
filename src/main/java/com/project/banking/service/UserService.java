package com.project.banking.service;

import com.project.banking.model.User;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.UsersResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {

    ApiResponse createUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
    ApiResponse updateUserPassword(Integer id, User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
    ApiResponse deleteUser(Integer id);
    User getUserById(Integer id);
    public User getUserByUserName(String userName);
    List<User> getAllUsers();
    UsersResponse getUsersData() throws SQLException, ClassNotFoundException;
    Boolean authenticateUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
