package com.rean.springbootmaster.user.service.impl;

import com.rean.springbootmaster.user.repository.UserRepository;
import com.rean.springbootmaster.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service for business logic of user
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void getUserTodo() {

    }

    @Override
    public void getUsers() {

    }
}
