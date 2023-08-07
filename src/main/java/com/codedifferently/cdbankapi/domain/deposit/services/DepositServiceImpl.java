package com.codedifferently.cdbankapi.domain.deposit.services;

import com.codedifferently.cdbankapi.domain.deposit.exceptions.DepositException;
import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import com.codedifferently.cdbankapi.domain.deposit.repos.DepositRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService {

    private DepositRepo depositRepo;

    @Autowired
    public DepositServiceImpl(DepositRepo depositRepo) {
        this.depositRepo = depositRepo;
    }

    @Override
    public Deposit createDeposit(Deposit deposit) throws DepositException {
        return depositRepo.save(deposit);
    }

    @Override
    public Deposit updateDeposit(Deposit deposit, long id) throws DepositException {
        Deposit updateDeposit = findDeposit(id);

        updateDeposit.setType(deposit.getType());
        updateDeposit.setTransactionDate(deposit.getTransactionDate());
        updateDeposit.setStatus(deposit.getStatus());
        updateDeposit.setPayee(deposit.getPayee());
        updateDeposit.setMedium(deposit.getMedium());
        updateDeposit.setAmount(deposit.getAmount());
        updateDeposit.setDescription(deposit.getDescription());

        depositRepo.save(updateDeposit);

        return updateDeposit;
    }

    @Override
    public Deposit getDepositById(long id) throws DepositException {
        return findDeposit(id);
    }

    @Override
    public List<Deposit> getAllDeposits() {
        return depositRepo.findAll();
    }

    @Override
    public boolean deleteDepositById(long id) throws DepositException {
        Deposit deposit = findDeposit(id);
        depositRepo.delete(deposit);
        return true;
    }

    private Deposit findDeposit(long id) throws DepositException {
        Optional<Deposit> depositOptional = depositRepo.findById(id);
        if (depositOptional.isEmpty()) {
            throw new DepositException("Deposit with id: " + id + " could not be found");
        }
        return depositOptional.get();
    }
}
