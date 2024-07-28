package com.rean.springbootmaster.user.service;

import com.rean.springbootmaster.user.dto.ResponseErrorTemplate;
import com.rean.springbootmaster.user.dto.UserRequest;

public interface UserService {

    ResponseErrorTemplate create(UserRequest userRequest);
    ResponseErrorTemplate findById(Long id);
    ResponseErrorTemplate findByUsername(String username);

}