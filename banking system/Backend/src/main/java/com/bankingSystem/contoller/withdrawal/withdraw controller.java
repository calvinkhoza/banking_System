// File: WithdrawController.java
package com.bankingSystem.controller.withdraw;

import com.bankingSystem.database.AccountDatabase;
import com.bankingSystem.database.TransactionDatabase;
import com.bankingSystem.model.Account;
import com.bankingSystem.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WithdrawController {

    @GetMapping("/withdraw")
    public String showWithdrawForm() {
        return "dashboard"; // Assume modal or section handles withdraw input on dashboard
    }

    @PostMapping("/withdraw")
    public String handleWithdraw(@RequestParam Long accNo, @RequestParam Double amount, Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "form";
        }

        Account account = AccountDatabase.getAccountByAccNo(accNo);
        if (account == null || !account.getUserId().equals(user.getId())) {
            model.addAttribute("errorMessage", "Invalid account or access denied.");
            return "dashboard";
        }

        if (amount > account.getBalance()) {
            model.addAttribute("errorMessage", "Insufficient funds.");
            return "dashboard";
        }

        double newBalance = account.getBalance() - amount;
        AccountDatabase.updateBalance(accNo, newBalance);
        TransactionDatabase.insertTransaction(accNo, amount, "Withdraw");

        model.addAttribute("message", "Withdraw successful! New balance: R" + newBalance);
        model.addAttribute("user", user);
        return "dashboard";
    }
}