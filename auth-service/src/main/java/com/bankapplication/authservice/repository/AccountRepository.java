package com.bankapplication.authservice.repository;

import com.bankapplication.authservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
