package com.codedifferently.cdbankapi.domain.withdrawal.models;

import com.codedifferently.cdbankapi.domain.Transaction;
import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Withdrawal extends Transaction {

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
    private Date transactionDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NonNull
    private String payer;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Medium medium;

    @NonNull
    private Long amount;

    @Transient
    private String transactionType;



    private String description;


}
