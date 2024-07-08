package com.stackroute.userservice.repository;

import com.stackroute.userservice.modal.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User,String>{

    Optional<User> findByEmailId (String emailId);
}
