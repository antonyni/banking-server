package com.codedifferently.cdbankapi.domain.withdrawal.models;

import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
