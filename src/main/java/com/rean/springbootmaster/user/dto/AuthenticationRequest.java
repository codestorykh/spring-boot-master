package com.rean.springbootmaster.user.dto;

public record AuthenticationRequest(
        String username,
        String password){}