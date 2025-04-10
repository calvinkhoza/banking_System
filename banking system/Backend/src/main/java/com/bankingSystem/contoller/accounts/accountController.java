// controller/AccountController.java
package com.banking.system.controller;

import com.banking.system.model.Account;
import com.banking.system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam Long userId, 
                                               @RequestParam String accountType) {
        return ResponseEntity.ok(accountService.createAccount(userId, accountType));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getUserAccounts(userId));
    }

    // Keep existing deposit/withdraw/transfer endpoints
}