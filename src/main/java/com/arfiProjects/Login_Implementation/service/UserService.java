package com.arfiProjects.Login_Implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arfiProjects.Login_Implementation.model.User;
import com.arfiProjects.Login_Implementation.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    /**
     * Register a new user
     * @param fullName User's full name
     * @param email User's email
     * @param password User's password (should be encoded before saving in a real application)
     * @return The saved user
     */
    public User registerUser(String fullName, String email, String password) {
        // In a real application, password should be encoded
        // For example: String encodedPassword = passwordEncoder.encode(password);
        
        User newUser = new User(fullName, email, password);
        return userRepository.save(newUser);
    }
    
    /**
     * Check if a user with the given email already exists
     * @param email User's email
     * @return true if user exists, false otherwise
     */
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }
}
