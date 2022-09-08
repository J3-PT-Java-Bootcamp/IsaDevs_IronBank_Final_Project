package com.ironhack.ironbank_monolit.repository.account;

import com.ironhack.ironbank_monolit.model.account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository <StudentChecking, Long> {
}
