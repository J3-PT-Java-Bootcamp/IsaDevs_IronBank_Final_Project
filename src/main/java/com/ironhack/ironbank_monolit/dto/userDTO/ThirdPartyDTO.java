package com.ironhack.ironbank_monolit.dto.userDTO;

import com.ironhack.ironbank_monolit.model.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ThirdPartyDTO {

    private String name;

    private Account owner;

    private Account secondaryOwner;

    private List<Account> accountList;

    //by class

    private String hashedKey;

    private BigDecimal amount;

    private long idAccount;

    private String secretKey;
}
