package com.speedledger.bank.account.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedledger.bank.account.objectmodel.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

public class FileRepository implements Repository {

    private static final Logger LOGGER = LogManager.getLogger();

    private List<Account> accounts;

    public FileRepository(String path) throws FileNotFoundException {
        File file = new File(path);
        try {
            accounts = Arrays.asList(new ObjectMapper().readValue(file, Account[].class));
        } catch (FileNotFoundException e) {
            LOGGER.fatal(String.format("Could not find the source file specified by %s " , path));
            throw e;
        } catch (IOException e) {
            LOGGER.warn("It seems the file is corrupted. Could not read file.");
            accounts = new ArrayList<>();
        }
    }

    @Override
    public List<Account> fetchAll() {
        return accounts;
    }


}
