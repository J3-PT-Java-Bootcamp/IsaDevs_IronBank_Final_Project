package com.ironhack.ironbank_monolit.repository.user;

import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHolderRepository extends JpaRepository <AccountHolder, Long> {
    AccountHolder findByName(String name);

}
