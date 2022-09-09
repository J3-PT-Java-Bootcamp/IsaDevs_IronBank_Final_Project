package com.ironhack.ironbank_monolit.model.user;

import com.ironhack.ironbank_monolit.model.account.Account;
import com.ironhack.ironbank_monolit.model.account.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdParty extends User {

    @Size(min = 24, max = 24)
    private String hashedKey;

    @NotBlank
    private Money amount;

    private long idAccount;

    private String secretKey;

    public ThirdParty(String name, Account owner, Account secondaryOwner, List<Account> accountList, String hashedKey, Money amount, long idAccount, String secretKey) {
        super(name, owner, secondaryOwner, accountList);
        this.hashedKey = hashedKey;
        this.amount = amount;
        this.idAccount = idAccount;
        this.secretKey = secretKey;
    }
}
