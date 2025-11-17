package com.aishwarya.ethical.transparency_portal.modules.user.service;

import org.springframework.stereotype.Service;

import com.aishwarya.ethical.transparency_portal.exception_handling.UserNotFoundException;


@Service
public class UserService {

    public String getUser(Long id) {
        if (id == 0) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return "User found with id: " + id;
    }
}

