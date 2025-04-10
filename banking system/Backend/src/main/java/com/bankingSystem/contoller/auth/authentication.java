// controller/auth/Authentication.java
package com.bankingSystem.controller.auth;

import com.bankingSystem.database.UserDatabase;
import com.bankingSystem.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class Authentication {

    public boolean register(String username, String surname, String password) {
        UserDatabase.insertUser(username, surname, password);
        return true;
    }

    public String login(String username, String password, HttpSession session, Model model) {
        User user = UserDatabase.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("response", "Invalid credentials");
            return "form";
        }
        session.setAttribute("currentUser", user);
        model.addAttribute("user", user);
        return "dashboard";
    }
}
