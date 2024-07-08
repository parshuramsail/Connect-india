package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.model.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtUserRepo extends JpaRepository<JwtUser, String> {
    Optional<JwtUser> findByEmailId(String emailId);
    Optional<JwtUser> findByEmailIdAndPassword(String emailId, String password);
}
