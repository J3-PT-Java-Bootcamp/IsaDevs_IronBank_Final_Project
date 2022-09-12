package com.ironhack.ironbank_monolit.repository.account;

import com.ironhack.ironbank_monolit.model.account.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {

    Account findBySecretKey(String pass);
}
