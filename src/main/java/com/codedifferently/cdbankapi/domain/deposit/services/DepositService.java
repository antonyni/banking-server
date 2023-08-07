package com.codedifferently.cdbankapi.domain.deposit.services;

import com.codedifferently.cdbankapi.domain.deposit.exceptions.DepositException;
import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;

import java.util.List;

public interface DepositService {

    Deposit createDeposit(Deposit deposit) throws DepositException;
    Deposit updateDeposit(Deposit deposit, long id) throws DepositException;
    Deposit getDepositById(long id) throws DepositException;
    List<Deposit> getAllDeposits();
    boolean deleteDepositById(long id) throws DepositException;
}
