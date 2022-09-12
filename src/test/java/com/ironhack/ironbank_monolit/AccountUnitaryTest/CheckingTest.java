package com.ironhack.ironbank_monolit.AccountUnitaryTest;

import com.ironhack.ironbank_monolit.dto.accountDTO.CheckingDTO;
import com.ironhack.ironbank_monolit.model.account.Checking;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
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
        checkingDTO = new CheckingDTO(new Money(new BigDecimal("200")), "cATYcAT", 1, 1, Status.ACTIVE, 1, new Money(new BigDecimal("600")), new Money(new BigDecimal("50")));
        // );
    // findById();

        User owner1 = accountHolderRepository.findById(checkingDTO.getPrimaryOwner()).orElseThrow();
        User owner2 = accountHolderRepository.findById(checkingDTO.getSecondaryOwner()).orElseThrow();

        var checker = Checking.byDTO(checkingDTO, owner1, owner2);
        checkingRepository.save(checker);
    }

    @Test
    void count() {
        Assertions.assertEquals(3, checkingRepository.count());
    }
}
