package com.speedledger.bank.account.service;

import com.speedledger.bank.account.objectmodel.Account;
import com.speedledger.bank.account.repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {
    private Repository repository;

    public AccountServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> getAccounts() {
        return repository.fetchAll();
    }

    @Override
    public Account getDefault() {
        List<Account> currentList = repository.fetchAll();
        if (currentList == null || currentList.isEmpty())
            return null;
        currentList = currentList.stream().filter(Account::canBeDefault).collect(Collectors.toList());
        if (currentList.isEmpty())
            return null;
        if (currentList.size() == 1)
            return currentList.get(0);
        currentList.sort((a1, a2) -> (int) (a2.getBalance() - a1.getBalance()));
        if (currentList.get(0).getBalance() >= currentList.get(1).getBalance() * 2) {
            return currentList.get(0);
        }
        return null;

    }


}
