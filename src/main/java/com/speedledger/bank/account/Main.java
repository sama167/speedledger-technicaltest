package com.speedledger.bank.account;

import com.speedledger.bank.account.controller.AccountController;
import com.speedledger.bank.account.repository.FileRepository;
import com.speedledger.bank.account.repository.Repository;
import com.speedledger.bank.account.service.AccountService;
import com.speedledger.bank.account.service.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        Repository fileRepository = null;
        String path = "";
        try {
            path = getDefaultPath();
            fileRepository = new FileRepository(path);
        } catch (FileNotFoundException e) {
            LOGGER.fatal(String.format("Could not find the source file specified by path %s", path) );
            System.exit(-1);
        }
        AccountService accountService = new AccountServiceImpl(fileRepository);
        new AccountController(accountService);
        LOGGER.info("The server is running now . . .");
    }

    private static String getDefaultPath() throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = Main.class.getClassLoader();
        URL resource = loader.getResource("config.prop");
        if (resource == null){
            throw new IOException("Could not load properties file \"config.prop\" ");
        }
        try (InputStream inputStream = new FileInputStream(resource.getFile())) {
                properties.load(inputStream);
                return properties.getProperty("source_folder");
            } catch (IOException e) {
                LOGGER.fatal("Could not load properties file \"config.prop\" ", e);
                throw e;
            }


    }
}
