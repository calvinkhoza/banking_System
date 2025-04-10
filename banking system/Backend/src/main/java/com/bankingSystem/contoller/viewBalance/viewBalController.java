// File: ViewBalanceController.java
package com.bankingSystem.controller.balance;

import com.bankingSystem.database.AccountDatabase;
import com.bankingSystem.model.Account;
import com.bankingSystem.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewBalanceController {

    @GetMapping("/balance")
    public String viewBalance(@RequestParam Long accNo, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            model.addAttribute("errorMessage", "You must be logged in to view balance.");
            return "form";
        }

        Account account = AccountDatabase.getAccountByAccNo(accNo);
        if (account == null || !account.getUserId().equals(user.getId())) {
            model.addAttribute("errorMessage", "Invalid account or access denied.");
            return "dashboard";
        }

        model.addAttribute("user", user);
        model.addAttribute("balance", account.getBalance());
        model.addAttribute("accountNumber", accNo);
        return "dashboard"; // Ensure your dashboard shows this balance info
    }
}