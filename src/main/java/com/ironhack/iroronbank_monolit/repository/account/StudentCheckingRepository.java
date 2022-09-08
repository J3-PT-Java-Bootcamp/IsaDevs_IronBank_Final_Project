package com.ironhack.iroronbank_monolit.repository.account;

import com.ironhack.iroronbank_monolit.model.account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository <StudentChecking, Long> {
}
