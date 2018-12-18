package com.speedledger.bank.account.service;

import com.speedledger.bank.account.objectmodel.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts();
    Account getDefault();

}
