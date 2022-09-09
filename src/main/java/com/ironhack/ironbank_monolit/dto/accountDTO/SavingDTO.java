package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.Saving;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
public class SavingDTO {

    private Money balance;

    private String secretKey;

    private User primaryOwner;

    private User secondaryOwner;

    private Status status;

    private User accounts;

    //own by class
    private Money minimalBalance;

    private BigDecimal interestRate;

    public static SavingDTO byObject(Saving saving){
        var dtoSaving = new SavingDTO();
        dtoSaving.setBalance(saving.getBalance());
        dtoSaving.setSecretKey(saving.getSecretKey());
        dtoSaving.setPrimaryOwner(saving.getPrimaryOwner());
        dtoSaving.setSecondaryOwner(saving.getSecondaryOwner());
        dtoSaving.setStatus(saving.getStatus());
        dtoSaving.setAccounts(saving.getAccounts());
        dtoSaving.setMinimalBalance(saving.getMinimalBalance());
        dtoSaving.setInterestRate(saving.getInterestRate());

        return dtoSaving;
    }

}
