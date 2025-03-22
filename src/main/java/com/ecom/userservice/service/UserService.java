package com.ecom.userservice.service;

import com.ecom.userservice.model.User;
import com.ecom.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User user) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if(existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setAddress(user.getAddress());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

}
