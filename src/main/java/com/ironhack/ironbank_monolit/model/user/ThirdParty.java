package com.ironhack.ironbank_monolit.model.user;

import com.ironhack.ironbank_monolit.model.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdParty extends User {

    private String hashedKey;

    private BigDecimal amount;

    private long idAccount;

    private String secretKey;

    public ThirdParty(String name, Account owner, Account secondaryOwner, List<Account> accountList, String hashedKey, BigDecimal amount, long idAccount, String secretKey) {
        super(name, owner, secondaryOwner, accountList);
        this.hashedKey = hashedKey;
        this.amount = amount;
        this.idAccount = idAccount;
        this.secretKey = secretKey;
    }
}
