package com.codedifferently.cdbankapi.domain.withdrawl.service;

import com.codedifferently.cdbankapi.domain.withdrawl.exceptions.WithdrawalException;
import com.codedifferently.cdbankapi.domain.withdrawl.models.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal createWithdrawal(Withdrawal withdrawal);
    Withdrawal updateWithdrawal(Withdrawal withdrawal, long id) throws WithdrawalException;
    Withdrawal getWithdrawalById(long id) throws WithdrawalException;
    List<Withdrawal> getAllWithdrawals();
    Boolean deleteWithdrawalById(long id) throws WithdrawalException;
}
