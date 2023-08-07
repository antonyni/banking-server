package com.codedifferently.cdbankapi.domain.withdrawal.repos;

import com.codedifferently.cdbankapi.domain.withdrawal.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepo extends JpaRepository<Withdrawal, Long> {

}
