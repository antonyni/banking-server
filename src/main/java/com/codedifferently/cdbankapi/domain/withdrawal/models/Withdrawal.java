package com.codedifferently.cdbankapi.domain.withdrawal.models;


import com.codedifferently.cdbankapi.domain.enums.Medium;
import com.codedifferently.cdbankapi.domain.enums.Status;
import com.codedifferently.cdbankapi.domain.enums.Type;
import jakarta.persistence.*;
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
    private long amount;

    private String description;


}

