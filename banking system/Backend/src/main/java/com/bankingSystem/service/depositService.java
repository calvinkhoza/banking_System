package com.bankingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankingSystem.model.Account;
import com.bankingSystem.service.AccountService;
import com.bankingSystem.service.TransactionService;

@Service
public class DepositService {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private TransactionService transactionService;

    // Handle deposit logic
    public boolean deposit(long accountNumber, double amount) {
        // Fetch the account
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        
        if (account == null) {
            return false;  // Account not found
        }

        // Update the balance after deposit
        double newBalance = account.getBalance() + amount;
        accountService.updateAccountBalance(accountNumber, newBalance);

        // Record the deposit transaction
        transactionService.recordTransaction(accountNumber, amount, "Deposit");

        return true;
    }
}
