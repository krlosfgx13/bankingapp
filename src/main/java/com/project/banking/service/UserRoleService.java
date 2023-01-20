package com.project.banking.service;

import com.project.banking.model.User;
import com.project.banking.model.UserRole;
import com.project.banking.response.ApiResponse;

public interface UserRoleService {

    ApiResponse createUserRole(UserRole userRole);
}
