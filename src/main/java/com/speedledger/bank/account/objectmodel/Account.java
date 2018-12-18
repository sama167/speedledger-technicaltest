package com.speedledger.bank.account.objectmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty("id")
    private long id;
    @JsonProperty("number")
    private String number;
    @JsonProperty("name")
    private String name;
    @JsonProperty("balance")
    private float balance;
    @JsonProperty("creditcard")
    private boolean creditCard;
    @JsonProperty("synthetic")
    private boolean synthetic;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    public boolean canBeDefault(){
        return !isSynthetic() && balance>=0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", creditCard=" + creditCard +
                ", synthetic=" + synthetic +
                '}';
    }
}
