package com.rean.springbootmaster.user.service;

import com.rean.springbootmaster.user.dto.AuthenticationRequest;
import com.rean.springbootmaster.user.dto.AuthenticationResponse;
import com.rean.springbootmaster.user.model.Role;
import com.rean.springbootmaster.user.model.User;
import com.rean.springbootmaster.user.repository.RoleRepository;
import com.rean.springbootmaster.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {

        this.getTodo(authenticationRequest);
        var username = authenticationRequest.username();
        Optional<User> userOptional = userRepository.findFirstByUsername(username);
        if(userOptional.isEmpty()){
            log.warn("Username {} not found.", username);
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username));
        }

        List<String> roles = new ArrayList<>();
        userOptional.get().getRoles().forEach(role -> roles.add(role.getName()));
        return new AuthenticationResponse(
                username,
                authenticationRequest.password(),
                userOptional.get().getEmail(),
                userOptional.get().getFullName(),
                roles
        );
    }

    public void saveUserRole() {
        Role userRole = Role.builder()
                .name("ROLE_USER")
                .build();

        Role adminRole = Role.builder()
                .name("ROLE_ADMIN")
                .build();

        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        Set<Role> roles = roleRepository.findAllByName("ROLE_ADMIN");

        User user = User.builder()
                .fullName("Rean Code")
                .username("reancode")
                .password(passwordEncoder.encode("123456"))
                .email("reancode@gmail.com")
                .roles(roles)
                .build();

        userRepository.save(user);
    }

    public void getTodo(AuthenticationRequest authenticationRequest) {
        var url = "http://localhost:8080/api/v1/todos";
        var usernamePassword = authenticationRequest.username()+":"+authenticationRequest.password();
        byte [] plainCredentialBytes = usernamePassword.getBytes();
        byte [] based64CredentialBytes = Base64.getEncoder().encode(plainCredentialBytes);
        var base64Credentials = new String(based64CredentialBytes);

        final RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<Object> objectHttpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, objectHttpEntity, String.class);
        log.info("Login response {}", response.getBody());
    }
}