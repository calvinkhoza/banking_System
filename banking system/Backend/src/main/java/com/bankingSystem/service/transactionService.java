package com.bankingSystem.service;

import com.bankingSystem.database.TransactionDatabase;
import com.bankingSystem.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    // Record a new transaction
    public void recordTransaction(long accountNumber, double amount, String type) {
        Transaction transaction = new Transaction(accountNumber, amount, type);
        TransactionDatabase.insertTransaction(transaction);
    }

    // Get all transactions for a given account
    public List<Transaction> getTransactionsByAccountNumber(long accountNumber) {
        return TransactionDatabase.getTransactionsByAccount(accountNumber);
    }
}
