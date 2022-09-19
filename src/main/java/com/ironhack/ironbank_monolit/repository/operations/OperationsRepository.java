package com.ironhack.ironbank_monolit.repository.operations;

import com.ironhack.ironbank_monolit.validation.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRepository extends JpaRepository<Operations, Long> {
}
