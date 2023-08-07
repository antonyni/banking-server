package com.codedifferently.cdbankapi.domain.withdrawal.service;

import com.codedifferently.cdbankapi.domain.withdrawal.exceptions.WithdrawalException;
import com.codedifferently.cdbankapi.domain.withdrawal.models.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal createWithdrawal(Withdrawal withdrawal);
    Withdrawal updateWithdrawal(Withdrawal withdrawal, long id) throws WithdrawalException;
    Withdrawal getWithdrawalById(long id) throws WithdrawalException;
    List<Withdrawal> getAllWithdrawals();
    Boolean deleteWithdrawalById(long id) throws WithdrawalException;
}
