package com.bankingSystem.controller.withdrawal;

import com.bankingSystem.database.AccountDatabase;
import com.bankingSystem.database.TransactionDatabase;
import com.bankingSystem.model.Account;

public class withdrawal {

    public withdrawal(Account account, Double balance){

        double newBalance = account.getBalance()-balance;

        AccountDatabase.updateBalance(account.getAccNo(),newBalance );
       TransactionDatabase.insertTransaction(account.getAccNo(),balance,"withdrawal");
    }
}
