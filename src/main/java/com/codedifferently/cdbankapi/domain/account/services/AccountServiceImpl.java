package com.codedifferently.cdbankapi.domain.account.services;

import com.codedifferently.cdbankapi.domain.account.exceptions.AccountException;
import com.codedifferently.cdbankapi.domain.account.models.Account;
import com.codedifferently.cdbankapi.domain.account.repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepo accountRepo;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }



    @Override
    public Account create(Account account) throws AccountException {
        Optional<Account> accountOptional = accountRepo.findByNickname(account.getNickname());
        if(accountOptional.isPresent())
            throw new AccountException("Account with nickname exists: " + account.getNickname());
        return accountRepo.save(account);
    }

    @Override
    public Account getAccountById(Long id) throws AccountException {
        Account account = accountRepo.findById(id)
                .orElseThrow(()-> new AccountException("No Account with id: " + id));
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public Account updateAccount(Long id, Account accountDetail) throws AccountException {
        Account account = getAccountById(id);
        account.setType(accountDetail.getType());
        account.setCustomer(accountDetail.getCustomer());
        account.setPassword(accountDetail.getPassword());
        return accountRepo.save(account);
    }

    @Override
    public Long getAccountIdByNickname(String nickname) {
        Optional<Account> accountOptional = accountRepo.findByNickname(nickname);
        if(accountOptional.isEmpty())
            return 0L;
        return accountOptional.get().getId();
    }

    @Override
    public Integer loginChecker(String nickname, String password) throws AccountException {
        Long id = getAccountIdByNickname(nickname);
        if(id== 0L)
            return 0;
        if(getAccountById(id).getPassword().equals(password))
            return 1;
        return -1;
    }

    @Override
    public void deleteUser(long id) throws AccountException {
        Account account = getAccountById(id);
        accountRepo.delete(account);
    }
}
