package com.ironhack.ironbank_monolit.AccountUnitaryTest;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.repository.account.CheckingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckingTest {

    @Autowired
    private CheckingRepository checkingRepository;

    private Checking checkingDTO;

    @BeforeEach
    void setUp() {
        //var check = List.of(
        checkingDTO = new Checking(null, "cATYcAT", null, null, Status.ACTIVE, null, null, null);
        // );

        checkingRepository.save(checkingDTO);
    }

    @Test
    void count() {
        Assertions.assertEquals(1, checkingRepository.count());
    }
}
