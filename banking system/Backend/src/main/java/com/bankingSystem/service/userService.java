package com.bankingSystem.service;

import com.bankingSystem.database.UserDatabase;
import com.bankingSystem.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Register a new user
    public boolean registerUser(String username, String surname, String password) {
        return UserDatabase.insertUser(username, surname, password);
    }

    // Get user by username
    public User getUserByUsername(String username) {
        return UserDatabase.getUserByUsername(username);
    }
}
