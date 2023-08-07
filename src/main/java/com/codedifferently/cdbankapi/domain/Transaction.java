package com.codedifferently.cdbankapi.domain;

import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;

import java.util.Date;

public abstract class Transaction {

    private long accountId;
    private long id;
    String transactionType;
    Long amount;
    String description;
    Medium medium;
    Status status;
    Date transactionDate;

    public void setTransactionType(String transactionType){
        this.transactionType = transactionType;
    }
    public abstract Date getTransactionDate();

    public Long getAmount() {
        return this.amount;
    }
}
