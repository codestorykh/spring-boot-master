package com.rean.springbootmaster.user.security;

import com.rean.springbootmaster.user.constant.Constant;
import com.rean.springbootmaster.user.exception.CustomMessageException;
import com.rean.springbootmaster.user.model.User;
import com.rean.springbootmaster.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.customUserDetail(username);
    }

    public CustomUserDetail customUserDetail(String username) {
        Optional<User> user = userRepository.findFirstByUsernameAndStatus(username, Constant.ACT);
        if(user.isEmpty()){
            log.warn("Username {} unauthorized", username);
            throw new CustomMessageException("Unauthorized", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        }
        user.ifPresent(
                u -> {
                    if(!u.getStatus().equals(Constant.ACT)){
                        log.warn("Username {} blocked", username);
                        throw new CustomMessageException("Blocked", String.valueOf(HttpStatus.FORBIDDEN.value()));
                    }
                    if (u.getAttempt() > 3) {
                        log.warn("Username {} attempt more than 3", username);
                        throw new CustomMessageException("Unauthorized", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
                    }
                }
        );

        return new CustomUserDetail(
                user.get().getUsername(),
                user.get().getPassword(),
                user.get().getRoles()
                        .stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }

    public void saveUserAttemptAuthentication(String username) {
         userRepository.findFirstByUsernameAndStatus(username, Constant.ACT).ifPresent(
                user -> {
                    int attempt = user.getAttempt() + 1;
                    user.setAttempt(attempt);
                    user.setUpdated(LocalDateTime.now());
                    if(user.getAttempt() > 3) {
                        log.warn("User {} update status to blocked", username);
                        user.setStatus(Constant.BLK);
                    }
                    userRepository.save(user);
                }
        );
    }

    public void updateAttempt(String username) {
        userRepository.findFirstByUsernameAndStatus(username, Constant.ACT).ifPresent(
                user -> {
                    user.setAttempt(0);
                    user.setUpdated(LocalDateTime.now());
                    userRepository.save(user);
                }
        );
    }

}