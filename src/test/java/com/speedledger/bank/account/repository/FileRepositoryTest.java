package com.speedledger.bank.account.repository;

import com.speedledger.bank.account.objectmodel.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Executable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileRepositoryTest {

    FileRepository fileRepository;

    @Test()
    void setup(){
        assertThrows(FileNotFoundException.class, () -> {
            FileRepository fileRepository= new FileRepository("void-file.txt");
        });
    }

    @Test
    void fetchAll() throws FileNotFoundException {
        FileRepository fileRepository = new FileRepository("/Users/ebramcity/Documents/workspace2/Home/technicaltest/target/test-classes/first.txt");
        List<Account> accounts = fileRepository.fetchAll();
        assertEquals(4, accounts.size());
        assertEquals(1, accounts.get(0).getId());
    }
}