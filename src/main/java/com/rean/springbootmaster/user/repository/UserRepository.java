package com.rean.springbootmaster.user.repository;

import com.rean.springbootmaster.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for communication with database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
