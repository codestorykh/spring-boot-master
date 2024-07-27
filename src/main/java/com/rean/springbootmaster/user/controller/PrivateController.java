package com.rean.springbootmaster.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/private")
public class PrivateController {

    @GetMapping("access-resource-server")
    public ResponseEntity<Object> accessResourceServer() {
        return new ResponseEntity<>("Access Resource Server", HttpStatus.OK);
    }


    @GetMapping("ping-pong")
    public ResponseEntity<Object> pingPong() {
        return new ResponseEntity<>("Ping Pong", HttpStatus.OK);
    }
}
