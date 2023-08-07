package com.codedifferently.cdbankapi.domain.deposit.repos;

import com.codedifferently.cdbankapi.domain.deposit.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends JpaRepository<Deposit, Long> {

}
