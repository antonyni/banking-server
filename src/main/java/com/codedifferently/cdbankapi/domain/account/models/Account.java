package com.codedifferently.cdbankapi.domain.account.models;

import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.withdrawl.models.Withdrawal;
import com.codedifferently.cdbankapi.domain.enums.Type;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    Type type;

    @NonNull
    String nickname;

    @NonNull
    Long balance;

    @NonNull
    String customer;

    @OneToMany
    @JsonManagedReference
    List<Deposit> deposits;

    @OneToMany
    @JsonManagedReference
    List<Withdrawal> withdrawls;







}
