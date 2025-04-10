package com.bankingSystem.service;

import com.bankingSystem.database.AccountDatabase;
import com.bankingSystem.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    // Create a new account for a user
    public Account createAccount(long userId, double initialDeposit) {
        // Generate a random account number (you can refine this logic)
        long accountNumber = (long) (Math.random() * 1000000000L);
        double balance = initialDeposit;
        Account account = new Account(accountNumber, userId, balance);
        
        // Insert the account into the database
        AccountDatabase.insertAccount(account);
        
        return account;
    }

    // Get account details by account number
    public Account getAccountByAccountNumber(long accountNumber) {
        return AccountDatabase.getAccountByAccNo(accountNumber);
    }

    // Update account balance after a deposit or withdrawal
    public void updateAccountBalance(long accountNumber, double newBalance) {
        AccountDatabase.updateBalance(accountNumber, newBalance);
    }
}
