package com.rean.springbootmaster.user.controller;

import com.rean.springbootmaster.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Admin", description = "Admin API")
public class AdminController {

    private final UserService userService;

    @GetMapping("/accounts")
    public ResponseEntity<Object> index(Principal principal) {
        var username = principal.getName();
        log.info("Admin: username {} request get user data", username);
        return ResponseEntity.ok(userService.findByUsername(username));
    }

}