package com.codedifferently.cdbankapi.domain.deposit.models;

import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name ="account_id")
    private long accountId;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transactionDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @NonNull
    @Column(nullable = false)
    private String payee;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Medium medium;

    @NonNull
    @Column(nullable = false)
    private long amount;

    @NonNull
    private String description;
}




