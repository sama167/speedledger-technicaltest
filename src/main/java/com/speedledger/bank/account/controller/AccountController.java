package com.speedledger.bank.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedledger.bank.account.service.AccountService;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;


public class AccountController {


    public AccountController(AccountService accountService) {
        get("/bankaccounts", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                response.type("application/json");
                return new ObjectMapper().writeValueAsString(accountService.getAccounts());
            }
        });
        get("/bankaccounts/default", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                response.type("application/json");
                return new ObjectMapper().writeValueAsString(accountService.getDefault());
            }
        });
    }

}
