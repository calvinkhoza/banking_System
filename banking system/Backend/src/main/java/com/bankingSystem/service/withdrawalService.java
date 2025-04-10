package com.bankingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankingSystem.model.Account;
import com.bankingSystem.service.AccountService;
import com.bankingSystem.service.TransactionService;

@Service
public class WithdrawalService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    // Handle withdrawal logic
    public boolean withdraw(long accountNumber, double amount) {
        // Fetch the account
        Account account = accountService.getAccountByAccountNumber(accountNumber);

        if (account == null || account.getBalance() < amount) {
            return false;  // Account not found or insufficient balance
        }

        // Update the balance after withdrawal
        double newBalance = account.getBalance() - amount;
        accountService.updateAccountBalance(accountNumber, newBalance);

        // Record the withdrawal transaction
        transactionService.recordTransaction(accountNumber, amount, "Withdrawal");

        return true;
    }
}
