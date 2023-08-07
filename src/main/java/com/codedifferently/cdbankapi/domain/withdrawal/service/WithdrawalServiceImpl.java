package com.codedifferently.cdbankapi.domain.withdrawal.service;

import com.codedifferently.cdbankapi.domain.withdrawal.exceptions.WithdrawalException;
import com.codedifferently.cdbankapi.domain.withdrawal.models.Withdrawal;
import com.codedifferently.cdbankapi.domain.withdrawal.repos.WithdrawalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {


    private WithdrawalRepo withdrawalRepo;

    @Autowired
    public WithdrawalServiceImpl(WithdrawalRepo withdrawalRepo){
        this.withdrawalRepo =  withdrawalRepo;
    }

    @Override
        public Withdrawal createWithdrawal(Withdrawal withdrawal) {
        return withdrawalRepo.save(withdrawal);
    }

    @Override
    public Withdrawal updateWithdrawal(Withdrawal withdrawal, long id) throws WithdrawalException {
        Withdrawal updateWithdrawal = findWithdrawal(id);

        updateWithdrawal.setAmount(withdrawal.getAmount());
        updateWithdrawal.setDescription(withdrawal.getDescription());
        updateWithdrawal.setPayer(withdrawal.getPayer());
        updateWithdrawal.setType(withdrawal.getType());
        updateWithdrawal.setMedium(withdrawal.getMedium());
        updateWithdrawal.setTransactionDate(withdrawal.getTransactionDate());

        withdrawalRepo.save(updateWithdrawal);

        return updateWithdrawal;
    }

    @Override
    public Withdrawal getWithdrawalById(long id) throws WithdrawalException {
        return findWithdrawal(id);
    }

    @Override
    public List<Withdrawal> getAllWithdrawals() {
        return withdrawalRepo.findAll();
    }

    @Override
    public Boolean deleteWithdrawalById(long id) throws WithdrawalException {
        Withdrawal withdrawal = findWithdrawal(id);
        withdrawalRepo.delete(withdrawal);

        return true;
    }

    private Withdrawal findWithdrawal(long id) throws WithdrawalException {
        Optional<Withdrawal> withdrawalOptional = withdrawalRepo.findById(id);
        if(withdrawalOptional.isEmpty()){
            throw new WithdrawalException("Withdrawal with id: "+id+". could not be found");
        }

        return withdrawalOptional.get();
    }
}
