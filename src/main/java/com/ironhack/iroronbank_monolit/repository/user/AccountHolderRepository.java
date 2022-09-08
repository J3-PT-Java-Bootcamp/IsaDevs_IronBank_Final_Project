package com.ironhack.iroronbank_monolit.repository.user;

import com.ironhack.iroronbank_monolit.model.user.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository <AccountHolder, Long> {
}
