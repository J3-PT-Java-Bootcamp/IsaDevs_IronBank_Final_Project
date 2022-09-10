package com.ironhack.ironbank_monolit.userUnitaryTest;

import com.ironhack.ironbank_monolit.dto.userDTO.AccountHolderDTO;
import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.user.AccountHolder;
import com.ironhack.ironbank_monolit.repository.account.CheckingRepository;
import com.ironhack.ironbank_monolit.repository.user.AccountHolderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AccountHolderTest {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private CheckingRepository checkingRepository;


    @BeforeEach
    void setUp() {
        var holder = new AccountHolderDTO("Pedro Perez", 1, 1,  new Date(), 24, "Abbey Road", "England", 4563L, "abbeyroad@fantasymail.com");
        Account owner1 = checkingRepository.findById(holder.getOwner()).orElseThrow();
        Account owner2 = checkingRepository.findById(holder.getSecondaryOwner()).orElseThrow();

        var test1 = AccountHolder.byDTO(holder, owner1, owner2);
        // var test1 = AccountHolder.byDTO(holder, null, null);

        accountHolderRepository.save(test1);
    }

    @Test
    void counter() {
        Assertions.assertEquals(2, accountHolderRepository.count());
    }
}
