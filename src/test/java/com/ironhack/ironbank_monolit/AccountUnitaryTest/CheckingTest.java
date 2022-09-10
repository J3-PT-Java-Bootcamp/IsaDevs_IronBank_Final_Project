package com.ironhack.ironbank_monolit.AccountUnitaryTest;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.repository.account.CheckingRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CheckingTest {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    private CheckingDTO checkingDTO;

    private Checking checking;

    @BeforeEach
    void setUp() {
        //var check = List.of(
        checkingDTO = new CheckingDTO(new BigDecimal("550"), "cATYcAT", 1, 1, Status.ACTIVE, 1, new BigDecimal("600"), new BigDecimal("50"));
        // );
    // findById();

        //var check = Checking.byDTO(checkingDTO);
        //checkingRepository.save(check);
    }

    @Test
    void count() {
        Assertions.assertEquals(2, checkingRepository.count());
    }
}
