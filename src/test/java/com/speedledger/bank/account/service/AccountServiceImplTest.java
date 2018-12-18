package com.speedledger.bank.account.service;

import com.speedledger.bank.account.objectmodel.Account;
import com.speedledger.bank.account.repository.FileRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    static FileRepository repository;

    @BeforeAll
    public static void setup(){
        repository = Mockito.mock(FileRepository.class);
    }

    @Test
    public void getDefaultAccount_emptyList(){
        List<Account> emptyList = new ArrayList<>();
        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        when(repository.fetchAll()).thenReturn(emptyList);
        assertNull(accountService.getDefault());
    }

    @Test
    public void getDefaultAccount_syntheticAccount(){
        List<Account> syntheticList = new ArrayList<>();
        Account account = new Account();
        account.setSynthetic(true);
        account.setBalance(1L);
        account.setId(101);
        syntheticList.add(account);
        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        when(repository.fetchAll()).thenReturn(syntheticList);
        assertNull(accountService.getDefault());
        syntheticList.clear();
        account.setSynthetic(false);
        syntheticList.add(account);
        assertNotNull(accountService.getDefault());
        assertEquals(accountService.getDefault().getId(),101);
        Account account2 = new Account();
        account2.setSynthetic(true);
        account.setSynthetic(true);
        syntheticList.add(account2);
        assertNull(accountService.getDefault());
    }

    @Test
    public void getDefaultAccount_OnlyAccount(){
        List<Account> single = new ArrayList<>();
        Account account = new Account();
        account.setId(202);
        account.setBalance(0);
        single.add(account);
        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        when(repository.fetchAll()).thenReturn(single);
        assertNotNull(accountService.getDefault());
        single.clear();
        account.setBalance(-1);
        single.add(account);
        assertNull(accountService.getDefault());
    }

    @Test
    public void getDefaultAccount_multipleAccount(){
        List<Account> accountList = new ArrayList<>();
        Account account1 = new Account();
        account1.setId(101);
        account1.setBalance(-1);
        Account account2 = new Account();
        account2.setId(202);
        account2.setBalance(-2);
        accountList.add(account1);
        accountList.add(account2);
        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        when(repository.fetchAll()).thenReturn(accountList);
        assertNull(accountService.getDefault());
        Account account3 = new Account();
        account3.setId(303);
        account3.setBalance(0);
        accountList.add(account3);
        assertNotNull(accountService.getDefault());
        assertEquals(303, accountService.getDefault().getId());
        Account account4 = new Account();
        account4.setId(404);
        account4.setBalance(4);
        accountList.add(account4);
        assertEquals(404, accountService.getDefault().getId());
        Account account5 = new Account();
        account5.setId(505);
        account5.setBalance(5);
        accountList.add(account5);
        assertNull(accountService.getDefault());
        Account account6 = new Account();
        account6.setId(606);
        account6.setBalance(13);
        accountList.add(account6);
        assertEquals(606, accountService.getDefault().getId());
    }

}