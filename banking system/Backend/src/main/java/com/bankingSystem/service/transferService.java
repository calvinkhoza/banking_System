package com.bankingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankingSystem.model.Account;
import com.bankingSystem.service.AccountService;
import com.bankingSystem.service.TransactionService;

@Service
public class TransferService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    // Handle transfer logic
    public boolean transfer(long fromAccountNumber, long toAccountNumber, double amount) {
        // Fetch both accounts
        Account fromAccount = accountService.getAccountByAccountNumber(fromAccountNumber);
        Account toAccount = accountService.getAccountByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null || fromAccount.getBalance() < amount) {
            return false;  // Account not found or insufficient balance
        }

        // Update the balance for both accounts
        accountService.updateAccountBalance(fromAccountNumber, fromAccount.getBalance() - amount);
        accountService.updateAccountBalance(toAccountNumber, toAccount.getBalance() + amount);

        // Record the transactions
        transactionService.recordTransaction(fromAccountNumber, amount, "Transfer Out");
        transactionService.recordTransaction(toAccountNumber, amount, "Transfer In");

        return true;
    }
}
