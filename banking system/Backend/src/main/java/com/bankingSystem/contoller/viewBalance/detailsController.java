package com.bankingSystem.controller.ViewBalance;

import com.bankingSystem.database.AccountDatabase;
import com.bankingSystem.database.TransactionDatabase;
import com.bankingSystem.model.Account;
import com.bankingSystem.model.User;
import com.bankingSystem.model.transaction;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class detailsController {


    @PostMapping("/details")
    public String details(Model model, HttpSession session, @RequestParam("accNo") long accNo){

        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "login";
        }
        Account account = AccountDatabase.getAccountByAccNo(accNo);

        List<transaction> transactions = TransactionDatabase.getTransactionsByAccount(account);


        model.addAttribute("transactions", transactions); // Pass accounts to the view
        model.addAttribute("user", user);
        model.addAttribute("account", account);
        return "details"; // Return the Thymeleaf template name
    }
}
