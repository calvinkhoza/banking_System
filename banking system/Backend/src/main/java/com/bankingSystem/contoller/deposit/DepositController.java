package com.bankingSystem.controller.deposit;

import com.bankingSystem.database.AccountDatabase;
import com.bankingSystem.database.TransactionDatabase;
import com.bankingSystem.model.Account;
import com.bankingSystem.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepositController {

    @PostMapping("/deposit")
    public String handleDeposit(@RequestParam Long accNo, @RequestParam Double amount,
                                Model model, HttpSession session) {
        Account account = AccountDatabase.getAccountByAccNo(accNo);
        if (account == null || amount < 10) {
            model.addAttribute("response", "Invalid account or minimum deposit R10");
            return "dashboard";
        }

        double newBalance = account.getBalance() + amount;
        AccountDatabase.updateBalance(accNo, newBalance);
        TransactionDatabase.insertTransaction(accNo, amount, "Deposit");

        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);
        model.addAttribute("accounts", AccountDatabase.getAccountsByUserId(user.getId()));
        return "dashboard";
    }
}
