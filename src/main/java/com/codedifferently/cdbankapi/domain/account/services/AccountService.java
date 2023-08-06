package com.codedifferently.cdbankapi.domain.account.services;

import com.codedifferently.cdbankapi.domain.account.exceptions.AccountException;
import com.codedifferently.cdbankapi.domain.account.models.Account;

import java.util.List;

public interface AccountService {
    Account create(Account account) throws AccountException;

    Account getAccountById(Long id) throws AccountException;

    List<Account> getAllAccounts();

    Account updateAccount(Long id,Account accountDetail) throws AccountException;

    Long getAccountIdByNickname(String nickname);

    Integer loginChecker (String nickname,String password) throws AccountException;

    void deleteUser(long id) throws AccountException;




}