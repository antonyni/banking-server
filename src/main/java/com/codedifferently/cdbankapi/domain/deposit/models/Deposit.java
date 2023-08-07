package com.codedifferently.cdbankapi.domain.deposit.models;

import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String payee;


    public Deposit(Long id, Medium medium, String transaction_date, int amount, String description, Status status, int payee_id, Type type, Account account) {
        this.id = id;
        this.medium = medium;
        this.transaction_date = transaction_date;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.payee_id = payee_id;
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Medium medium;

    @Column(nullable = false)
    private long amount;

    private String description;

    // Constructors
    public Deposit() {
    }

    public Deposit(Type type, Date transactionDate, Status status, String payee, Medium medium, long amount, String description) {
        this.type = type;
        this.transactionDate = transactionDate;
        this.status = status;
        this.payee = payee;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
    }

    public Deposit(long l, Type type, Date date, Status status, String john, Medium medium, int i, String finished) {
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", type=" + type +
                ", transactionDate=" + transactionDate +
                ", status=" + status +
                ", payee='" + payee + '\'' +
                ", medium=" + medium +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}




