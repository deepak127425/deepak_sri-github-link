package com.mukul.demo.repository;

import com.mukul.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(String name);

    List<User> findByPlace(String place);

}
