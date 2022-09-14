package com.ironhack.ironbank_monolit.dto.accountDTO;

import com.ironhack.ironbank_monolit.model.account.Credit;
import com.ironhack.ironbank_monolit.model.account.Money;
import com.ironhack.ironbank_monolit.model.account.Saving;
import com.ironhack.ironbank_monolit.model.enums.Status;
import com.ironhack.ironbank_monolit.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SavingDTO {

    private Money balance;

    private String secretKey;

    private long primaryOwner;

    private long secondaryOwner;

    private Status status;

    //private long accounts;

    //own by class
    //private Money minimalBalance;

    private BigDecimal interestRate;

    public static SavingDTO byObject(Saving saving){

        return new SavingDTO(saving.getBalance(),saving.getSecretKey(), saving.getPrimaryOwner().getId(), saving.getSecondaryOwner().getId(), saving.getStatus(),  saving.getInterestRate());
    }

}
