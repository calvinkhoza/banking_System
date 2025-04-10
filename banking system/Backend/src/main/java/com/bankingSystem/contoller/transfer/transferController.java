// File: TransferController.java
package com.bankingSystem.controller.transfer;

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
public class TransferController {

    @GetMapping("/transfer")
    public String showTransferForm() {
        return "dashboard"; // Modal or section for transfer on dashboard
    }

    @PostMapping("/transfer")
    public String handleTransfer(@RequestParam Long fromAccNo, @RequestParam Long toAccNo, @RequestParam Double amount, Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "form";
        }

        Account fromAccount = AccountDatabase.getAccountByAccNo(fromAccNo);
        Account toAccount = AccountDatabase.getAccountByAccNo(toAccNo);

        if (fromAccount == null || !fromAccount.getUserId().equals(user.getId()) || toAccount == null) {
            model.addAttribute("errorMessage", "Invalid accounts or access denied.");
            return "dashboard";
        }

        if (amount > fromAccount.getBalance()) {
            model.addAttribute("errorMessage", "Insufficient funds for transfer.");
            return "dashboard";
        }

        AccountDatabase.updateBalance(fromAccNo, fromAccount.getBalance() - amount);
        AccountDatabase.updateBalance(toAccNo, toAccount.getBalance() + amount);

        TransactionDatabase.insertTransaction(fromAccNo, amount, "Transfer to " + toAccNo);
        TransactionDatabase.insertTransaction(toAccNo, amount, "Transfer from " + fromAccNo);

        model.addAttribute("message", "Transfer successful!");
        model.addAttribute("user", user);
        return "dashboard";
    }
}
