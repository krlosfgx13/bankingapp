package com.project.banking.service.impl;

import com.project.banking.model.Role;
import com.project.banking.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.project.banking.model.User user = userService.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUserName(), user.getPassword(), emptyList());
    }

    public com.project.banking.model.User findByUserName(String username) {
        return userService.getUserByUserName(username);
    }

    public List<Role> getRolesFromUser(Integer userId) {
        return userService.getRolesFromUser(userId);
    }
}
