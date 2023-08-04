package com.codedifferently.cdbankapi.domain.withdrawl.models;


import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private Type type;

    @NonNull
    private Date transactionDate;

    @NonNull
    private Status status;

    @NonNull
    private String payer;

    @NonNull
    private Medium medium;

    @NonNull
    private long amount;

    private String description;


}

