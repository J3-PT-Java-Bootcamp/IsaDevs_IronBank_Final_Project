package com.ironhack.ironbank_monolit.repository.account;

import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

    List<Checking> findByStatus(Status status);



}
