package org.example.ibroximjon.banking_app.repository;

import org.example.ibroximjon.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
