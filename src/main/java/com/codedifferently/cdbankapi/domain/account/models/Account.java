package com.codedifferently.cdbankapi.domain.account.models;

import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.withdrawal.models.Withdrawal;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NonNull
    private String nickname;

    @NonNull
    private Long balance;

    @NonNull
    private String customer;

    @NonNull
    private String password;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)

    private List<Deposit> deposits;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)

    private List<Withdrawal> withdrawals;




}
