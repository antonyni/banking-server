package com.codedifferently.cdbankapi.domain.withdrawl.repos;

import com.codedifferently.cdbankapi.domain.withdrawl.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepo extends JpaRepository<Long, Withdrawal> {

}
