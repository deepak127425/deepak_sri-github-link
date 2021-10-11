package com.mukul.demo.service;

import com.mukul.demo.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);

    List<User> getUsersByName(String name);

    List<User> getUsersByPlace(String place);

    User updateUser(String id, User user);

    void deleteUser(String id);

}
