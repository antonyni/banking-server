package com.codedifferently.cdbankapi.domain.account.repos;

import com.codedifferently.cdbankapi.domain.account.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account,Long> {

    Optional<Account> findByNickname(String nickname);

}
