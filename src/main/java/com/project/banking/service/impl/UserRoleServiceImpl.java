package com.project.banking.service.impl;

import com.project.banking.model.UserRole;
import com.project.banking.repository.UserRoleRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public ApiResponse createUserRole(UserRole userRole) {
        ApiResponse response = new ApiResponse();
        userRoleRepository.save(userRole);
        response.setMessage("Operation performed successfully.");
        response.setCode(200);
        response.setStatus("success");
        return response;
    }
}
