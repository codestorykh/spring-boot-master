package com.rean.springbootmaster.user.controller;

import com.rean.springbootmaster.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("api/v1/user")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    @GetMapping("/accounts")
    public ResponseEntity<Object> findUserByUsername(Principal principal) {
        var username = principal.getName();
        log.info("User: username {} request get user data", username);
        return ResponseEntity.ok(userService.findByUsername(username));
    }

}