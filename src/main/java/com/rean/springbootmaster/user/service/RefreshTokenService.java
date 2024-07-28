package com.rean.springbootmaster.user.service;

import com.rean.springbootmaster.user.constant.Constant;
import com.rean.springbootmaster.user.model.RefreshToken;
import com.rean.springbootmaster.user.repository.RefreshTokenRepository;
import com.rean.springbootmaster.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class RefreshTokenService {

   private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public void createRefreshToken(String username, String token, Date tokenExpiration){
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userRepository.findFirstByUsernameAndStatus(username, Constant.ACT).orElse(null))
                .token(token)
                .expiryDate(tokenExpiration)
                .build();
        log.atDebug().log("Refresh refreshToken created successfully..!");
        refreshTokenRepository.save(refreshToken);
    }


    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Date.from(Instant.now())) < 0){
            refreshTokenRepository.delete(token);
            log.info("Refresh refreshToken is expired. Please make a new login..!");
            throw new RuntimeException(token.getToken() + " Refresh refreshToken is expired. Please make a new login..!");
        }
        return token;
    }

}