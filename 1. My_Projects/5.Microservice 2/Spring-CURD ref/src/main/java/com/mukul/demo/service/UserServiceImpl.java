package com.mukul.demo.service;

import com.mukul.demo.model.User;
import com.mukul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        try {

            User _user = userRepository.save(user);

            return _user;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {

            List<User> users = userRepository.findAll();

            if (users == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Records not found!");

            return users;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public User getUserById(String id) {

        try {

            Optional<User> user = userRepository.findById(id);

            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found!");

            return user.get();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public List<User> getUsersByName(String name) {

        try {

            List<User> users = userRepository.findByName(name);

            if (users == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Records not found!");

            return users;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Override
    public List<User> getUsersByPlace(String place) {

        try {

            List<User> users = userRepository.findByPlace(place);

            if (users.size() == 0)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Records not found!");

            return users;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Override
    public User updateUser(String id, User user) {

        try {

            Optional<User> userData = userRepository.findById(id);

            if(userData.isPresent())
            {
                User _user = userData.get();

                _user.setName(user.getName());
                _user.setPlace(user.getPlace());


                return userRepository.save(_user);
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found!");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteUser(String id) {

        try {

            userRepository.deleteById(id);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
