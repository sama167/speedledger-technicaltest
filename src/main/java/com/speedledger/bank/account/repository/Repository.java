package com.speedledger.bank.account.repository;

import com.speedledger.bank.account.objectmodel.Account;

import java.util.List;

public interface Repository {

    List<Account> fetchAll();
}
