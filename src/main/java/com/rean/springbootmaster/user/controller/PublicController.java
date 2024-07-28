package com.rean.springbootmaster.user.controller;


import com.rean.springbootmaster.user.dto.AuthenticationRequest;
import com.rean.springbootmaster.user.dto.AuthenticationResponse;
import com.rean.springbootmaster.user.dto.RefreshTokenRequest;
import com.rean.springbootmaster.user.dto.UserRequest;
import com.rean.springbootmaster.user.jwt.JwtService;
import com.rean.springbootmaster.user.model.RefreshToken;
import com.rean.springbootmaster.user.security.CustomUserDetail;
import com.rean.springbootmaster.user.security.CustomUserDetailService;
import com.rean.springbootmaster.user.service.RefreshTokenService;
import com.rean.springbootmaster.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final CustomUserDetailService customUserDetailService;

    @PostMapping("/accounts/register")
    public ResponseEntity<Object> register(@RequestBody UserRequest userRequest) {
        log.info("Intercept registration new user with req: {}", userRequest);
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> AuthenticateAndGetToken(@RequestBody AuthenticationRequest authenticationRequest){
        log.info("Start login process");
        final CustomUserDetail customUserDetail = customUserDetailService.customUserDetail(authenticationRequest.username());
        log.info("End login process");
        return ResponseEntity.ok(
                new AuthenticationResponse(
                        jwtService.generateToken(customUserDetail),
                        jwtService.refreshToken(customUserDetail)));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Object> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        var authResponse = refreshTokenService.findByToken(refreshTokenRequest.refreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    final CustomUserDetail customUserDetail = customUserDetailService.customUserDetail(userInfo.getUsername());
                    final String accessToken = jwtService.generateToken(customUserDetail);
                    return new AuthenticationResponse(accessToken, refreshTokenRequest.refreshToken());
                }).orElseThrow(() -> new UsernameNotFoundException("Invalid refresh refreshToken"));

        return ResponseEntity.ok(authResponse);
    }
}