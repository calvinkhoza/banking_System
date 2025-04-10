// controller/AuthController.java
package com.banking.system.controller;

import com.banking.system.model.User;
import com.banking.system.repository.UserRepository;
import com.banking.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, 
                                    @RequestParam String password) {
        return ResponseEntity.ok(authService.authenticate(email, password));
    }
}