package com.msaccount_se182744.repository;

import com.msaccount_se182744.entity.SystemAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemAccountRepository extends JpaRepository<SystemAccounts, Integer> {
    Optional<SystemAccounts> findByEmail(String email);
}
