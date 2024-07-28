package com.rean.springbootmaster.user.repository;


import com.rean.springbootmaster.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsernameAndStatus(String username, String status);

    Optional<User> findFirstByUsernameOrEmail(String username, String email);
}